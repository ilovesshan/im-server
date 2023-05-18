package com.ilovesshan.im.mapper;

import com.ilovesshan.im.model.po.User;
import com.ilovesshan.im.model.vo.FriendVo;
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
public interface UserMapper {
    User selectUserByNameAndPwd(@Param("username") String username, @Param("password") String password);

    FriendVo selectByIdOrUsername(String kw);
}
