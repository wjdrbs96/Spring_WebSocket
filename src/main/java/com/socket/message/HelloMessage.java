package com.socket.message;

import lombok.Getter;

/**
 * created by jg 2021/08/09
 */
@Getter
public class HelloMessage {
    private String name;

    public HelloMessage(String name) {
        this.name = name;
    }

}
