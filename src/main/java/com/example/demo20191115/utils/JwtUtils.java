package com.example.demo20191115.utils;
import com.example.demo20191115.domain.TUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * jwt工具类
 */
@Data
@PropertySource(value = "classpath:application.properties")   //指定在哪里读取配置文件路径  默认为项目根目录
@Configuration
public class JwtUtils {

    /**
     * 读取配置文件里面配置的私钥
     */
    @Value("${jwtkey}")
    private    String jwtkey ;
    /**
     * 常量需要全部大写
     */
    /**
     * 生成jwt token
     * @param object
     * @param endDatetime  过期时间
     * @return  返回生成的token
     */
    public   String geneJsonWebToken(String id,String name,Map<String,Object>map,
                                          long endDatetime) {
         try {
             JwtBuilder jwtBuilder= Jwts.builder().setId(id).setSubject(name)
                     .setIssuedAt(new Date()) //发行时间
                     .setExpiration(new Date(System.currentTimeMillis()+endDatetime)) //过期时间
                     .signWith(SignatureAlgorithm.HS256,jwtkey); //生成token方式、和初始密; //加密方式
             String token= jwtBuilder.setClaims(map).compact();
             return token;

         }catch (Exception EX){

             EX.getLocalizedMessage();
         }
      return  null;

    }







    /**
     * 校验token
     * @param token  token
     * @param key  生成的初始值密码
     * @return  返回claims对象
     */
    public  Claims checkJwt(String token){

        try {
            final  Claims claims=Jwts.parser().
                    setSigningKey(jwtkey).parseClaimsJws(token).getBody();
              return  claims;

        }catch (Exception ex){
           return  null;

        }

  }

}
