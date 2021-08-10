package com.socket.controller;

import com.socket.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * created by jg 2021/07/16
 */
@RequiredArgsConstructor
@Controller
public class ChatController {

    private final RedisTemplate<String, Object> redisTemplate;

    @MessageMapping("/chat.sendMessage/{idx}")
    @SendTo("/topic/public/{idx}")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage, @DestinationVariable String idx) {
        // Key: chatNumber1, Value: value
        redisTemplate.opsForValue().set("chatting1", chatMessage.getContent());
        System.out.println(redisTemplate.opsForValue().get("chatting1"));

        // Key: chatNumber1, Value: 채팅 List
        ListOperations<String, Object> listOperations = redisTemplate.opsForList();
        redisTemplate.opsForList().rightPush("chatNumber" + idx, chatMessage);
        RedisOperations<String, Object> operations = redisTemplate.opsForList().getOperations();
        System.out.println(operations.opsForList().range("chatNumber" + idx, 0, -1));

        // Key: chatNumber1, Value: 채팅 Set
        SetOperations<String, Object> setOperations = redisTemplate.opsForSet();
        setOperations.add("Key", chatMessage);
        System.out.println(setOperations.members("Key"));

        // Key chatNumber1, Value: 채팅 SortedSet
        ZSetOperations<String, Object> zSetOperations = redisTemplate.opsForZSet();
        zSetOperations.add("ZKey", chatMessage, 10);
        zSetOperations.add("ZKey", chatMessage, 5);
        zSetOperations.add("ZKey", chatMessage, 7);
        System.out.println(zSetOperations.range("ZKey", 0, -1));

         // Key: chatNumber1, Value: 채팅 Map
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        Map<String, Object> map = new HashMap<>();
        map.put("firstName", "Gyunny");
        map.put("lastName", "Choi");
        map.put("gender", "Man");
        hashOperations.putAll("key", map);

        String firstName = (String) redisTemplate.opsForHash().get("key", "firstName");
        String lastName = (String) redisTemplate.opsForHash().get("key", "lastName");
        String gender = (String) redisTemplate.opsForHash().get("key", "gender");
        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(gender);

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
