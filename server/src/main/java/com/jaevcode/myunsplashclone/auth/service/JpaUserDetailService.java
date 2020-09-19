/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaevcode.myunsplashclone.auth.service;

import com.jaevcode.myunsplashclone.model.entity.User;
import com.jaevcode.myunsplashclone.model.service.IUserService;
import com.jaevcode.myunsplashclone.model.wrapper.UserDetail;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author @JAEVCODE
 */
@Service
public class JpaUserDetailService implements UserDetailsService {

    @Autowired
    IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findById(email);

        if (user == null) {
            throw new UsernameNotFoundException("User ".concat(user.getEmail()).concat(" does not exist"));
        }

        List<GrantedAuthority> authorities = new ArrayList();

        return new UserDetail(user.getEmail(),
                 user.getPassword(),
                 user.getName(),
                 user.getCreateAt(), true, true, true, true, authorities);
    }

}
