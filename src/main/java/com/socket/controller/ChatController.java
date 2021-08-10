package com.socket.controller;

import com.socket.model.ChatMessage;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * created by jg 2021/07/16
 */
@Controller
public class ChatController {

    @MessageMapping("/chat.sendMessage/{idx}")
    @SendTo("/topic/public/{idx}")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage, @DestinationVariable String idx) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser/{idx}")
    @SendTo("/topic/public/{idx}")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor, @DestinationVariable String idx){
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

    @GetMapping("/test")
    public String test() {
        return "test.html";
    }

}
