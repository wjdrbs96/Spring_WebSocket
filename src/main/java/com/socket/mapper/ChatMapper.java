package com.socket.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * created by jg 2021/08/10
 */
@Mapper
public interface ChatMapper {

    @Insert("INSERT INTO chat (chat) VALUES (#{chat})")
    void saveChat(String message);
}
