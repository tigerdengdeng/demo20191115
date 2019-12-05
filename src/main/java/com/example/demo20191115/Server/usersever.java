package com.example.demo20191115.Server;
import com.example.demo20191115.Domain.TUser;
import java.util.List;
/*
接口类
 */
public interface usersever {
    List<TUser> userfindall();
    List<TUser> userfindbyid(int id);
    int userfindupdate(TUser user);
    int userfinddelete(int id);
    int userfindadd(TUser user);
}
