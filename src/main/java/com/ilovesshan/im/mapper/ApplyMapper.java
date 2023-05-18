package com.ilovesshan.im.mapper;

import com.ilovesshan.im.model.po.Apply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2023/5/18
 * @description:
 */

@Mapper
public interface ApplyMapper {
    int insert(@Param("uid") long uid, @Param("tid") long fid);

    Apply selectByUidAndTid(@Param("uid") long uid, @Param("tid") long fid);
}
