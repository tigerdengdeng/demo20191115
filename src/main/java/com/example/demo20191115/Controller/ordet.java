package com.example.demo20191115.Controller;

import com.example.demo20191115.Domain.TOrder;
import com.example.demo20191115.Server.testsever;
import com.fasterxml.jackson.databind.ObjectReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
订单表
 */
@RestController
@RequestMapping("/Order/webapi")
public class ordet {

    @Autowired   //注入查询接口
    private testsever testsever;

    /**
     * 分页接口
     * @param page 当前第几页 默认第一页
     * @param size 每页显示几条
     * @return
     */
    @GetMapping("/orderlist")
    public  Object orderlist(@RequestParam(value = "page",defaultValue ="1")int page,
                             @RequestParam(value = "size",defaultValue ="10")int size){
        return testsever.findall();
    }

    /**
     * 根据条件查询操作
     * @param id 条件ID
     * @return
     */
    @GetMapping("/orderid")
    public Object orderid(@RequestParam(value = "page",required = true)int id){

        return testsever.findbyid(id);
    }

    /**
     * 删除操作
     * @param id 删除条件
     * @return
     */
    @DeleteMapping("/orderdelete")
    public  int orderdelete(@RequestParam(value = "id",required = true)int id){
     return  testsever.finddelete(id);
    }

    @PutMapping("/orderupdate")
    public  int orderupdate(int orderid,String  nanme){
        TOrder order =new TOrder();
        order.setOrderid(orderid);
        order.setNanme(nanme);
        return testsever.findupdate(order);
    }

    @PostMapping("/orderadd")
    public  int orderadd(int orderid ,String  nanme){
        TOrder order =new TOrder();
        order.setOrderid(orderid);
        order.setNanme(nanme);
        int rows=testsever.findadd(order);
        System.out.println(order.getOrderid()+"获取保存对象的ID");
        return rows;
    }
}
