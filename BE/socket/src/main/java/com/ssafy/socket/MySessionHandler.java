package com.ssafy.socket;

import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.awt.*;
import java.lang.reflect.Type;
import java.net.URI;

class MySessionHandler extends StompSessionHandlerAdapter {
    // 웹소켓 연결이 성공하면 호출되는 메서드
    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        // "/topic/url" 주제를 구독하고 메시지가 도착하면 처리할 핸들러를 등록합니다.
        session.subscribe("/topic/url", new StompFrameHandler() {
            // 수신된 메시지의 payload 타입을 반환합니다. 여기서는 Url 클래스로 지정되어 있습니다.
            @Override
            public Type getPayloadType(StompHeaders headers) {
                return Url.class;
            }

            // 수신된 메시지를 처리하는 메서드입니다.
            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                Url url = (Url) payload;  // 수신된 메시지의 payload를 Url 객체로 변환합니다.
                System.out.println("Received url: " + url.getContent());  // 콘솔에 수신한 URL을 출력합니다.

                // 시스템의 기본 웹 브라우저에서 URL을 엽니다.
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    try {
                        Desktop.getDesktop().browse(new URI(url.getContent()));
                    } catch (Exception e) {  // 예외가 발생하면 스택 트레이스를 출력합니다.
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
