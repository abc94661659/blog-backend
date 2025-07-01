package org.example.service;

import org.example.entity.Message;

import java.util.List;

public interface MessageService {
    void insertMessage(Message message);

    List<Message> selectAllMessage();

    void replyMessage(Integer id, Message message);

    void deleteMessage(Integer id);
}
