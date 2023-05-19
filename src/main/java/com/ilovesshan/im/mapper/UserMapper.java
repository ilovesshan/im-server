package com.ilovesshan.im.mapper;

import com.ilovesshan.im.model.po.User;
import com.ilovesshan.im.model.vo.FriendVo;
import com.ilovesshan.im.model.vo.UserVo;
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
public interface UserMapper {
    User selectUserByNameAndPwd(@Param("username") String username, @Param("password") String password);

    List<FriendVo> selectByIdOrUsername(String kw);

    UserVo selectByIds(long id);
}
