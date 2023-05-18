package com.ilovesshan.im.model.vo;

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
public class FriendVo {
    private long id;
    private String username;
    private String image;
}
