// "/app" prefix 경로로 들어오는 요청은 애플리케이션 내부의 Controller method가 처리하고,
// 애플리케이션이 "/topic" 경로 밑으로 데이터를 publish하면 그것들은 해당 topic에 subscribe한 클라이언트들에게 전달
package com.ssafy.algoarium.WebSocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
// 메시지 브로커 기반의 WebSocket 메시징을 활성화합니다.
// 메시지 브로커는 클라이언트와 서버 간에 메시지를 라우팅하는 역할을 합니다.
@EnableWebSocketMessageBroker
//  WebSocket 메시지 브로커 관련 구성 옵션을 제공
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    // 메시지 브로커에 대한 구성을 정의합니다.
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // "/topic" prefix를 가진 목적지에 대해 Simple message broker를 활성화합니다.
        // 클라이언트가 이 주소로 메시지를 구독(subscribe)하면, 서버에서 해당 주소로 보낼 수 있는 메시지들을 받게 됩니다.
        config.enableSimpleBroker("/topic");
        // "/app" prefix가 붙은 목적지들에 대한 요청은 @MessageMapping 어노테이션이 붙은 Controller method들에 의해 처리됩니다.
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    // STOMP 프로토콜 엔드포인트를 등록합니다.
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // "/command" 경로에 STOMP endpoint를 추가하고, 모든 출처('*')에서 오는 요청을 허용하며, SockJS fallback options도 활성화 합니다.
        // SockJS는 WebSocket이 지원되지 않는 경우 fallback 옵션으로 다른 방식으로 통신할 수 있게 도와주는 JavaScript 라이브러리입니다.
        registry.addEndpoint("/command").setAllowedOrigins("*").withSockJS();
    }

}
