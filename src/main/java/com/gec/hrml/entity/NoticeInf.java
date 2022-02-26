package com.gec.hrml.entity;


import java.io.Serializable;
import java.sql.Timestamp;
/**
 * 通知信息表
 */
public class NoticeInf extends  PageModel implements Serializable {

  private long id;
  private String title;
  private String content;
  private Timestamp createDate;
  private long userId;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public java.sql.Timestamp getCreateDate() {
    return createDate;
  }

  public void setCreateDate(java.sql.Timestamp createDate) {
    this.createDate = createDate;
  }


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

}
