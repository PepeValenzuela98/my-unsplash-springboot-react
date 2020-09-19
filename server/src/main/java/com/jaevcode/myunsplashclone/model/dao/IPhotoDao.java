/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaevcode.myunsplashclone.model.dao;

import com.jaevcode.myunsplashclone.model.embeddedId.PhotoId;
import com.jaevcode.myunsplashclone.model.entity.Photo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author @JAEVCODE
 */
public interface IPhotoDao extends CrudRepository<Photo, PhotoId> {

    @Query("SELECT p FROM Photo p WHERE p.nestId.userUnsplash.email LIKE ?1 ORDER BY p.createAt DESC")
    public Iterable<Photo> findByUser(String email);
}
