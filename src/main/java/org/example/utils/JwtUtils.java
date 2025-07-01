package org.example.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;
@Slf4j
public class JwtUtils {

    private static final String signKey = "7LrS5a6d5piv5qWt7L2c5a6Y5a6d5piv5qWt7L2c5a6Y5a6d5piv5qWt7L2c5a6Y5a6d5piv5qWt7L2c5a6Y=";
    private static final Long expire = 604800000L;

    /**
     * 生成JWT令牌
     * @return
     */
    public static String generateJwt(Map<String,Object> claims){
        log.info("解析前的  {}", claims);
        String jwt = Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, signKey)
                .compact();
        return jwt;
    }

    /**
     * 解析JWT令牌
     * @param jwt JWT令牌
     * @return JWT第二部分负载 payload 中存储的内容
     */
    public static Claims parseJWT(String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(jwt)
                .getBody();


        log.info("解析后的{}", claims);

        return claims;

    }


}