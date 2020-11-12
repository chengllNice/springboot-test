package com.chenglulu.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

public class JwtTokenUtils {
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    // 私钥
    private static final String SECRET = "maven.chenglulu.com";
    // 该JWT的签发者
    private static final String ISS = "chenglulu.com";

    // 过期时间是3600秒，既是1个小时
    private static final long EXPIRATION = 60L;

    // 选择了记住我之后的过期时间为7天
    private static final long EXPIRATION_REMEMBER = 604800L;

    /**
     * 由字符串生成加密key
     * @return SecretKey
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.decodeBase64(SECRET);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    // 创建token
    public static String createToken(String username, String userId, boolean isRememberMe) {
        long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;
        long nowMillis = System.currentTimeMillis();//生成JWT的时间
        Date now = new Date(nowMillis);
        SecretKey secretKey = generalKey();
        return Jwts.builder()
                .setIssuer(ISS)
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

        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

//    public static void main(String[] args) {
//        // 生成token
//        String s = generateToken("111", 20);
//        System.out.println(s);
//
//        // 验证
//        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMTEiLCJvcGVuSWQiOiIxMTEiLCJleHAiOjE1OTI1NTc3ODMsInVzZXJJZCI6MjAsImlhdCI6MTU5MjU1NTE5MSwianRpIjoidG9rZW5JZCJ9.X7JHnx3Y5wtb-n3pT9tft2I4hENJdeRxW2QWaI4jv2E";
//        Claims claims = verifyJwt(token);
//        String subject = claims.getSubject();
//        String userId = (String)claims.get("userId");
//        String openId = (String)claims.get("openId");
//        String sub = (String)claims.get("sub");
//        System.out.println("subject:" + subject);
//        System.out.println("userId:" + userId);
//        System.out.println("openId:" + openId);
//        System.out.println("sub:" + sub);
//    }
}
