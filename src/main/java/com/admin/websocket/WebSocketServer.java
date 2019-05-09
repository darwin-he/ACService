package com.admin.websocket;

import com.admin.service.UserInfService;
import com.admin.utils.CommonResult;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: hxl
 * \* Date: 2018/12/3
 * \* Time: 22:24
 * \* Description:
 * \
 */


@ServerEndpoint(value = "/websocket")
@Component
public class WebSocketServer {
    
    @Autowired
    private UserInfService userService;
    //日志工具
    private static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);
    
    //硬件设备
    private static Session clientSession;
    
    //管理员网页客服端
    private static Session adminClient;

    /**
 　　* 连接建立成功时方法
 　　 */
    @OnOpen
    public static void onOpen(Session session){
        clientSession = session;
        log.info("onOpen"+session.getId());
    }
    
    /**
     * 连接断开时调用的方法
     */
    @OnClose
    public static void onClose(Session session) {
        log.info("客户端断开连接："+clientSession.getId());
        clientSession=null;
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public static void onMessage(String message, Session session) {
        log.info("收到客服端消息:" + message);
        CommonResult commonResult= JSON.parseObject(message).toJavaObject(CommonResult.class);
        if (commonResult.getCode()==DeviceCodeEnum.GET_USERINFOR.getCode()){
            CommonResult sendData=new CommonResult(DeviceCodeEnum.GET_USERINFOR_SUCCEDSS.getCode(),DeviceCodeEnum.GET_USERINFOR_SUCCEDSS.getMsg());
            String sendString=JSON.toJSONString(sendData);
            sendMessage(sendString);
        }
    }

    /**
 　　 * 发生错误时触发的方法
 　　*/
    @OnError
    public static void onError(Session session,Throwable error){
        log.info("发生错误："+session.getId()+"："+error.getMessage());
        error.printStackTrace();
    }

    /**
     * 发送消息给客户端
     * @param message
     * @return
     */
    public static Object sendMessage(String message)  {
        if (clientSession==null)
            return new CommonResult(-1,"设备未连接",message);
        try {
            clientSession.getBasicRemote().sendText(message);
            return new CommonResult(-1,"指令发送成功",message);
        } catch (Exception e) {
            return new CommonResult(-1,e.toString(),message);
        }
    }
    
}
