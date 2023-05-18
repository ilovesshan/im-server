package com.ilovesshan.im.controller;

import com.ilovesshan.im.core.config.UserCache;
import com.ilovesshan.im.core.utils.R;
import com.ilovesshan.im.model.vo.FriendVo;
import com.ilovesshan.im.service.FriendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("查询好友")
    @GetMapping("/{kw}")
    public R queryFriend(@PathVariable("kw") String kw) {
        FriendVo friendVo = friendService.queryFriend(kw);
        return R.success(R.SUCCESS_MESSAGE_SELECT, friendVo);
    }

    @ApiOperation("添加好友")
    @PostMapping("/{fid}")
    public R addFriend(@PathVariable("fid") long fid) {
        boolean isSuccess = friendService.addFriend(Long.parseLong(UserCache.get("userId")), fid);
        return isSuccess ? R.success(R.SUCCESS_MESSAGE) : R.fail(R.SUCCESS_MESSAGE);
    }

    @ApiOperation("查询好友申请列表")
    @PostMapping("/apply")
    public R addFriend() {
        List<FriendVo> friendVoList = friendService.queryApplyList(Long.parseLong(UserCache.get("userId")));
        return R.success(R.SUCCESS_MESSAGE_SELECT, friendVoList);
    }

    @ApiOperation("同意/拒绝好友申请")
    @PutMapping("/apply/{type}/{fid}")
    public R agreeOrRefuseFriendApply(@PathVariable("type") String type, @PathVariable("fid") long fid) {
        boolean isSuccess = friendService.agreeOrRefuseFriendApply(type, Long.parseLong(UserCache.get("userId")), fid);
        return isSuccess ? R.success(R.SUCCESS_MESSAGE) : R.fail(R.SUCCESS_MESSAGE);
    }
}
