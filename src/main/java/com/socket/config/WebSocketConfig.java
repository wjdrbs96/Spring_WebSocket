package com.socket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * created by jg 2021/07/16
 */
@Configuration
@EnableWebSocketMessageBroker  // WebSocket 서버를 활성화하는 데 사용
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // 한 클라이언트에서 다른 클라이언트로 메시지를 라우팅 하는 데 사용될 메시지 브로커를 구성
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 클라이언트로 메세지를 응답해줄 때 prefix 정의
        registry.enableSimpleBroker("/topic");
        // 클라이언트에서 메세지 송신시 붙여줄 prefix 정의
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 최초 소켓을 연결하는 경우, endpoint가 되는 url
        registry.addEndpoint("/ws").withSockJS();
    }

}
