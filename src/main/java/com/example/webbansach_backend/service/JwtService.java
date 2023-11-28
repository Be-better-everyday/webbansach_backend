//package com.example.webbansach_backend.service;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//public class JwtService {
//    //  using a random String
//    public static final String SECRECT = "gddpG8sFsyyGMi6hAwsC8plPI0XdxMUK1ur9XLlHiGHRLdnGmfudZk0eeHwOqvMz";
//
//    // Create JWT depending on "username", we also use more param if we want
//    public String generateToken(String username) {
//        Map<String, Object> claims =  new HashMap<>();
//        return createToken(claims, username);
//    }
//
//    private String createToken(Map<String, Object> claims, String username) {
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(username)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                // expire after 30 minutes from created
//                .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
//                .signWith(SignatureAlgorithm.ES256, getSignKey())
//                .compact();
//    }
//
//    // Get secret key
//    private Key getSignKey () {
//        byte[] keyBytes = Decoders.BASE64.decode(SECRECT);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//
//    // Extract information from JWT
//    private Claims extractAllClaims (String token){
//        return Jwts.parser().setSigningKey(getSignKey()).parseClaimsJws(token).getBody();
//    }
//}
