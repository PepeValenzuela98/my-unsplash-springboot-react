/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaevcode.myunsplashclone.model.dao;

import com.jaevcode.myunsplashclone.model.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author @JAEVCODE
 */
public interface IUserDao extends CrudRepository<User, String>{
    
}
