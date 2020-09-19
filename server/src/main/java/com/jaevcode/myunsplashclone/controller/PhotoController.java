/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaevcode.myunsplashclone.controller;

import com.jaevcode.myunsplashclone.auth.service.IJWTService;
import com.jaevcode.myunsplashclone.helpers.Util;
import com.jaevcode.myunsplashclone.model.embeddedId.PhotoId;
import com.jaevcode.myunsplashclone.model.entity.Photo;
import com.jaevcode.myunsplashclone.model.entity.User;
import com.jaevcode.myunsplashclone.model.service.IPhotoService;
import com.jaevcode.myunsplashclone.model.service.IUserService;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author @JAEVCODE
 */
@RestController
@RequestMapping("/photo")
@CrossOrigin
public class PhotoController {

    @Autowired
    IPhotoService photoService;

    @Autowired
    IUserService userService;

    @Autowired
    IJWTService jwtService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    Util util;

    @GetMapping("/")
    public ResponseEntity<?> list(@RequestHeader("Authorization") String token) {
        List<Photo> photos;
        Model response = new ConcurrentModel();
        try {
            photos = photoService.findAllByEmail(jwtService.getEmail(token));
        } catch (DataAccessException e) {
            response.addAttribute("error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Model>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<Photo>>(photos, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> save(@RequestHeader("Authorization") String token, @Valid @RequestBody Photo photo, BindingResult result) {
        Photo newPhoto;
        Model response = new ConcurrentModel();
        if (result.hasErrors()) {
            response.addAttribute("errors", util.getErrorsMessages(result));
            return new ResponseEntity<Model>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            photo.setNestId(new PhotoId("", userService.findById(jwtService.getEmail(token))));
            newPhoto = photoService.save(photo);
        } catch (DataAccessException e) {
            response.addAttribute("error: ", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Model>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.addAttribute("message", "Photo saved successfully!");
        response.addAttribute("photo", newPhoto);
        return new ResponseEntity<Model>(response, HttpStatus.CREATED);

    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestHeader("Authorization") String token, 
            @RequestBody Map<String,String> payload) {
        Photo photo;
        Model response = new ConcurrentModel();
        User user = userService.findById(jwtService.getEmail(token));
        if(!passwordEncoder.matches(payload.get("password"),user.getPassword())){
            response.addAttribute("error", "Wrong password");
            System.out.println("dsadsa");
            return new ResponseEntity<Model>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            photo = photoService.findById(new PhotoId(payload.get("id"), user));
            if (photo == null) {
                
                response.addAttribute("error", "Photo does not exist");
                return new ResponseEntity<Model>(response, HttpStatus.NOT_FOUND);
            }
            photoService.delete(photo.getNestId());
        } catch (DataAccessException e) {
            response.addAttribute("error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Model>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.addAttribute("message", "Photo deleted successfully!");
        return new ResponseEntity<Model>(response, HttpStatus.OK);
    }
}

