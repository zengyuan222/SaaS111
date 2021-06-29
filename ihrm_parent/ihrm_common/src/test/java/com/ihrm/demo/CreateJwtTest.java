package com.ihrm.demo;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class CreateJwtTest {

    /**
     * 通过jjwt创建token
     * @param args
     */
    public static void main(String[] args) {
        JwtBuilder jwtBuilder = Jwts.builder().setId("88").setSubject("小白")
                .setIssuedAt(new Date())     // 当前签名的时间
                .signWith(SignatureAlgorithm.HS256, "ihrm")// (1)加密签名方式，(2)自己定义的私钥
                .claim("companyId","123456")
                .claim("companyName","柳州山清水秀");
        String token = jwtBuilder.compact();
        System.out.println(token);
    }

}
