package com.ilovesshan.im.controller;

import com.ilovesshan.im.core.config.UserCache;
import com.ilovesshan.im.core.utils.R;
import com.ilovesshan.im.model.vo.FriendVo;
import com.ilovesshan.im.service.FriendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2023/5/18
 * @description:
 */

@Api(tags = "好友模块")
@RestController
@RequestMapping("/friend")
public class FriendController {

    @Resource
    private FriendService friendService;

    @ApiOperation("查询好友列表")
    @GetMapping("")
    public R queryFriendList() {
        List<FriendVo> friendVoList = friendService.queryFriendList(Long.parseLong(UserCache.get("userId")));
        return R.success(R.SUCCESS_MESSAGE_SELECT, friendVoList);
    }
}