package com.example.demo20191115.utils;
import com.example.demo20191115.domain.TUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * jwt工具类
 */
public class JwtUtils {
    public static final String SUBJECT = "xdclass";
    public static final long EXPIRE = 1000 * 60 * 60 * 24 * 7;//过期时间
    //秘钥
    public static final String APPERCRET = "xd6666";
    /**
     * 生成vJWT验证
     *
     * @return 返回生成的token
     */
    public static String geneJsonWebToken(TUser user) {
        if(user==null)
        {
            return  null;
        }
        String token= Jwts.builder().setSubject(SUBJECT)
                .claim("id",user.getId())
                .claim("username",user.getUsername())
                .claim("password",user.getPassword())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRE))
                .signWith(SignatureAlgorithm.ES256,APPERCRET).compact();
        return token;

    }

}
