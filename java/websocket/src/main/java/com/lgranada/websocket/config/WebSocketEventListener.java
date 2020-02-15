package com.lgranada.websocket.config;

import com.lgranada.websocket.model.ChatMessage;
import com.lgranada.websocket.model.MessageType;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Log4j2
@Component
public class WebSocketEventListener {
    private final SimpMessageSendingOperations sendingOperations;

    public WebSocketEventListener(SimpMessageSendingOperations sendingOperations) {
        this.sendingOperations = sendingOperations;
    }

    @EventListener
    public void handleWebSocketConnectListener(final SessionConnectedEvent event) {
        log.info("----New user connected ----", event);
        System.out.println(event.getSource());
        System.out.println(new String(event.getMessage().getPayload()));
        event.getMessage().getHeaders().forEach((key,value)-> System.out.println(key + " " + value));
        System.out.println(event.getUser());
    }

    @EventListener
    public void handleWebSocketDisnnectListenner(final SessionDisconnectEvent event) {
        final StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        final String userName = (String) headerAccessor.getSessionAttributes().get("username");
        log.info("User " + userName + " disconnected", event);
        final ChatMessage chatMessage = ChatMessage.builder()
                .type(MessageType.DISCONNECT)
                .sender(userName)
                .build();
        sendingOperations.convertAndSend("/topic/public", chatMessage);
    }
}
