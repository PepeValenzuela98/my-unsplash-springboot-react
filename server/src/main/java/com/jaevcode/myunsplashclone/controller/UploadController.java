/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaevcode.myunsplashclone.controller;

import com.jaevcode.myunsplashclone.auth.service.IJWTService;
import com.jaevcode.myunsplashclone.service.IUploadFileService;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author @JAEVCODE
 */
@RestController
@RequestMapping("/upload")
@CrossOrigin
public class UploadController {

    @Autowired
    IUploadFileService uploadFileService;

    @Autowired
    IJWTService jwtService;

    @GetMapping(value = "/{filename:.+}")
    public ResponseEntity<Resource> showPhoto(@PathVariable String filename) {
        Resource resource = null;
        try {
            resource = uploadFileService.load(filename);
        } catch (MalformedURLException ex) {
            Logger.getLogger(UploadController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ResponseEntity.ok().
                header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> save(@RequestParam(name = "file") MultipartFile photo, @RequestParam String id) {
        String uniqueFileName = "";
        Model response = new ConcurrentModel();
        try {
            uniqueFileName = uploadFileService.copy(photo, id);
        } catch (IOException ex) {
            response.addAttribute("error", ex.getMessage());
            return new ResponseEntity<Model>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.addAttribute("filename", uniqueFileName);
        return new ResponseEntity<Model>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        uploadFileService.delete(id);
        Model response = new ConcurrentModel();
        response.addAttribute("message", "Deleted Successfully");
        return new ResponseEntity<Model>(response, HttpStatus.OK);
    }
}
