package com.example.demo20191115.Server.impl;

import com.example.demo20191115.Domain.TOrder;
import com.example.demo20191115.Mapper.videomapper;
import com.example.demo20191115.Server.testsever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/*
实现类
 */
@Service
public class videoserverimpl implements testsever {
    @Autowired   //底层实现
    private videomapper videomapper;

    @Override
    public List<TOrder> findall() {
        return videomapper.findall();
    }

    @Override
    public List<TOrder> findbyid(int id) {
        return videomapper.findbyid(id);
    }

    @Override
    public int findupdate(TOrder order) {
        return videomapper.findupdate(order);
    }

    @Override
    public int finddelete(int id) {
        return videomapper.finddelete(id);
    }

    @Override
    public  int findadd(TOrder order)
    {
        return  videomapper.findadd(order);

    }
}
