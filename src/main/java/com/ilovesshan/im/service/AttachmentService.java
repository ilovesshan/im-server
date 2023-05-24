package com.ilovesshan.im.service;

import com.ilovesshan.im.model.po.Attachment;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2023/5/18
 * @description:
 */
public interface AttachmentService {

    Attachment upload(MultipartFile file);
}
