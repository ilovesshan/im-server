package com.ilovesshan.im.mapper;

import com.ilovesshan.im.model.po.Apply;
import com.ilovesshan.im.model.vo.FriendVo;
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
public interface ApplyMapper {
    int insert(@Param("uid") long uid, @Param("tid") long fid);

    Apply selectByUidAndTid(@Param("uid") long uid, @Param("tid") long fid);

    List<FriendVo> selectByTid(long userId);

    int deleteByUidAndTid(@Param("uid") long uid, @Param("tid") long fid);
}
