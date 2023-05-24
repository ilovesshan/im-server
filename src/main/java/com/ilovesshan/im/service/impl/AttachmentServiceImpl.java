package com.ilovesshan.im.service.impl;

import com.ilovesshan.im.core.exception.ImException;
import com.ilovesshan.im.core.utils.R;
import com.ilovesshan.im.mapper.AttachmentMapper;
import com.ilovesshan.im.model.po.Attachment;
import com.ilovesshan.im.service.AttachmentService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2023/5/18
 * @description:
 */

@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Resource
    private AttachmentMapper attachmentMapper;

    @Override
    public Attachment upload(MultipartFile file) {
        // 获取文件扩展名
        String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String extWithNotPoint = ext.replaceAll("\\.", "");


        // 文件夹名称
        File attachmentDir = new File("D:/www/im/upload/" + File.separator + extWithNotPoint);
        if (!attachmentDir.exists()) {
            attachmentDir.mkdirs();
        }

        // 文件名称
        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + ext;

        try {
            file.transferTo(new File(attachmentDir + File.separator + fileName));
        } catch (IOException e) {
            e.printStackTrace();
            throw new ImException(R.ERROR_ATTACHMENT_UPLOAD);
        }

        // 组装一个Attachment对象 新增到数据库
        Attachment attachment = new Attachment();
        attachment.setPath("/preview/" + extWithNotPoint + "/" + fileName);
        attachment.setCreateTime(new Date());

        attachmentMapper.insert(attachment);

        return attachment;
    }
}
