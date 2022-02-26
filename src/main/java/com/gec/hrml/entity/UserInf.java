
package com.gec.hrml.entity;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 * 管理用户登陆表
 */
public class UserInf extends PageModel implements Serializable {

  private long id;
  private String loginname;
  private String password;
  private String status;
  private Timestamp createdate;
  private String username;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getLoginname() {
    return loginname;
  }

  public void setLoginname(String loginname) {
    this.loginname = loginname;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Timestamp getCreatedate() {
    return createdate;
  }

  public void setCreatedate(Timestamp createdate) {
    this.createdate = createdate;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public String toString() {
    return "UserInf{" +
            "id=" + id +
            ", loginname='" + loginname + '\'' +
            ", password='" + password + '\'' +
            ", status='" + status + '\'' +
            ", createdate=" + createdate +
            ", username='" + username + '\'' +
            '}';
  }
}
