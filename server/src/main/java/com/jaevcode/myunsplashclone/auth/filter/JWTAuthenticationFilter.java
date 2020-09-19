/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaevcode.myunsplashclone.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaevcode.myunsplashclone.auth.service.IJWTService;
import com.jaevcode.myunsplashclone.model.entity.User;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

/**
 *
 * @author @JAEVCODE
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    AuthenticationManager authenticationManager;

    IJWTService jwtService;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager,IJWTService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    //Metodo que se ejecuta al intentar hacer la autenticacion
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //Recibe el email y el password
        String email = request.getParameter("email");
        String password = obtainPassword(request);

        if (email == null) {
            email = "";
        }

        if (password == null) {
            password = "";
        }

        email = email.trim();

        //Si el email y password no vienen en form data
        if (email.isEmpty() && password.isEmpty()) {
            //Extraer del raw
            User user = null;
            try {
                user = new ObjectMapper().readValue(request.getInputStream(), User.class);
                email = user.getEmail();
                password = user.getPassword();
            } catch (IOException ex) {
                Logger.getLogger(JWTAuthenticationFilter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password);

        return authenticationManager.authenticate(authToken);
    }

    //Metodo que se ejecuta cuando la autenticacion fue exitosa
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        String jwt = jwtService.create(authResult);
        User user = jwtService.getUser(authResult);
        

        //Se agrega el JWT al response
        response.addHeader("Authorization", "Bearer " + jwt);

        Model body = new ConcurrentModel();
        body.addAttribute("jwt", jwt);
        body.addAttribute("user", user);
        body.addAttribute("message", "Hello ".concat(user.getName()).concat(" you have successfully logged in!"));

        //Se convierte el objeto body en un JSON
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(200);//Http status OK
        response.setContentType("application/json");

    }

    //Metodo que se ejecuta cuando la autenticacion no fue exitosa
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        Model body = new ConcurrentModel();
        body.addAttribute("message", "Email or Password authentication error!");
        body.addAttribute("error", failed.getMessage());

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(401);//Http status Unauthorized
        response.setContentType("application/json");
    }

}
