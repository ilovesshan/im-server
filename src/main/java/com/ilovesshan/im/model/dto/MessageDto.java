package com.ilovesshan.im.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class MessageDto {
    private long to;
    private long type;
    private String content;
}

