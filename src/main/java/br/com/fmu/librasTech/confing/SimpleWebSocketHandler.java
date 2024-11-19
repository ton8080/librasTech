package br.com.fmu.librasTech.confing;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class SimpleWebSocketHandler extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        // Lógica para processar a mensagem recebida
        System.out.println("Mensagem recebida: " + message.getPayload());

        // Enviar uma mensagem de volta ao cliente
        try {
            // Enviar uma mensagem de volta com o status de disponibilidade
            session.sendMessage(new TextMessage("availabilityStatus:true"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
