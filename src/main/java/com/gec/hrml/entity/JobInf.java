package com.gec.hrml.entity;
import java.io.Serializable;

/**
 * 职位表
 * @author 1
 */
public class JobInf extends PageModel implements Serializable {

  private long id;
  private String name;
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

  @Override
  public String toString() {
    return "JobInf{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", remark='" + remark + '\'' +
            '}';
  }
}
