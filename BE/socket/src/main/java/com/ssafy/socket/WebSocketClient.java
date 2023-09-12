package com.ssafy.socket;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class WebSocketClient {

    private WebSocketStompClient stompClient;
    private static final String LOCK_FILE = "websocket.lock";
    private static Path lockFilePath = Paths.get(LOCK_FILE);

    public void start() {
        if (isAppAlreadyRunning()) {
            System.out.println("The application is already running.");
            return;
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                // 백그라운드 이미지 추가
                ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/see.jpg"));
                Image backgroundImage = backgroundIcon.getImage().getScaledInstance(375, 595, Image.SCALE_SMOOTH);
                ImageIcon smallBackgroundIcon = new ImageIcon(backgroundImage);
                JLabel backgroundLabel = new JLabel(smallBackgroundIcon);

                // UI 요소 생성
                ImageIcon starIcon = new ImageIcon(getClass().getResource("/star.png")); // star.png 이미지 추가
                Image starImage = starIcon.getImage().getScaledInstance(starIcon.getIconWidth() / 2, starIcon.getIconHeight() / 2, Image.SCALE_SMOOTH);
                ImageIcon smallStarIcon = new ImageIcon(starImage);
                JLabel starLabel = new JLabel(smallStarIcon); // star.png 이미지 추가


                JTextField channelIdField = new JTextField(13); // 채널 ID 입력 필드
                Dimension fieldPreferredSize = channelIdField.getPreferredSize();
                fieldPreferredSize.height *= 1.9; // 상하 길이를 두 배로 늘리기
                channelIdField.setPreferredSize(fieldPreferredSize);
                channelIdField.setFont(new Font("Arial", Font.PLAIN, 18)); // 폰트 설정



                JButton connectButton = new JButton("Connect"); // 연결 버튼
                connectButton.setMaximumSize(channelIdField.getPreferredSize());
                connectButton.setMinimumSize(channelIdField.getPreferredSize());
                connectButton.setPreferredSize(channelIdField.getPreferredSize());

                JButton exitButton = new JButton("Exit"); // 프로세스 종료 버튼
                exitButton.setMaximumSize(channelIdField.getPreferredSize());
                exitButton.setMinimumSize(channelIdField.getPreferredSize());
                exitButton.setPreferredSize(channelIdField.getPreferredSize());

// exitButton을 눌렀을 때의 동작 설정
                exitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // 프로그램 종료 시 WebSocket 클라이언트 정지
                        if (stompClient != null) {
                            stompClient.stop();
                        }
                        System.exit(0); // 시스템 종료
                    }
                });

                // channelIdField에 대한 텍스트 가운데 정렬 설정
                channelIdField.setHorizontalAlignment(JTextField.CENTER);



                // 버튼의 크기를 1.1배로 늘리기
                Dimension buttonPreferredSize = connectButton.getPreferredSize();
                buttonPreferredSize.height *= 20;
                connectButton.setPreferredSize(buttonPreferredSize);

                // 연결 버튼 이전에 채널 ID 입력 필드 추가
                JPanel panel = new JPanel(); // 패널 생성
                panel.setOpaque(false); // 패널을 투명하게 설정

                GroupLayout layout = new GroupLayout(panel);
                panel.setLayout(layout);

                layout.setAutoCreateGaps(true); // 자동 간격 생성
                layout.setAutoCreateContainerGaps(true); // 컨테이너 간격 자동 생성

                layout.setHorizontalGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(starLabel)
                                .addComponent(channelIdField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(connectButton)
                                .addComponent(exitButton)

                );

                layout.setVerticalGroup(
                        layout.createSequentialGroup()
                                .addGap(50)
                                .addComponent(starLabel)
                                .addGap(20)
                                .addComponent(channelIdField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(connectButton)
                                .addGap(40)
                                .addComponent(exitButton)
                                .addComponent(exitButton)

                );
                JFrame frame = new JFrame("Algoarium"); // 프레임 생성
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // 프레임 닫기 버튼 동작 설정


                // 시스템 트레이 설정
                if (SystemTray.isSupported()) {
                    SystemTray tray = SystemTray.getSystemTray();
//                    Image image = Toolkit.getDefaultToolkit().getImage("/arrow.png"); // 아이콘 이미지 경로 지정

                    ActionListener showAction = new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            frame.setVisible(true);
                            frame.setExtendedState(JFrame.NORMAL);
                        }
                    };

                    PopupMenu popup = new PopupMenu();
                    MenuItem showItem = new MenuItem("Show");
                    showItem.addActionListener(showAction);
                    popup.add(showItem);

                    MenuItem exitItem = new MenuItem("Exit");
                    exitItem.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (stompClient != null) {
                                stompClient.stop(); // WebSocket 클라이언트 정지
                            }
                            System.exit(0); // 시스템 종료
                        }
                    });


                    popup.add(exitItem);
                    ImageIcon icon = new ImageIcon(getClass().getResource("/star2.png"));
                    Image image = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
                    TrayIcon trayIcon = new TrayIcon(image, "Algoarium", popup);

                    trayIcon.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            if (e.getButton() == MouseEvent.BUTTON1) { // BUTTON1은 좌클릭을 의미합니다.
                                frame.setVisible(true);
                                frame.setExtendedState(JFrame.NORMAL);
                            }
                        }
                    });


                    try {
                        tray.add(trayIcon);
                    } catch (AWTException e) {
                        e.printStackTrace();
                    }

                    // 프레임을 아이콘화할 때 처리할 이벤트
                    frame.addWindowListener(new WindowAdapter() {
                        public void windowIconified(WindowEvent e) {
                            try {
                                tray.add(trayIcon); // 시스템 트레이에 아이콘 추가
                                frame.setVisible(false); // 프레임 숨기기
                            } catch (AWTException ex) {
                                ex.printStackTrace();
                            }
                        }

                        public void windowClosing(WindowEvent e) {
                            // 창이 닫히는 이벤트를 처리합니다.
                            frame.setVisible(false);
                        }


                    });
                }

                // Connect 버튼 ActionListener 내부에서의 코드
                connectButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // 실행 창 숨기기
                        frame.setVisible(false);

                        // 백그라운드 스레드에서 작업 수행
                        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                            @Override
                            protected Void doInBackground() throws Exception {
                                String url = "ws://192.168.100.169:8080/websocket";

                                stompClient = new WebSocketStompClient(new StandardWebSocketClient());
                                stompClient.setMessageConverter(new MappingJackson2MessageConverter());

                                String channelId = channelIdField.getText();
                                MySessionHandler sessionHandler = new MySessionHandler(channelId);

                                channelIdField.setVisible(false);
                                connectButton.setEnabled(false);
                                connectButton.setText("Connected!");

                                stompClient.connect(url, sessionHandler, new StompSessionHandlerAdapter() {
                                    @Override
                                    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
                                        SwingUtilities.invokeLater(() -> {
                                            connectButton.setEnabled(true);
                                            connectButton.setText("Connect");
                                        });

                                        // 연결 완료 후 필요한 작업 수행
                                        // ...
                                    }
                                });
                                return null;
                            }
                        };

                        worker.execute();
                    }
                });





                // 백그라운드 이미지 추가
                frame.setContentPane(new JLabel(backgroundIcon));
                frame.setLayout(new FlowLayout());

                frame.setIconImage(starImage); // 프레임 아이콘 설정

                frame.getContentPane().add(panel); // 패널을 프레임에 추가
                frame.pack(); // 크기 자동 조절
                frame.setVisible(true); // 화면에 표시
                frame.setLocationRelativeTo(null); // 화면 중앙에 표시

                // 버튼 폰트 설정
                Font buttonFont = new Font("Arial", Font.BOLD, 18);
                connectButton.setFont(buttonFont);
                exitButton.setFont(buttonFont);

                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        // 창을 닫았을 때의 동작 변경: 아이콘화하고 프레임 숨기기
                        frame.setExtendedState(JFrame.ICONIFIED);
                        frame.setVisible(false);
                    }
                });

            }
        });
    }
    private static boolean isAppAlreadyRunning() {
        try {
            FileChannel channel = FileChannel.open(lockFilePath, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            FileLock lock = channel.tryLock();
            if (lock == null) {
                channel.close();
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void main(String[] args) {
        WebSocketClient client = new WebSocketClient(); // WebSocketClient 객체 생성
        client.start(); // 클라이언트 시작
    }
}
