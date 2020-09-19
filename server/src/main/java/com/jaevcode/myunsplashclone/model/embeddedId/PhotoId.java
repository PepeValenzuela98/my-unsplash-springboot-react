/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaevcode.myunsplashclone.model.embeddedId;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jaevcode.myunsplashclone.model.entity.User;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author @JAEVCODE
 */
@Embeddable
public class PhotoId implements Serializable {

    
    @Column(name = "pk_photo")
    private String id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user", referencedColumnName = "pk_email")
    private User userUnsplash;

    public PhotoId() {
    }

    public PhotoId(String id, User user) {
        this.id = id;
        this.userUnsplash = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUserUnsplash() {
        return userUnsplash;
    }

    public void setUserUnsplash(User userUnsplash) {
        this.userUnsplash = userUnsplash;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.userUnsplash);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PhotoId other = (PhotoId) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.userUnsplash, other.userUnsplash)) {
            return false;
        }
        return true;
    }

    private static final long serialVersionUID = -6794015541181395797L;

}
