package com.ilovesshan.im.service;

import com.ilovesshan.im.model.dto.MessageDto;
import com.ilovesshan.im.model.vo.MessageVo;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2023/5/18
 * @description:
 */
public interface MessageService {
    boolean sendMessage(MessageDto messageDto);

    MessageVo queryMessageList(long userId, long fid);

    boolean withdrawMessage(long messageId, long fromUserId);
}
