package com.ihrm.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
@ConfigurationProperties("jwt.config")
public class JwtUtils {

    //签名私钥
    private String key;    // 在 “jwt.config” 中配置了key的值

    //签名失效时间
    private Long ttl;     //  在 “jwt.config” 中配置了ttl的值

    /**
     * 设置认证token
     *      id:登录用户id
     *      subject:登录用户名
     *
     */
    public String createJwt(String id , String name , Map<String,Object> map){

        //1.设置失效时间
        long now = System.currentTimeMillis();  // 当前毫秒数；
        long exp = now + ttl;                   // 失效时间
        //2.创建jwtBuilder
        JwtBuilder jwtBuilder = Jwts.builder().setId(id).setSubject(name)
                .setIssuedAt(new Date())     // 当前签名的时间
                .signWith(SignatureAlgorithm.HS256, key);// (1)加密签名方式，(2)自己定义的私钥
        //3.根据map设置claims
        for(Map.Entry<String,Object> entry : map.entrySet()){
            jwtBuilder.claim(entry.getKey(),entry.getValue());
        }
        jwtBuilder.setExpiration(new Date(exp));
        //4.创建token
        String token = jwtBuilder.compact();
        return token;
    }

    /**
     * 解析token字符串获取clamis
     */
    public Claims parsetJwt(String token){
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();  // 得到token的信息体
        return claims;
    }

}
