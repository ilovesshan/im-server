package com.ilovesshan.im.mapper;

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
public interface FriendMapper {
    List<FriendVo> selectByUid(long id);

    void insert(@Param("uid") long userId, @Param("fid")long fid);
}
