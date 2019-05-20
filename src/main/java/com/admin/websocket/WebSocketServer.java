package com.admin.websocket;

import com.admin.service.UserInfService;
import com.admin.websocket.msgdefin.CodeEnum;
import com.admin.websocket.msgdefin.MsgResult;
import com.admin.websocket.msgdefin.MsgRoute;
import com.alibaba.fastjson.JSON;
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
 * \* Created with IntelliJ IDEA.
 * \* User: hxl
 * \* Date: 2018/12/3
 * \* Time: 22:24
 * \* Description:
 * \
 */


@ServerEndpoint(value = "/LocalService")
@Component
public class WebSocketServer {
    
    @Autowired
    private UserInfService userService;
    
    private static final String SERVICE_ID="LocalService";
    
    //日志工具
    private static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);
    
    //客户端列表
    private static Map<String,Session> clients=new HashMap<>();
    
    @OnOpen
    public static void onOpen(Session session){
        log.info("客户端连接:" + session.getId());
    }
    
    /**
     * 连接断开时调用的方法
     */
    @OnClose
    public static void onClose(Session session) {
        log.info("客户端断开连接："+session.getId());
        
        Iterator<Map.Entry<String, Session>> clientMap = clients.entrySet().iterator();
        while (clientMap.hasNext()) {
            Map.Entry<String, Session> entry = clientMap.next();
            if (entry.getValue().equals(session)){
                clientMap.remove();
            }
        }
        
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public static void onMessage(String message, Session session) {
        log.info("收到客户端消息:" + message);
        //解析数据
        MsgResult msgResult;
        try {
            msgResult= JSON.parseObject(message).toJavaObject(MsgResult.class);
        }catch (Exception e){
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
    private static void decodeServiceMsg(MsgResult msgResult, Session session) {
        int code=msgResult.getCode();
        if (code== CodeEnum.GET_USERINFOR.getCode()){//门禁设备验证用户信息
            //查询用户信息
            
            //添加通行记录
            
            //用户信息
            MsgResult sendData=new MsgResult(msgResult.getMsgRoute(),CodeEnum.GET_USERINFOR_SUCCEDSS.getCode(), CodeEnum.GET_USERINFOR_SUCCEDSS.getMsg());
            sendMessage(sendData,session);
        }else if (code== CodeEnum.UPDATE_IDENTITY_INFOR.getCode()){//上传身份信息
            log.info("添加会话:" + session.getId());
            //上传身份信息成功(管理和设备都会上传身份信息来建立连接)保存到会话列表
            clients.put(msgResult.getMsgRoute().getFrom(),session);
            MsgResult sendData=new MsgResult(msgResult.getMsgRoute(),CodeEnum.UPDATE_IDENTITY_INFOR_SUCCESS.getCode(), CodeEnum.GET_USERINFOR_SUCCEDSS.getMsg());
            sendMessage(sendData,session);
        }else if (code== CodeEnum.UPDATE_ENVIRODATE.getCode()){//上传环境数据(设备自动上传的)
            //保存环境数据到数据库
            
            //转发给管理员
            String deviceNumber=msgResult.getMsgRoute().getFrom();
            Session sessionTo=clients.get("A"+deviceNumber.substring(1));
            if (sessionTo!=null){
                sendMessage(msgResult,sessionTo);
            }
        }
    }

    /**
 　　 * 发生错误时触发的方法
 　　*/
    @OnError
    public static void onError(Session session,Throwable error){
        log.info("发生错误："+session.getId()+"："+error.getMessage());
        Iterator<Map.Entry<String, Session>> clientMap = clients.entrySet().iterator();
        while (clientMap.hasNext()) {
            Map.Entry<String, Session> entry = clientMap.next();
            if (entry.getValue().equals(session)){
                clientMap.remove();
            }
        }
    }

    /**
     * 发送消息给客户端
     * @param msgResult
     * @return
     */
    private static void sendMessage(MsgResult msgResult,Session session)  {
        String sendString=JSON.toJSONString(msgResult);
        try {
            session.getBasicRemote().sendText(sendString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void sendMessage(String msg,Session session){
        try {
            session.getBasicRemote().sendText(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
