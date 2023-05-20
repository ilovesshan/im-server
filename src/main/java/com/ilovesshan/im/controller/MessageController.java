package com.ilovesshan.im.controller;

import com.ilovesshan.im.core.config.UserCache;
import com.ilovesshan.im.core.utils.R;
import com.ilovesshan.im.model.dto.MessageDto;
import com.ilovesshan.im.model.vo.MessageVo;
import com.ilovesshan.im.model.vo.RecentlyMessageVo;
import com.ilovesshan.im.service.MessageService;
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

@Api(tags = "消息模块")
@RestController
@RequestMapping("/message")
public class MessageController {

    @Resource
    private MessageService messageService;

    @ApiOperation("向好友发送消息")
    @PostMapping("")
    public R sendMessage(@RequestBody MessageDto messageDto) {
        boolean isSuccess = messageService.sendMessage(messageDto);
        return isSuccess ? R.success(R.SUCCESS_MESSAGE) : R.fail(R.SUCCESS_MESSAGE);
    }

    @ApiOperation("查询指定好友聊天记录")
    @GetMapping("/{fid}")
    public R queryMessageList(@PathVariable("fid") long fid) {
        MessageVo messageVo = messageService.queryMessageList(Long.parseLong(UserCache.get("userId")), fid);
        return R.success(R.SUCCESS_MESSAGE_SELECT, messageVo);
    }

    @ApiOperation("查询与好友最近一条聊天记录")
    @GetMapping("/recently")
    public R queryRecentlyMessageList() {
        List<RecentlyMessageVo> recentlyMessageVoList = messageService.queryRecentlyMessageList(Long.parseLong(UserCache.get("userId")));
        return R.success(R.SUCCESS_MESSAGE_SELECT, recentlyMessageVoList);
    }

    @ApiOperation("撤回消息")
    @DeleteMapping("/{id}")
    public R withdrawMessage(@PathVariable("id") long id) {
        boolean isSuccess = messageService.withdrawMessage(id, Long.parseLong(UserCache.get("userId")));
        return isSuccess ? R.success(R.SUCCESS_MESSAGE) : R.fail(R.ERROR_MESSAGE);
    }
}
