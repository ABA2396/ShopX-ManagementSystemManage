package com.zack.utils;

import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;

import java.util.Date;

public class JwtHelper {
    //token的生效时间，过期失效
    private static long tokenExpiration = 24 * 60 * 60 * 1000;
    //生成token用的密钥
    private static String tokenSignKey = "123456";

    //生成token字符串  注：可以用任意参数来生成token，也可以用对象
    public static String createToken(Long userId, Integer userType) {
        String token = Jwts.builder()
                //分类
                .setSubject("YYGH-USER")
                //设置token的有效时间
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                //主体部分
                .claim("userId", userId)
//                .claim("userName", userName)
                .claim("userType", userType)
                //签名部分
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        return token;
    }

    //从token字符串获取userid
    public static Long getUserId(String token) {
        if (StringUtils.isEmpty(token)) return null;
        //获取主体部分
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        Integer userId = (Integer) claims.get("userId");
        return userId.longValue();
    }

    //从token字符串获取userType
    public static Integer getUserType(String token) {
        if (StringUtils.isEmpty(token)) return null;
        Jws<Claims> claimsJws
                = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return (Integer) (claims.get("userType"));
    }

    //从token字符串获取userName
    public static String getUserName(String token) {
        if (StringUtils.isEmpty(token)) return "";
        Jws<Claims> claimsJws
                = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return (String) claims.get("userName");
    }

    //判断token是否有效
    public static boolean isExpiration(String token) {
        try {
            boolean isExpire = Jwts.parser()
                    .setSigningKey(tokenSignKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration().before(new Date());
            //没有过期，有效，返回false
            return isExpire;
        } catch (Exception e) {
            //过期出现异常，返回true
            return true;
        }
    }

    /**
     * 刷新Token
     *
     * @param token
     * @return
     */
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = Jwts.parser()
                    .setSigningKey(tokenSignKey)
                    .parseClaimsJws(token)
                    .getBody();
            refreshedToken = JwtHelper.createToken(getUserId(token), getUserType(token));
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
//        String token = JwtHelper.createToken(1L, 2);
//        System.out.println(token);
//        System.out.println(JwtHelper.getUserId(token));
//        System.out.println(JwtHelper.getUserName(token));
    }

}
