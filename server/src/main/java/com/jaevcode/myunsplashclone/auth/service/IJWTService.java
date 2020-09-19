/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaevcode.myunsplashclone.auth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jaevcode.myunsplashclone.model.entity.User;
import io.jsonwebtoken.Claims;
import javax.crypto.SecretKey;
import org.springframework.security.core.Authentication;

/**
 *
 * @author @JAEVCODE
 */
public interface IJWTService {

    public String create(Authentication auth) throws JsonProcessingException;
    
    public String create(User user) throws JsonProcessingException;

    public Boolean validate(String token);
    
    public SecretKey getSecretKey();

    public User getUser(Authentication auth);

    public Claims getClaims(String token);

    public String getEmail(String token);

    public String resolve(String token);

}
