package com.gec.hrml.entity;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 *文档表
 */
public class DocumentInf extends PageModel implements Serializable {

  private long id;
  private String title;
  private String filename;
  private String remark;
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


  public String getFilename() {
    return filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
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
