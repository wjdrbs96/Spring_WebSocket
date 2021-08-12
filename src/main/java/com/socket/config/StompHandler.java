package com.socket.config;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

/**
 * created by jg 2021/08/13
 */
@Component
public class StompHandler implements ChannelInterceptor {

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        SimpMessageHeaderAccessor simpMessageHeaderAccessor = StompHeaderAccessor.create(SimpMessageType.UNSUBSCRIBE);
        System.out.println(simpMessageHeaderAccessor);
        System.out.println("-----");
        return message;
    }

    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        String sessionId = accessor.getSessionId();
        System.out.println("============");
        System.out.println(accessor.getCommand());  // CONNECT, DISCONNECT
        System.out.println(sessionId);
        System.out.println("=============");
    }

}
