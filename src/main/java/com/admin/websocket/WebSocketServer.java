package com.admin.websocket;

import com.admin.model.Device;
import com.admin.model.Record;
import com.admin.model.User;
import com.admin.service.*;
import com.admin.utils.JsonUtil;
import com.admin.utils.TimeUtil;
import com.admin.vo.CommonResult;
import com.admin.websocket.deviceDataModel.EnviroDate;
import com.admin.websocket.deviceDataModel.Handle;
import com.admin.websocket.msgdefin.Code;
import com.admin.websocket.msgdefin.Message;
import com.admin.websocket.msgdefin.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author darwin_he
 * @date 2019/5/7 0:45
 */
@ServerEndpoint(value = "/LocalService")
@Component
public class WebSocketServer {
    private static AdminService adminService;

    @Autowired
    public void setAdminService(AdminService adminService) {
        WebSocketServer.adminService = adminService;
    }

    private static EnviroService enviroService;

    @Autowired
    public void setEnviroService(EnviroService enviroService) {
        WebSocketServer.enviroService = enviroService;
    }

    private static RecordService recordService;

    @Autowired
    public void setRecordService(RecordService recordService) {
        WebSocketServer.recordService = recordService;
    }

    private static DeviceService deviceService;

    @Autowired
    public void setDeviceService(DeviceService deviceService) {
        WebSocketServer.deviceService = deviceService;
    }

    private static UserInfService userService;

    @Autowired
    public void setUserService(UserInfService userService) {
        WebSocketServer.userService = userService;
    }

//    @Autowired
//    private static SystemService systemService;
//	public void setSystemService(SystemService systemService) {
//		WebSocketServer.systemService = systemService;
//	}

    private static final String SERVICE_ID = "LocalService";

    //日志工具
    private static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);

    //客户端列表
    private static final Map<String, Session> clients = new HashMap<>();

    @OnOpen
    public void onOpen(Session session) {
        log.info("客户端请求连接:" + session.getId());
    }

    /**
     * 连接断开时调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        log.info("客户端连接断开：" + session.getId());
        Iterator<Map.Entry<String, Session>> clientMap = clients.entrySet().iterator();
        while (clientMap.hasNext()) {
            Map.Entry<String, Session> entry = clientMap.next();
            if (entry.getValue().getId().equals(session.getId())) {
                if (entry.getKey().charAt(0) == 'D') {
                    //设备连接断开，通知管理员
                    String adminKey = "A" + entry.getKey().substring(1);
                    Session adminSession = clients.get(adminKey);
                    if (adminSession != null) {
                        Route route = new Route();
                        route.setFrom(SERVICE_ID);
                        route.setTo(adminKey);
                        Message message = new Message(route, 100, "设备连接已断开！");
                        sendMessage(message, adminSession);
                    }
                }
                clientMap.remove();
                return;
            }
        }

    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到客户端" + session.getId() + "消息：" + message);
        Message msg;
        try {
            msg = JsonUtil.toJavaBean(message, Message.class);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        msg.getRoute();
        if (SERVICE_ID.equals(msg.getRoute().getTo())) {
            dealToServiceMsg(msg, session);
        } else {
            Session sessionTo = clients.get(msg.getRoute().getTo());
            if (sessionTo != null) {
                sendMessage(message, sessionTo);
            }
        }
    }

    /**
     * 发生错误时触发的方法
     *
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }




    /**
     * 处理发送给服务器的消息
     *
     * @param message
     * @param session
     */
    private void dealToServiceMsg(Message message, Session session) {
        int code = message.getCode();
        Route backRoute = new Route();
        backRoute.setTo(message.getRoute().getFrom());
        backRoute.setFrom(message.getRoute().getTo());

        //门禁设备请求验证用户信息
        if (code == Code.CHECK_USERINFO.getCode()) {
            checkUserInfo(message, session);
            return;
        }

        //门禁设备被打开
        if (code == Code.OPENDOOR_SUCCESS.getCode()) {
            onDoorOpened(message, session);
            return;
        }

        //门禁设备关闭
        if (code == Code.CCLOSEDOOR_SUCCESS.getCode()) {
            onDoorClosed(message, session);
            return;
        }

        //设备上传环境数据
        if (code == Code.UPLOAD_ENVIRODATE.getCode()) {
            onUploadEnviroData(message, session);
            return;
        }

        //客户端连接时进行身份验证
        if (code == Code.UPLOAD_IDENTITY_INFO.getCode()) {
            authClientIdentity(message, session);
        }
    }

    /**
     * 处理校验用户信息请求
     * @param message
     * @param session
     */
    private void checkUserInfo(Message message, Session session) {
        Handle handle = JsonUtil.toJavaBean(message.getData(), Handle.class);
        Route backRoute = overturnRoute(message.getRoute());
        //查询用户信息
        CommonResult userResult = (CommonResult) userService.getUserByCardNumber(handle.getUserCard().getUserCard());
        //查询设备信息
        CommonResult deviceResult = (CommonResult) deviceService.getDeviceByDeviceNumber(message.getRoute().getFrom().substring(1));
        Message sendData;
        if (userResult.getCode() == 0 && deviceResult.getCode() == 0) {
            sendData = new Message(backRoute, Code.CHECK_USERINFO_SUCCESS, JsonUtil.toJsonStr(handle));
        } else {
            sendData = new Message(backRoute, Code.CHECK_USERINFO_DEFAULT, userResult.getMsg() + deviceResult.getMsg());
        }
        sendMessage(sendData, session);
    }

    /**
     * 当门禁设备被开启时的处理函数
     * @param message
     * @param session
     */
    private void onDoorOpened(Message message, Session session) {
        Handle handle = JsonUtil.toJavaBean(message.getData(), Handle.class);
        CommonResult userResult = (CommonResult) userService.getUserByCardNumber(handle.getUserCard().getUserCard());
        User user = (User) userResult.getData();
        CommonResult deviceResult = (CommonResult) deviceService.getDeviceByDeviceNumber(handle.getDeviceNumber());
        Device device = (Device) deviceResult.getData();
        //新增通行记录
        Record record = new Record();
        record.setUserAccount(user.getAccount());
        record.setUserName(user.getName());
        record.setDeviceNumber(device.getDeviceNumber());
        record.setDeviceNikeName(device.getDeviceNikeName());
        record.setState(handle.getHandle());
        record.setTime(TimeUtil.getCurrentTime());
        recordService.addRecord(record);
        //通知管理员门禁设备被开启
        String adminKey = "A" + message.getRoute().getFrom().substring(1);
        Session adminSession = clients.get(adminKey);
        if (adminSession == null) return;
        Route route = new Route();
        route.setFrom(message.getRoute().getFrom());
        route.setTo(adminKey);
        Message sendMsg = new Message(route, Code.OPENDOOR_SUCCESS);
        sendMessage(sendMsg, adminSession);
    }

    /**
     * 当门禁设备被关闭时的处理函数
     * @param message
     * @param session
     */
    private void onDoorClosed(Message message, Session session) {
        //通知管理员门禁设备已关闭
        String adminKey = "A" + message.getRoute().getFrom().substring(1);
        Session adminSession = clients.get(adminKey);
        if (adminSession == null) return;
        Route route = new Route();
        route.setFrom(message.getRoute().getFrom());
        route.setTo(adminKey);
        Message sendMsg = new Message(route, Code.CCLOSEDOOR_SUCCESS);
        sendMessage(sendMsg, adminSession);
    }

    /**
     * 当门禁设备上传环境数据时
     * @param message
     * @param session
     */
    private void onUploadEnviroData(Message message, Session session) {
        String deviceNumber = message.getRoute().getFrom().substring(1);
        CommonResult deviceResult = (CommonResult) deviceService.getDeviceByDeviceNumber(deviceNumber);
        if (deviceResult.getCode() != 0) return;
        Device device = (Device) deviceResult.getData();
        EnviroDate enviroDate = JsonUtil.toJavaBean(message.getData(), EnviroDate.class);;
        com.admin.model.EnviroData enviroDataRecord = new com.admin.model.EnviroData();
        enviroDataRecord.setDeviceNumber(device.getDeviceNumber());
        enviroDataRecord.setDeviceNikeName(device.getDeviceNikeName());
        enviroDataRecord.setTemperature(enviroDate.getTemperature());
        enviroDataRecord.setHumidity(enviroDate.getHumidity());
        enviroDataRecord.setLightIntensity(enviroDate.getLightIntensity());
        enviroDataRecord.setTime(enviroDate.getTime());
        //新增环境数据记录
        enviroService.addErviroData(enviroDataRecord);
        //转发给管理员
        String adminKey = "A" + message.getRoute().getFrom().substring(1);
        Session adminSession = clients.get(adminKey);
        if (adminSession == null) return;
        Route route = new Route();
        route.setFrom(message.getRoute().getFrom());
        route.setTo(adminKey);
        Message sendMsg = new Message(route, Code.UPLOAD_ENVIRODATE, message.getData());
        sendMessage(sendMsg, adminSession);
    }

    /**
     * 客户端验证身份信息
     * @param message
     * @param session
     */
    private void authClientIdentity(Message message, Session session) {
        //来自硬件设备的身份验证请求
        if (message.getRoute().getFrom().charAt(0) == 'D') {
            String deviceNumber = message.getRoute().getFrom().substring(1);
            CommonResult deviceResult = (CommonResult) deviceService.getDeviceByDeviceNumber(deviceNumber);
            if (deviceResult.getCode() != 0) return;
            log.info("添加设备会话:" + session.getId());
            clients.put(message.getRoute().getFrom(), session);
            Message sendData = new Message(overturnRoute(message.getRoute()), Code.UPLOAD_IDENTITY_INFO_SUCCESS);
            sendMessage(sendData, session);
            //通知管理员设备已连接
            String adminKey = "A" + deviceNumber;
            Session adminSession = clients.get(adminKey);
            if (adminSession == null) return;
            Route route = new Route();
            route.setFrom(SERVICE_ID);
            route.setTo(adminKey);
            Message sendMsg = new Message(route, 101, "设备已连接！");
            sendMessage(sendMsg, adminSession);
            return;
        }
        //来自管理员的身份验证请求
        if (message.getRoute().getFrom().charAt(0) == 'A') {
            String deviceNumber = message.getRoute().getFrom().substring(1);
            CommonResult adminResult = (CommonResult) adminService.getAdminByDeviceNumber(deviceNumber);
            if (adminResult.getCode() != 0) return;
            log.info("添加会话:" + session.getId());
            clients.put(message.getRoute().getFrom(), session);
            Message sendData = new Message(overturnRoute(message.getRoute()), Code.UPLOAD_IDENTITY_INFO_SUCCESS);
            sendMessage(sendData, session);
            //告知管理员设备当前连接状态
            String deviceKey = "D" + message.getRoute().getFrom().substring(1);
            Session deviceSession = clients.get(deviceKey);
            Route msgRoute = new Route();
            msgRoute.setFrom(SERVICE_ID);
            msgRoute.setTo(message.getRoute().getFrom());
            Message sendMsg;
            if (deviceSession != null) {
                sendMsg = new Message(msgRoute, 101, "设备已连接！");
            } else {
                sendMsg = new Message(msgRoute, 100, "设备未连接！");
            }
            sendMessage(sendMsg, session);
            return;
        }
        log.info("unknown auth request, close the session");
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    /**
     * 翻转消息路由
     *
     * @param route
     * @return
     */
    private Route overturnRoute(Route route) {
        Route turnedRoute = new Route();
        turnedRoute.setFrom(route.getTo());
        turnedRoute.setTo(route.getFrom());
        return turnedRoute;
    }

    /**
     * 发送消息给客户端
     *
     * @param message
     * @return
     */
    private void sendMessage(Message message, Session session) {
        String msg = JsonUtil.toJsonStr(message);
        sendMessage(msg, session);
    }

    /**
     * 发送文本消息给客户端
     * @param msg
     * @param session
     */
    private void sendMessage(String msg, Session session) {
        try {
            session.getBasicRemote().sendText(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
