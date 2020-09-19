/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaevcode.myunsplashclone.model.wrapper;

import java.util.Collection;
import java.util.Date;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author @JAEVCODE
 */
public class UserDetail extends User {

    private String email;

    private String name;

    private Date createAt;
    
    public UserDetail(String email, String password) {
        super("", password, false, false, false, false, null);
        this.email = email;
    }

    public UserDetail(String username, String password, String name, Date createAt, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.email = username;
        this.name = name;
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object rhs) {
        return super.equals(rhs); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return super.isCredentialsNonExpired(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isAccountNonLocked() {
        return super.isAccountNonLocked(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isAccountNonExpired() {
        return super.isAccountNonExpired(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEnabled() {
        return super.isEnabled(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUsername() {
        return super.getUsername(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPassword() {
        return super.getPassword(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return super.getAuthorities(); //To change body of generated methods, choose Tools | Templates.
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

}
