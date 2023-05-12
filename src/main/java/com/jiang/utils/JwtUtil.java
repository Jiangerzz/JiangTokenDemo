package com.jiang.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

public class JwtUtil {
    
    //有效期
    public static final Long JWT_TTL = 60 * 60 * 1000L;
    //设置密钥明文
    public static final String JWT_KEY = "xjiang";
    
    public static String getUUID(){
        String token = UUID.randomUUID().toString().replaceAll("—","");
        return token;
    }
    
  
    
    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if(ttlMillis == null){
            ttlMillis = JwtUtil.JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                .setId(uuid) //唯一ID
                .setSubject(subject)  //主题 
                .setIssuer("xj")
                .setIssuedAt(now)
                .signWith(signatureAlgorithm, secretKey) //算法签名，密匙
                .setExpiration(expDate);
    }

    /**
     * 生成jwt
     * @param id
     * @param subject
     * @param ttlMillis
     * @return
     */
    public static String createJWT(String id, String subject, Long ttlMillis){
        JwtBuilder builder =  getJwtBuilder(subject, ttlMillis, id);
        return builder.compact();
    }

    public static String createJWT(String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID());//设置过期时间
        return builder.compact();
    }
    
    public static  String createJWT(String subject) {
        JwtBuilder jwtBuilder = getJwtBuilder(subject, null, getUUID());
        return jwtBuilder.compact();
    }
    /**
     * 生成加密后的密钥 secretKey
     */
    public static SecretKey generalKey(){
        byte[] decode = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        SecretKey key = new SecretKeySpec(decode, 0, decode.length, "AES");
        return key;
    }

    /**
     * 解析
     */
    public static Claims parseJWT(String jwt) throws Exception{
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }
}
