package com.example.entity.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class jwtUtils {
    /*过期时间15分钟*/
    private static final long EXPIRE_TIME=15*60*1000;
    /*token私钥*/
    private static final String TOKEN_SECRET="65sd8fg6af1as1f65as6d1asd5";

    /**
     * 生成签名验证，15分钟后过期
     * @param username   用户名
     * @param userId     用户ID
     * @return           返回加密的TOKEN
     * @throws UnsupportedEncodingException
     */
    public static String sign(String username,String userId) throws UnsupportedEncodingException {
        //过期时间
        Date date=new Date(System.currentTimeMillis()+EXPIRE_TIME);
        //私密及加密算法
        Algorithm algorithm=Algorithm.HMAC256(TOKEN_SECRET);
        //设置头部信息
        Map<String,Object> header=new HashMap<>();
        header.put("typ","JWT");
        header.put("alg","HS256");
        //附带username,userId信息，生成签名
        return JWT.create()
                .withHeader(header)
                .withClaim("loginName",username)
                .withClaim("userId",userId)
                .withExpiresAt(date)
                .sign(algorithm);
    }

    /**
     * 校验token是否正确
     * @param token  token密匙
     * @return    boolean
     */
    public static boolean verify(String token){
        try{
            Algorithm algorithm=Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier=JWT.require(algorithm).build();
            DecodedJWT jwt=verifier.verify(token);
            return true;
        }catch (Exception exception){
            return false;
        }
    }
}
