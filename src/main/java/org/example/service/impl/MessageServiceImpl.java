package org.example.service.impl;

import org.example.entity.Message;
import org.example.exception.UserContextHolder;
import org.example.mapper.MessageMapper;
import org.example.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;



    @Override
    public void insertMessage(Message message) {
        message.setUserId(UserContextHolder.getUserId());
        message.setContent(message.getContent());
        message.setCreateTime(LocalDateTime.now());
        messageMapper.insertMessage(message);
    }

    @Override
    public List<Message> selectAllMessage() {

        return messageMapper.selectAllMessage();
    }

    @Override
    public void replyMessage(Integer id, Message message) {
            message.setReplyContent(message.getReplyContent());
            message.setReplyTime(LocalDateTime.now());
            message.setId(id);
            messageMapper.replyMessage(message);
    }

    @Override
    public void deleteMessage(Integer id) {
        // 获取当前用户的ID
        // 只有管理员才可以删除留言
            messageMapper.deleteByIdMessage(id);
    }
}
