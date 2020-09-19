/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaevcode.myunsplashclone.model.service;

import com.jaevcode.myunsplashclone.model.embeddedId.PhotoId;
import com.jaevcode.myunsplashclone.model.entity.Photo;
import java.util.List;

/**
 *
 * @author @JAEVCODE
 */
public interface IPhotoService {

    public List<Photo> findAllByEmail(String email);

    public Photo findById(PhotoId id);

    public Photo save(Photo photo);

    public void delete(PhotoId id);
}
