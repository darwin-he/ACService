package com.admin.websocket;

import com.admin.model.Device;
import com.admin.model.Record;
import com.admin.model.User;
import com.admin.service.*;
import com.admin.utils.TimeUtil;
import com.admin.vo.CommonResult;
import com.admin.websocket.deviceDataModel.EnviroDate;
import com.admin.websocket.deviceDataModel.UserCard;
import com.admin.websocket.msgdefin.CodeEnum;
import com.admin.websocket.msgdefin.MsgResult;
import com.admin.websocket.msgdefin.MsgRoute;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
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

	private static final String SERVICE_ID="LocalService";
    
    //日志工具
    private  static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);
    
    //客户端列表
    private  static final Map<String,Session> clients=new HashMap<>();
    
    @OnOpen
    public void onOpen(Session session){
        log.info("客户端连接:" + session.getId());
    }
    
    /**
     * 连接断开时调用的方法
     */
    @OnClose
    public void onClose(Session session)  {
        log.info("客户端断开连接："+session.getId());
        Iterator<Map.Entry<String, Session>> clientMap = clients.entrySet().iterator();
        while (clientMap.hasNext()) {
            Map.Entry<String, Session> entry = clientMap.next();
            if (entry.getValue().equals(session)){
	            if (entry.getKey().charAt(0)=='D'){
		            //设备连接断开，通知管理员
		            String adminKey="A"+entry.getKey().substring(1);
		            Session adminSession=clients.get(adminKey);
		            if (adminSession!=null){
			            MsgRoute msgRoute=new MsgRoute();
			            msgRoute.setFrom(SERVICE_ID);
			            msgRoute.setTo(adminKey);
			            MsgResult msgResult=new MsgResult(msgRoute,100,"设备连接已断开！");
			            sendMessage(msgResult,adminSession);
		            }
	            }
                clientMap.remove();
            }
        }
        
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
	    log.info("收到客户端消息:" + message);
        MsgResult msgResult;
        try {
            msgResult= JSON.parseObject(message).toJavaObject(MsgResult.class);
        }catch (Exception e){
        	e.printStackTrace();
            return;
        }
        
        if (SERVICE_ID.equals(msgResult.getMsgRoute().getTo())){
            decodeServiceMsg(msgResult,session);
        }else {
            Session sessionTo=clients.get(msgResult.getMsgRoute().getTo());
            if (sessionTo!=null){
                sendMessage(message,sessionTo);
            }
        }
    }

    /**
    * 处理发送给服务器的消息
    * @param msgResult
    * @param session
    */
    private void decodeServiceMsg(MsgResult msgResult, Session session) {
        int code=msgResult.getCode();
        MsgRoute backRoute=new MsgRoute();
        backRoute.setTo(msgResult.getMsgRoute().getFrom());
        backRoute.setFrom(msgResult.getMsgRoute().getTo());
	    //门禁设备验证用户信息
        if (code== CodeEnum.GET_USERINFOR.getCode()){
        	UserCard userCard= JSON.toJavaObject((JSONObject)msgResult.getData(),UserCard.class);
	        //查询用户信息
	        CommonResult userResult=(CommonResult) userService.getUserByCardNumber(userCard.getUserCard());
	        //查询设备信息
	        CommonResult deviceResult=(CommonResult)deviceService.getDeviceByDeviceNumber(msgResult.getMsgRoute().getFrom().substring(1));
	        if (userResult.getCode()==0&&deviceResult.getCode()==0){
		        MsgResult sendData=new MsgResult(backRoute,CodeEnum.GET_USERINFOR_SUCCEDSS.getCode(),CodeEnum.GET_USERINFOR_SUCCEDSS.getMsg());
		        sendMessage(sendData,session);
		        //新增通行记录
		        User user=(User) userResult.getData();
		        Device device=(Device)deviceResult.getData();
		        Record record=new Record();
		        record.setUserAccount(user.getAccount());
		        record.setUserName(user.getName());
		        record.setDeviceNumber(device.getDeviceNumber());
		        record.setDeviceNikeName(device.getDeviceNikeName());
		        record.setState("出");
		        record.setTime(TimeUtil.getCurrentTime());
		        recordService.addRecord(record);
	        }else {
		        MsgResult sendData=new MsgResult(backRoute,CodeEnum.GET_USERINFOR_DEFAULT.getCode(),userResult.getMsg()+deviceResult.getMsg());
		        sendMessage(sendData,session);
	        }
	        return;
        }
        
	    //客户端连接时进行身份验证
        if (code== CodeEnum.UPDATE_IDENTITY_INFOR.getCode()){
        	//来自硬件设备的身份验证请求
	        if (msgResult.getMsgRoute().getFrom().charAt(0)=='D'){
		        //查询设备信息
		        CommonResult deviceResult=(CommonResult)deviceService.getDeviceByDeviceNumber(msgResult.getMsgRoute().getFrom().substring(1));
		        if(deviceResult.getCode()==0){
			        log.info("添加会话:" + session.getId());
			        clients.put(msgResult.getMsgRoute().getFrom(),session);
			        MsgResult sendData=new MsgResult(backRoute,CodeEnum.UPDATE_IDENTITY_INFOR_SUCCESS.getCode(), CodeEnum.UPDATE_IDENTITY_INFOR_SUCCESS.getMsg());
			        sendMessage(sendData,session);
			        //通知管理员设备已连接
			        String adminKey="A"+msgResult.getMsgRoute().getFrom().substring(1);
			        Session adminSession=clients.get(adminKey);
			        if (adminSession!=null){
				        MsgRoute msgRoute=new MsgRoute();
				        msgRoute.setFrom(SERVICE_ID);
				        msgRoute.setTo(adminKey);
				        MsgResult sendMsg=new MsgResult(msgRoute,101,"设备已连接！");
				        sendMessage(sendMsg,adminSession);
			        }
		        }else {
			        MsgResult sendData=new MsgResult(backRoute,CodeEnum.UPDATE_IDENTITY_INFOR_DEFAULT.getCode(), CodeEnum.UPDATE_IDENTITY_INFOR_DEFAULT.getMsg());
			        sendMessage(sendData,session);
		        }
		        return;
	        }
	        //来自管理员的身份验证请求
	        if (msgResult.getMsgRoute().getFrom().charAt(0)=='A'){
	        	//查询管理员信息
		        CommonResult adminResult=(CommonResult)adminService.getAdminByDeviceNumber(msgResult.getMsgRoute().getFrom().substring(1));
		        if (adminResult.getCode()==0){
			        log.info("添加会话:" + session.getId());
			        clients.put(msgResult.getMsgRoute().getFrom(),session);
			        MsgResult sendData=new MsgResult(backRoute,CodeEnum.UPDATE_IDENTITY_INFOR_SUCCESS.getCode(), CodeEnum.UPDATE_IDENTITY_INFOR_SUCCESS.getMsg());
			        sendMessage(sendData,session);
			        //告知管理员设备连接状态
			        String deviceKey="D"+msgResult.getMsgRoute().getFrom().substring(1);
			        Session deviceSession=clients.get(deviceKey);
			        MsgRoute msgRoute=new MsgRoute();
			        msgRoute.setFrom(SERVICE_ID);
			        msgRoute.setTo(msgResult.getMsgRoute().getFrom());
			        if (deviceSession!=null){
				        MsgResult sendMsg=new MsgResult(msgRoute,101,"设备已连接！");
				        sendMessage(sendMsg,session);
			        }else {
				        MsgResult sendMsg=new MsgResult(msgRoute,100,"设备未连接！");
				        sendMessage(sendMsg,session);
			        }
		        }else {
			        MsgResult sendData=new MsgResult(backRoute,CodeEnum.UPDATE_IDENTITY_INFOR_DEFAULT.getCode(), adminResult.getMsg());
			        sendMessage(sendData,session);
		        }
		        return;
	        }
        }
        
	    //设备上传环境数据
        if (code== CodeEnum.UPDATE_ENVIRODATE.getCode()){
	        //查询设备信息
	        CommonResult deviceResult=(CommonResult)deviceService.getDeviceByDeviceNumber(msgResult.getMsgRoute().getFrom().substring(1));
	        if (deviceResult.getCode()==0){
		        Device device=(Device) deviceResult.getData();
		        EnviroDate enviroDate=JSON.toJavaObject((JSONObject)msgResult.getData(),EnviroDate.class);
		        com.admin.model.EnviroData enviroDataRecord=new com.admin.model.EnviroData();
		        enviroDataRecord.setDeviceNumber(device.getDeviceNumber());
		        enviroDataRecord.setDeviceNikeName(device.getDeviceNikeName());
		        enviroDataRecord.setTemperature(enviroDate.getTemperature());
		        enviroDataRecord.setHumidity(enviroDate.getHumidity());
		        enviroDataRecord.setLightIntensity(enviroDate.getLightIntensity());
		        enviroDataRecord.setTime(enviroDate.getTime());
		        //新增环境数据记录
		        CommonResult addResult= (CommonResult) enviroService.addErviroData(enviroDataRecord);
		        if (addResult.getCode()==0){
			        MsgResult sendData=new MsgResult(backRoute,CodeEnum.UPDATA_ENVIRODATE_SUCCESS.getCode(),CodeEnum.UPDATA_ENVIRODATE_SUCCESS.getMsg());
			        sendMessage(sendData,session);
		        }else {
			        MsgResult sendData=new MsgResult(backRoute,CodeEnum.UPDATE_ENVIRODATE_DEFAULT.getCode(),addResult.getMsg());
			        sendMessage(sendData,session);
		        }
	        }else {
		        MsgResult sendData=new MsgResult(backRoute,CodeEnum.UPDATE_ENVIRODATE_DEFAULT.getCode(),deviceResult.getMsg());
		        sendMessage(sendData,session);
	        }
	        return;
        }
    }

    /**
 　　 * 发生错误时触发的方法
 　　*/
    @OnError
    public  void onError(Session session,Throwable error){
    	error.printStackTrace();
    }

    /**
     * 发送消息给客户端
     * @param msgResult
     * @return
     */
    private  void sendMessage(MsgResult msgResult,Session session)  {
        String sendString=JSON.toJSONString(msgResult);
	    sendMessage(sendString,session);
    }
    private  void sendMessage(String msg,Session session){
        try {
            session.getBasicRemote().sendText(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
