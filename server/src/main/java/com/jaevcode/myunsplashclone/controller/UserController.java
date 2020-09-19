/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaevcode.myunsplashclone.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaevcode.myunsplashclone.auth.service.IJWTService;
import com.jaevcode.myunsplashclone.helpers.Util;
import com.jaevcode.myunsplashclone.model.entity.User;
import com.jaevcode.myunsplashclone.model.service.IUserService;
import io.jsonwebtoken.Claims;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author @JAEVCODE
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    IUserService userService;

    @Autowired
    Util util;

    @Autowired
    IJWTService jwtService;

    @GetMapping("/authenticated")
    public ResponseEntity<?> find(@RequestHeader("Authorization") String token) {
        Model response = new ConcurrentModel();
        Claims claims = jwtService.getClaims(token);
        Object user = claims.get("user");

        try {
            response.addAttribute("user", new ObjectMapper().readValue(user.toString(), User.class));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ResponseEntity<Model>(response, HttpStatus.ACCEPTED);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user, BindingResult result) {
        User newUser = null;
        Model response = new ConcurrentModel();

        if (result.hasErrors()) {
            response.addAttribute("errors", util.getErrorsMessages(result));
            return new ResponseEntity<Model>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            newUser = userService.save(user);
            response.addAttribute("jwt", jwtService.create(newUser));
        } catch (DataAccessException e) {
            response.addAttribute("error: ", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Model>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (JsonProcessingException ex) {
            response.addAttribute("error: ", "Error generatin jwt");
            return new ResponseEntity<Model>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.addAttribute("message", "User registered successfully!");
        response.addAttribute("user", newUser);
        return new ResponseEntity<Model>(response, HttpStatus.CREATED);
    }

}
