package com.ilovesshan.im.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
  private long id;
  private long from;
  private long to;
  private long type;
  private String content;
  private Date time;

}
