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
public interface ApplyService {
    boolean addFriend(long uid, long fid);

    List<FriendVo> queryApplyList(long userId);

    int deleteByUidAndTid(long userId, long fid);
}
