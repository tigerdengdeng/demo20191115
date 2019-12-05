package com.example.demo20191115.Domain;
import lombok.Data;
import java.io.Serializable;

@Data   //简化get set 等一些日常操作
/**
 * 用户表数据模型
 */
public class TUser  implements Serializable {

  //主键ID
  private Integer id;
  //用户名
  private String username;
  //密码
  private String password;
  //状态（0）启用 （1）禁用
  private Integer status;

//  public Integer getId() {
//    return id;
//  }
//
//  public void setId(Integer id) {
//    this.id = id;
//  }
//
//
//  public String getUsername() {
//    return username;
//  }
//
//  public void setUsername(String username) {
//    this.username = username;
//  }
//
//
//  public String getPassword() {
//    return password;
//  }
//
//  public void setPassword(String password) {
//    this.password = password;
//  }
//
//
//  public Integer getStatus() {
//    return status;
//  }
//
//  public void setStatus(Integer status) {
//    this.status = status;
//  }

}
