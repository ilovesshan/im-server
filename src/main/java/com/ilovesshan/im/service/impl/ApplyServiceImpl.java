package com.ilovesshan.im.service.impl;

import com.ilovesshan.im.core.exception.ImException;
import com.ilovesshan.im.mapper.ApplyMapper;
import com.ilovesshan.im.model.po.Apply;
import com.ilovesshan.im.service.ApplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

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

    @Override
    public boolean addFriend(long uid, long fid) {
        if (uid == fid) {
            throw new ImException("不能添加自己为好友");
        }

        Apply apply = applyMapper.selectByUidAndTid(uid, fid);
        if (Objects.nonNull(apply)) {
            throw new ImException("你们已经是好友啦");
        }

        return applyMapper.insert(uid, fid) > 0;
    }
}
