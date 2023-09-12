package com.ssafy.socket;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.Scanner;

public class WebSocketClient {

    private WebSocketStompClient stompClient;


    public static void main(String[] args) {
        // 서버의 웹소켓 URL
        String url = "ws://192.168.100.169:8080/websocket";

        // WebSocketStompClient 인스턴스 생성
        WebSocketStompClient stompClient = new WebSocketStompClient(new StandardWebSocketClient());

        // 메시지 변환기 설정 - JSON 형태의 메시지를 Java 객체로 변환할 수 있게 해줍니다.
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        // 사용자로부터 채널 ID를 입력받습니다.
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter channel ID: ");
        String channelId = scanner.nextLine();

        // 세션 핸들러 인스턴스 생성 - 웹소켓 연결 후의 동작을 정의합니다.
        MySessionHandler sessionHandler = new MySessionHandler(channelId);

        // 주어진 URL로 웹소켓 서버에 연결하고, 세션 핸들러를 등록하여 연결 후에 수행할 작업들을 설정합니다.
        stompClient.connect(url, sessionHandler);

        // 이 스레드는 메인 스레드가 종료되는 것을 막기 위해 무한 루프를 실행합니다.
        // 메인 스레드가 종료되면 JVM도 함께 종료되므로, 이 스레드는 프로그램이 계속 실행되게 만듭니다.
        // 실제 상황에서는 좀 더 안전하고 효율적인 방법으로 비동기 처리나 스레드 관리가 필요할 것입니다.
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {  // 예외가 발생하면 스택 트레이스를 출력합니다.
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
