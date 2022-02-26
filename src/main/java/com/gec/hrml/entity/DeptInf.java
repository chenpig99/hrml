package com.gec.hrml.entity;



import java.io.Serializable;

/**
 * 部门表
 */
public class DeptInf extends PageModel implements Serializable {

  private long id;
  /**
   * 部门名
   */
  private String name;
  /**
   * 详细信息
   */
  private String remark;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

}
