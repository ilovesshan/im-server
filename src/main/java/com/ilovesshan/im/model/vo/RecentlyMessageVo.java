package com.ilovesshan.im.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2023/5/20
 * @description:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecentlyMessageVo {
    private long id;
    private long from;
    private long to;
    private long type;
    private String content;

    private long userId;
    private String username;
    private String image;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
    private Date time;
}
