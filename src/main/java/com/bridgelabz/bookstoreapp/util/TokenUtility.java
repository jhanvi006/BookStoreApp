package com.bridgelabz.bookstoreapp.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.springframework.stereotype.Component;

@Component
public class TokenUtility {
    public static final String TOKEN_SECRET = "jhanvik";

    public String createToken(int id){
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            String token = JWT.create().withClaim("userId", id).sign(algorithm);
            return token;
        }catch (JWTCreationException exception){
            exception.printStackTrace();
            //log Token Signing Failed
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    public int decodeToken(String token){
        Verification verification = null;
        try {
            verification = JWT.require(Algorithm.HMAC256(TOKEN_SECRET));
        }catch (IllegalArgumentException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        JWTVerifier jwtVerifier = verification.build();
        //to decode token
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        Claim claim = decodedJWT.getClaim("userId");
        int id = claim.asInt();
        return id;
    }
}
