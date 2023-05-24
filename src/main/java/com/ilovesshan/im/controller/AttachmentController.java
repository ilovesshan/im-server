package com.ilovesshan.im.controller;

import com.ilovesshan.im.core.utils.R;
import com.ilovesshan.im.model.po.Attachment;
import com.ilovesshan.im.service.AttachmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2023/5/18
 * @description:
 */

@Api(tags = "附件模块")
@RestController
@RequestMapping("/attachment")
public class AttachmentController {

    @Resource
    private AttachmentService attachmentService;

    @ApiOperation("附件上传")
    @PostMapping("")
    public R login(@RequestParam("file") MultipartFile multipartFile) {
        Attachment attachment = attachmentService.upload(multipartFile);
        return R.success(R.SUCCESS_ATTACHMENT_UPLOAD, attachment);
    }
}
