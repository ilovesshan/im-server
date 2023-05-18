package com.ilovesshan.im.service.impl;

import com.ilovesshan.im.mapper.FriendMapper;
import com.ilovesshan.im.model.vo.FriendVo;
import com.ilovesshan.im.service.ApplyService;
import com.ilovesshan.im.service.FriendService;
import com.ilovesshan.im.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2023/5/18
 * @description:
 */

@Service
public class FriendServiceImpl implements FriendService {
    @Resource
    private ApplyService applyService;

    @Resource
    private UserService userService;

    @Resource
    private FriendMapper friendMapper;


    @Override
    public List<FriendVo> queryFriendList(long id) {
        return friendMapper.selectByUid(id);
    }

    @Override
    public boolean addFriend(long uid, long fid) {
        return applyService.addFriend(uid, fid);
    }

    @Override
    public FriendVo queryFriend(String kw) {
        return userService.queryUser(kw);
    }
}
