package com.aistar.util;


import com.aistar.entity.Constant;
import com.aistar.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

/**
 * Created by js on 2019/6/28.
 */
public class TokenUtil {
    public String getToken(User user){

        //加密盐
        SecretKey secretKey=generalKey();
        JwtBuilder token= Jwts.builder()
                .setId(user.getId())
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())//登陆时间
                .setExpiration(new Date(new Date().getTime()+(60*10000)))//设置过期时间 十分钟
                .signWith(SignatureAlgorithm.HS256,secretKey);//设置签名秘钥

        return token.compact();
    }


    //产生加密盐
    public SecretKey generalKey() {
        String stringKey = Constant.JWT_SECRET;

        // 本地的密码解码
        byte[] encodedKey = Base64.decodeBase64(stringKey);

        // 根据给定的字节数组使用AES加密算法构造一个密钥
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");

        return key;
    }

    public Claims paraseToken(String token){
        Claims claims= Jwts.parser()
                .setSigningKey(generalKey())
                .parseClaimsJws(token)
                .getBody();

        System.out.println(claims.getId());
        return claims;
    }

    public static void main(String[] args) {
        User user=new User();
        user.setId("1");
        user.setUsername("张三");
        TokenUtil tokenUtil=new TokenUtil();
        System.out.println(tokenUtil.generalKey());
        System.out.println(tokenUtil.getToken(user));

        System.out.println(tokenUtil.paraseToken(tokenUtil.getToken(user)));


    }
}
