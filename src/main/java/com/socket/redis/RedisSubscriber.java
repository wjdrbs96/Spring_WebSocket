package com.socket.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * created by jg 2021/08/10
 */
@Slf4j
@Component
public class RedisSubscriber implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            System.out.println("Message Received: " + message);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
