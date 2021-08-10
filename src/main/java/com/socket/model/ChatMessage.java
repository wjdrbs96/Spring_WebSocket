package com.socket.model;

import lombok.Getter;
import lombok.ToString;

/**
 * created by jg 2021/07/16
 */
@ToString
@Getter
public class ChatMessage {

    private Long id;
    private MessageType type;
    private String content;
    private String sender;
}
