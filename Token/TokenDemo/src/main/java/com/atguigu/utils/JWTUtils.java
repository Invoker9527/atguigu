package com.atguigu.utils;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author YangRuiHong
 * @Create 2023-02-23 16:46
 * @Description
 */
public class JWTUtils {
    public static final String SECRET = "#^*gddj";

    /**
     * 生成token
     *
     * @param acct
     * @return
     */
    public static String gerateToken(String acct) {
        Map<String, Object> claims = new HashMap<String, Object>();
        //添加数据
        claims.put("acct", acct);
        JwtBuilder jwtBuilder = Jwts.builder()
                //签发算法，设置秘钥
                .signWith(SignatureAlgorithm.HS256, SECRET)
                //body数据，要唯一
                .setClaims(claims)
                //设置签发时间
                .setIssuedAt(new Date())
                //设置过期时间（当前系统时间+1天，即一天后过期）
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 60 * 1000));
        String token = jwtBuilder.compact();

        return token;

    }

    /**
     * 解析token
     *
     * @param toKen
     * @return
     */
    public static Map<String, Object> parseToken(String toKen) {
        Jwt parse = Jwts.parser().setSigningKey(SECRET).parse(toKen);
        Map<String, Object> claims = (Map<String, Object>) parse.getBody();
        return claims;
    }

    public static void main(String[] args) {
        String gerateToken = gerateToken("admin");
        System.out.println("gerateToken = " + gerateToken);
        String acct = (String) parseToken(gerateToken).get("acct");
        System.out.println("acct = " + acct);
    }
}
