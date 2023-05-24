package com.ilovesshan.im.mapper;

import com.ilovesshan.im.model.po.Attachment;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2023/5/18
 * @description:
 */
@Mapper
public interface AttachmentMapper {
    int insert(Attachment attachment);
}
