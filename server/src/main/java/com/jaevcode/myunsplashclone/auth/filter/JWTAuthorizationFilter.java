/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaevcode.myunsplashclone.auth.filter;

import com.jaevcode.myunsplashclone.auth.service.IJWTService;
import com.jaevcode.myunsplashclone.auth.service.JWTServiceImpl;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 *
 * @author @JAEVCODE
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    IJWTService jwtService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, IJWTService jwtService) {
        super(authenticationManager);
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader(JWTServiceImpl.HEADER_STRING);

        //Comprobar que se requiera autentication. Si no continuar con el siguiente filtro 
        if (!requiresAuthentication(token)) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = null;
        if (jwtService.validate(token)) {
            authentication = new UsernamePasswordAuthenticationToken(
                    jwtService.getEmail(token),
                    null,
                    new ArrayList<GrantedAuthority>());
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);

    }

    protected Boolean requiresAuthentication(String header) {
        if (header == null || !header.startsWith("Bearer ")) {
            return false;
        }
        return true;
    }

}
