package com.gec.hrml.entity;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 * 员工信息表
 */
public class EmployeeInf implements Serializable {

  private long id;
  private long deptId;
  private long jobId;
  private String name;
  private String cardId;
  private String address;
  private String postCode;
  private String tel;
  private String phone;
  private String qqNum;
  private String email;
  private long sex;
  private String party;
  private Timestamp birthday;
  private String race;
  private String education;
  private String speciality;
  private String hobby;
  private String remark;
  private Timestamp createDate;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getDeptId() {
    return deptId;
  }

  public void setDeptId(long deptId) {
    this.deptId = deptId;
  }


  public long getJobId() {
    return jobId;
  }

  public void setJobId(long jobId) {
    this.jobId = jobId;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getCardId() {
    return cardId;
  }

  public void setCardId(String cardId) {
    this.cardId = cardId;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public String getPostCode() {
    return postCode;
  }

  public void setPostCode(String postCode) {
    this.postCode = postCode;
  }


  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public String getQqNum() {
    return qqNum;
  }

  public void setQqNum(String qqNum) {
    this.qqNum = qqNum;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public long getSex() {
    return sex;
  }

  public void setSex(long sex) {
    this.sex = sex;
  }


  public String getParty() {
    return party;
  }

  public void setParty(String party) {
    this.party = party;
  }


  public java.sql.Timestamp getBirthday() {
    return birthday;
  }

  public void setBirthday(java.sql.Timestamp birthday) {
    this.birthday = birthday;
  }


  public String getRace() {
    return race;
  }

  public void setRace(String race) {
    this.race = race;
  }


  public String getEducation() {
    return education;
  }

  public void setEducation(String education) {
    this.education = education;
  }


  public String getSpeciality() {
    return speciality;
  }

  public void setSpeciality(String speciality) {
    this.speciality = speciality;
  }


  public String getHobby() {
    return hobby;
  }

  public void setHobby(String hobby) {
    this.hobby = hobby;
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

}
