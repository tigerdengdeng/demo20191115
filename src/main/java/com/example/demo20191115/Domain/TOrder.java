package com.example.demo20191115.Domain;

import java.io.Serializable;



/*
订单表  继承Serializable 序列化
 */
public class TOrder implements Serializable {

  private Integer orderid;
  private String nanme;


  public Integer getOrderid() {
    return orderid;
  }

  public void setOrderid(Integer orderid) {
    this.orderid = orderid;
  }


  public String getNanme() {
    return nanme;
  }

  public void setNanme(String nanme) {
    this.nanme = nanme;
  }

}
