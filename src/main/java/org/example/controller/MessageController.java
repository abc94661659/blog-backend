package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.Message;
import org.example.entity.Result;
import org.example.entity.User;
import org.example.exception.UserContextHolder;
import org.example.mapper.UserMapper;
import org.example.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/message-board")
@Slf4j
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserMapper userMapper;

    @PostMapping
    public Result insertMessage(@RequestBody Message message){
        log.info("{}",message);
        messageService.insertMessage(message);
        log.info("{}",message);
        return Result.success();
    }

    @GetMapping
    public Result selectAllMessage(){
        List<Message> list  = messageService.selectAllMessage();
        return Result.success(list);
    }

    @PutMapping("/{id}/reply")
    public Result replyMessage(@PathVariable Integer id,@RequestBody Message message){
        Integer userId = UserContextHolder.getUserId();
        User user = userMapper.selectById(userId);
        if(Objects.equals(user.getRole(), "ROLE_ADMIN")){
            messageService.replyMessage(id,message);
            return Result.success();
        }else{
            return Result.error("您没有权限发布留言");
        }
    }

    @DeleteMapping("/{id}")
    public Result deleteMessage(@PathVariable Integer id){
        Integer userId = UserContextHolder.getUserId();
        User user = userMapper.selectById(userId);
        if(Objects.equals(user.getRole(), "ROLE_ADMIN")){
            messageService.deleteMessage(id);
            return Result.success();
        }else{
            return Result.error("您没有权限删除留言");
        }
    }
}
