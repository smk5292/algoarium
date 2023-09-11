package com.ssafy.socket;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WebSocketClient {

    private WebSocketStompClient stompClient;

    public void start() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // UI 요소 생성
                ImageIcon starIcon = new ImageIcon(getClass().getResource("/star.png")); // star.png 이미지 추가
                Image starImage = starIcon.getImage().getScaledInstance(starIcon.getIconWidth() / 2, starIcon.getIconHeight() / 2, Image.SCALE_SMOOTH);
                ImageIcon smallStarIcon = new ImageIcon(starImage);
                JLabel starLabel = new JLabel(smallStarIcon); // star.png 이미지 추가

                JTextField channelIdField = new JTextField(23); // 채널 ID 입력 필드
                Dimension fieldPreferredSize = channelIdField.getPreferredSize();
                fieldPreferredSize.height *= 1.9; // 상하 길이를 두 배로 늘리기
                channelIdField.setPreferredSize(fieldPreferredSize);

                JButton connectButton = new JButton("로그인"); // 연결 버튼
                connectButton.setMaximumSize(channelIdField.getPreferredSize());
                connectButton.setMinimumSize(channelIdField.getPreferredSize());
                connectButton.setPreferredSize(channelIdField.getPreferredSize());


                // 버튼의 크기를 1.1배로 늘리기
                Dimension buttonPreferredSize = connectButton.getPreferredSize();
                buttonPreferredSize.height *= 20;
                connectButton.setPreferredSize(buttonPreferredSize);



                // 연결 버튼 이전에 채널 ID 입력 필드 추가
                JPanel panel = new JPanel(); // 패널 생성


                GroupLayout layout = new GroupLayout(panel);
                panel.setLayout(layout);

                layout.setAutoCreateGaps(true); // 자동 간격 생성
                layout.setAutoCreateContainerGaps(true); // 컨테이너 간격 자동 생성

                layout.setHorizontalGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(starLabel)
                                .addComponent(channelIdField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(connectButton)
                );

                layout.setVerticalGroup(
                        layout.createSequentialGroup()
                                .addComponent(starLabel)
                                .addComponent(channelIdField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(connectButton)
                );

                connectButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String url = "ws://192.168.100.169:8080/websocket"; // 서버 WebSocket URL

                        stompClient = new WebSocketStompClient(new StandardWebSocketClient()); // WebSocket 클라이언트 생성
                        stompClient.setMessageConverter(new MappingJackson2MessageConverter()); // JSON 메시지 변환기 설정

                        String channelId = channelIdField.getText(); // 입력한 채널 ID 가져오기
                        MySessionHandler sessionHandler = new MySessionHandler(channelId); // 세션 핸들러 생성
                        stompClient.connect(url, sessionHandler); // 서버에 연결
                    }
                });

                JFrame frame = new JFrame("Algoarium"); // 프레임 생성
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 닫기 버튼 동작 설정
                frame.getContentPane().add(panel); // 패널을 프레임에 추가
                frame.pack(); // 크기 자동 조절
                frame.setVisible(true); // 화면에 표시
                frame.setLocationRelativeTo(null); // 화면 중앙에 표시
                ImageIcon icon = new ImageIcon(getClass().getResource("/star.png")); // resources 폴더 안에 있는 이미지 사용
                frame.setIconImage(icon.getImage()); // 프레임 아이콘 설정

                frame.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                        if (stompClient != null) {
                            stompClient.stop(); // 프로그램 종료 시 WebSocket 클라이언트 정지
                        }
                        System.exit(0); // 시스템 종료
                    }
                });
            }
        });
    }

    public static void main(String[] args) {
        WebSocketClient client = new WebSocketClient(); // WebSocketClient 객체 생성
        client.start(); // 클라이언트 시작
    }
}
