package com.example.demo20191115.Server;
import com.example.demo20191115.Domain.TOrder;
import java.util.List;
/*
接口类
 */
public interface testsever {
    List<TOrder> findall();
    List<TOrder> findbyid(int id);
    int findupdate(TOrder order);
    int finddelete(int id);
    int findadd(TOrder order);

}
