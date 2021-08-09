package com.socket.message;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * created by jg 2021/08/09
 */
@Getter
@NoArgsConstructor
public class Greeting {
    private String content;

    public Greeting(String content) {
        this.content = content;
    }
}
