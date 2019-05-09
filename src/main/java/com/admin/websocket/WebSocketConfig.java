package com.admin.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: hxl
 * \* Date: 2018/12/3
 * \* Time: 22:19
 * \* Description:
 * \
 */
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}