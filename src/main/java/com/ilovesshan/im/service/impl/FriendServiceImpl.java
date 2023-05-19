package com.ilovesshan.im.service.impl;

import com.ilovesshan.im.core.exception.ImException;
import com.ilovesshan.im.mapper.FriendMapper;
import com.ilovesshan.im.model.vo.FriendVo;
import com.ilovesshan.im.service.ApplyService;
import com.ilovesshan.im.service.FriendService;
import com.ilovesshan.im.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public  List<FriendVo>  queryFriend(String kw) {
        return userService.queryUser(kw);
    }

    @Override
    public List<FriendVo> queryApplyList(long userId) {
        return applyService.queryApplyList(userId);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean agreeOrRefuseFriendApply(String type, long userId, long fid) {
        if ("1".equals(type)) {
            // 同意
            // 1、向friend表插入数据
            friendMapper.insert(userId, fid);
            friendMapper.insert(fid, userId);

            // 2、删除apply表记录
            applyService.deleteByUidAndTid(userId, fid);

        } else if ("2".equals(type)) {
            // 拒绝
            // 1、删除apply表记录
            applyService.deleteByUidAndTid(userId, fid);
        } else {
            throw new ImException("无效的请求type类型");
        }
        return true;
    }
}
