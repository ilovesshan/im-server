package com.ilovesshan.im.service.impl;

import com.ilovesshan.im.core.exception.ImException;
import com.ilovesshan.im.mapper.ApplyMapper;
import com.ilovesshan.im.mapper.FriendMapper;
import com.ilovesshan.im.model.po.Apply;
import com.ilovesshan.im.model.vo.FriendVo;
import com.ilovesshan.im.service.ApplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2023/5/18
 * @description:
 */

@Service
public class ApplyServiceImpl implements ApplyService {

    @Resource
    private ApplyMapper applyMapper;

    @Resource
    private FriendMapper friendMapper;


    @Override
    public boolean addFriend(long uid, long fid) {
        if (uid == fid) {
            throw new ImException("不能添加自己为好友");
        }

        List<Long> friendIds = friendMapper.selectByUid(uid).stream().map(friendVo -> friendVo.getId()).collect(Collectors.toList());
        if (friendIds.contains(fid)) {
            throw new ImException("对方已经是你的好友了");
        }

        Apply apply = applyMapper.selectByUidAndTid(uid, fid);
        if (Objects.nonNull(apply)) {
            throw new ImException("请求已发送，等待对方同意");
        }

        return applyMapper.insert(uid, fid) > 0;
    }

    @Override
    public List<FriendVo> queryApplyList(long userId) {
        return applyMapper.selectByTid(userId);
    }


    @Override
    public int deleteByUidAndTid(long userId, long fid) {
        return applyMapper.deleteByUidAndTid(userId, fid);
    }
}
