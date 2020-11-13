package com.chenglulu.utils;

import com.chenglulu.constant.Constants;
import com.chenglulu.constant.ErrorCode;
import com.chenglulu.exception.ForbiddenException;
import com.chenglulu.exception.ServiceException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

public class JwtTokenUtils {
    /**
     * 由字符串生成加密key
     * @return SecretKey
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.decodeBase64(Constants.TOKEN_SECRET);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    // 创建token
    public static String createToken(String username, String userId, boolean isRememberMe) {
        long expiration = isRememberMe ? Constants.TOKEN_EXPIRATION_REMEMBER : Constants.TOKEN_EXPIRATION;
        long nowMillis = System.currentTimeMillis();//生成JWT的时间
        Date now = new Date(nowMillis);
        SecretKey secretKey = generalKey();
        return Jwts.builder()
                .setIssuer(Constants.TOKEN_ISS)
                .claim("userId", userId)
                .setSubject(username)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setExpiration(new Date(nowMillis + expiration * 1000))
                .compact();
    }

    // 从token中获取用户名
    public static String getUsername(String token){
        return getTokenBody(token).getSubject();
    }

    // 从token中获取用户ID
    public static String getUserId(String token){
        return getTokenBody(token).get("userId", String.class);
    }

    // 是否已过期
    public static boolean isExpiration(String token){
        return getTokenBody(token).getExpiration().before(new Date());
    }

    private static Claims getTokenBody(String token){
        SecretKey secretKey = generalKey();

        try {
            return Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (ExpiredJwtException ex){
            throw new ForbiddenException(ErrorCode.AUTHORIZATION_TOKEN_EXPIRED);
        }catch (Exception ex){
            throw new ServiceException(ErrorCode.AUTHORIZATION_PARSE_TOKEN_ERROR);
        }
    }
}
