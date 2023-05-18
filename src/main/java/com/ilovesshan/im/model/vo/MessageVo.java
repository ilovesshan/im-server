package com.ilovesshan.im.model.vo;

import com.ilovesshan.im.model.po.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2023/5/18
 * @description:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageVo {
    private UserVo mUser;
    private UserVo yUser;
    private List<Message> message;
}

