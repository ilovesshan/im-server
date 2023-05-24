package com.ilovesshan.im.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attachment {

    private long id;
    private String path;
    private Date createTime;


}
