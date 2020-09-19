/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaevcode.myunsplashclone.model.service;

import com.jaevcode.myunsplashclone.model.entity.User;
import java.util.List;

public interface IUserService {

    public List<User> findAll();

    public User findById(String email);

    public User save(User user);

    public void delete(String email);
}
