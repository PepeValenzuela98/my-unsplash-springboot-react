/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaevcode.myunsplashclone.model.service.impl;

import com.jaevcode.myunsplashclone.model.dao.IPhotoDao;
import com.jaevcode.myunsplashclone.model.embeddedId.PhotoId;
import com.jaevcode.myunsplashclone.model.entity.Photo;
import com.jaevcode.myunsplashclone.model.service.IPhotoService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author @JAEVCODE
 */
@Service
public class PhotoServiceImpl implements IPhotoService {

    @Autowired
    IPhotoDao photoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Photo> findAllByEmail(String email) {
        return (List<Photo>) photoDao.findByUser(email);
    }

    @Override
    public Photo findById(PhotoId id) {
        return photoDao.findById(id).orElse(null);
    }

    @Override
    public Photo save(Photo photo) {
        photo.getNestId().setId(UUID.randomUUID().toString());
        photo.setUrl(photo.getUrl() + photo.getNestId().getId());
        return photoDao.save(photo);
    }

    @Override
    public void delete(PhotoId id) {
        photoDao.deleteById(id);
    }

}
