package com.ilovesshan.im.service.impl;

import com.ilovesshan.im.core.config.UserCache;
import com.ilovesshan.im.core.exception.ImException;
import com.ilovesshan.im.mapper.MessageMapper;
import com.ilovesshan.im.model.dto.MessageDto;
import com.ilovesshan.im.model.po.Message;
import com.ilovesshan.im.model.vo.MessageVo;
import com.ilovesshan.im.model.vo.UserVo;
import com.ilovesshan.im.service.MessageService;
import com.ilovesshan.im.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2023/5/18
 * @description:
 */

@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    private UserService userService;


    @Resource
    private MessageMapper messageMapper;


    @Override
    public boolean sendMessage(MessageDto messageDto) {
        Message message = new Message();
        BeanUtils.copyProperties(messageDto, message);
        message.setTime(new Date());
        message.setFrom(Long.parseLong(UserCache.get("userId")));
        return messageMapper.insert(message) > 0;
    }

    @Override
    public MessageVo queryMessageList(long userId, long fid) {

        if (userId == fid) {
            throw new ImException("聊天记录查询失败，不能查询自己和自己的聊天记录");
        }

        MessageVo messageVo = new MessageVo();

        // 查询用户基本信息
        UserVo mUser = userService.queryUserById(userId);
        UserVo yUser = userService.queryUserById(fid);
        if (Objects.isNull(mUser) || Objects.isNull(yUser)) {
            throw new ImException("聊天记录查询失败，非法用户信息");
        }

        // 查询聊天列表
        List<Message> messageList = messageMapper.selectList(userId, fid);

        messageVo.setMUser(mUser);
        messageVo.setYUser(yUser);
        messageVo.setMessage(messageList);

        return messageVo;
    }

    @Override
    public boolean withdrawMessage(long messageId, long fromUserId) {
        Message message = messageMapper.selectById(messageId);
        if (message.getFrom() != fromUserId) {
            throw new ImException("仅支持撤回自己发出的消息");
        }

        // 撤回消息仅支持1分钟以内的消息
        if (!new Date().before(new Date(message.getTime().getTime() + 1000 * 60))) {
            throw new ImException("仅支持撤回1分钟以内的消息");
        }
        return messageMapper.deleteByIdAndFromId(messageId, fromUserId) > 0;
    }
}
