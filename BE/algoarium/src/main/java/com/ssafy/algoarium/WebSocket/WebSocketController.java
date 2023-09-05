package com.ssafy.algoarium.WebSocket;

import org.json.JSONObject;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/sendCommand")
    public void sendCommand(String command) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("openUrl", command);

        messagingTemplate.convertAndSend("/topic/command", jsonObject.toString());
    }
}
