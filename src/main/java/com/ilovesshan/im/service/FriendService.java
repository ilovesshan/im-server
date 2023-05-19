package com.ilovesshan.im.service;

import com.ilovesshan.im.model.vo.FriendVo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2023/5/18
 * @description:
 */
public interface FriendService {
    List<FriendVo> queryFriendList(long id);

    boolean addFriend(long uid, long fid);

    List<FriendVo>  queryFriend(String kw);

    List<FriendVo> queryApplyList(long userId);

    boolean agreeOrRefuseFriendApply(String type, long userId, long fid);
}
