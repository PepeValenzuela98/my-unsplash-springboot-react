/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaevcode.myunsplashclone.model.service.impl;

import com.jaevcode.myunsplashclone.model.dao.IUserDao;
import com.jaevcode.myunsplashclone.model.entity.User;
import com.jaevcode.myunsplashclone.model.service.IUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author @JAEVCODE
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserDao userDao;
    
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>) userDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(String email) {
        return userDao.findById(email).orElse(null);
    }

    @Override
    @Transactional()
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.save(user);
    }

    @Override
    @Transactional()
    public void delete(String email) {
        userDao.deleteById(email);
    }

}
