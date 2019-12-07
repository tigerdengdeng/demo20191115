package com.example.demo20191115.server;
import com.example.demo20191115.domain.TUser;
import java.util.List;
/*
接口类
 */
public interface UserSever {
    List<TUser> userfindall();
    List<TUser> userfindbyid(int id);
    int userfindupdate(TUser user);
    int userfinddelete(int id);
    int userfindadd(TUser user);
    TUser loinguser(String username,String password);
}
