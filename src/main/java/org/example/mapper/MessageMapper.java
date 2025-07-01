package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.entity.Message;

import java.util.List;

@Mapper
public interface MessageMapper {
    void insertMessage(Message message);

    List<Message> selectAllMessage();

    void replyMessage(Message message);


    void deleteByIdMessage(Integer id);

    void deleteByUserId(Integer id);
}
