package com.example.demo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {

    final static long TOKEN_EXP = 1000 * 60 * 6 * 10;//过期时间,测试使用一小时


    /**
     * 签名生成
     *
     * @param userName
     * @return
     */
    public static String getToken(String userName, String userpassword) {
        String token = null;
        try {
            Date expiresAt = new Date(System.currentTimeMillis() + TOKEN_EXP);
            token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("username", userName)
                    .withExpiresAt(expiresAt)
                    // 使用了HMAC256加密算法。
                    .sign(Algorithm.HMAC256(userpassword));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    /**
     * 签名验证
     *
     * @param token
     * @return
     */
    public static boolean verify(String token, User user) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(user.getPassword())).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
            System.out.println("认证通过：");
            System.out.println("username: " + jwt.getClaim("username").asString());
            System.out.println("过期时间：      " + jwt.getExpiresAt());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}