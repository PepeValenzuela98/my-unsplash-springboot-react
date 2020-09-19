/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaevcode.myunsplashclone.auth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaevcode.myunsplashclone.model.entity.User;
import com.jaevcode.myunsplashclone.model.wrapper.UserDetail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

/**
 *
 * @author @JAEVCODE
 */
@Component
public class JWTServiceImpl implements IJWTService {

    public static final String SECRET = Base64Utils.encodeToString("HYPERHERO1998PEPHD89912020!".getBytes());

    public static final Long EXPIRATIONDATE = 14400000L;

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String HEADER_STRING = "Authorization";

    @Override
    public String create(Authentication auth) throws JsonProcessingException {

        User user = getUser(auth);

        Claims claims = Jwts.claims();
        claims.put("user", new ObjectMapper().writeValueAsString(user));
        claims.setSubject(user.getEmail());

        String jwt = Jwts
                .builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONDATE))
                .signWith(getSecretKey())
                .compact();

        return jwt;
    }

    @Override
    public String create(User user) throws JsonProcessingException {
        Claims claims = Jwts.claims();
        claims.put("user", new ObjectMapper().writeValueAsString(user));
        claims.setSubject(user.getEmail());

        String jwt = "Bearer " + Jwts
                .builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONDATE))
                .signWith(getSecretKey())
                .compact();
        return jwt;
    }

    @Override
    public Boolean validate(String token) {
        try {
            getClaims(token);
            return Boolean.TRUE;
        } catch (JwtException | IllegalArgumentException ex) {
            return Boolean.FALSE;
        }
    }

    @Override
    public SecretKey getSecretKey() {
        return new SecretKeySpec(SECRET.getBytes(), SignatureAlgorithm.HS512.getJcaName());
    }

    @Override
    public User getUser(Authentication auth) {
        UserDetail userDetail = (UserDetail) auth.getPrincipal();

        User user = new User();
        user.setEmail(userDetail.getEmail());
        user.setName(userDetail.getName());
        user.setCreateAt(userDetail.getCreateAt());
        return user;
    }

    @Override
    public Claims getClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(resolve(token))
                .getBody();
    }

    @Override
    public String getEmail(String token) {
        return getClaims(token).getSubject();
    }

    @Override
    public String resolve(String token) {
        if (token != null && token.startsWith(TOKEN_PREFIX)) {
            return token.replace(TOKEN_PREFIX, "");
        }
        return null;
    }

}
