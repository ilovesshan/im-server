package com.ilovesshan.im.mapper;

import com.ilovesshan.im.model.po.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2023/5/18
 * @description:
 */

@Mapper
public interface MessageMapper {
    int insert(Message message);

    List<Message> selectList(@Param("uid") long userId, @Param("fid")long fid);

    Message selectById(long id);

    int deleteByIdAndFromId(@Param("messageId") long messageId, @Param("fromUserId")long fromUserId);
}
