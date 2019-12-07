package com.example.demo20191115.server.impl;

import com.example.demo20191115.domain.TUser;
import com.example.demo20191115.mapper.UserMapper;
import com.example.demo20191115.server.UserSever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/*
实现类
 */
@Service
public class UserServeriMpl implements UserSever {
    @Autowired   //底层实现
    private UserMapper usermapper;

    @Override
    public List<TUser> userfindall() {
        return usermapper.userfindall();
    }

    @Override
    public List<TUser> userfindbyid(int id) { return usermapper.userfindbyid(id); }

    @Override
    public int userfindupdate(TUser user) {
        return usermapper.userfindupdate(user);
    }

    @Override
    public int userfinddelete(int id) {
        return usermapper.userfinddelete(id);
    }

    @Override
    public  int userfindadd(TUser user) { return  usermapper.userfindadd(user);}

    @Override
    public TUser loinguser(String username,String password){return  usermapper.loinguser(username,password);};

}
