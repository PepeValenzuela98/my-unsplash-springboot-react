/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaevcode.myunsplashclone.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author @JAEVCODE
 */
@Entity
@Table(name = "users", schema = "public")
public class User implements Serializable {

    @Id
    @Email
    @Column(name = "pk_email")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Length(min = 6, message = "Password must be at least 6 characters")
    @NotEmpty
    private String password;

    @Pattern(
            regexp = "^[A-Za-zÁÉÍÓÚÜÑáéíóúüñ\\s]+$",
            message = "Name not valid")
    @NotEmpty
    private String name;

    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @PrePersist
    public void prePersist() {
        createAt = new Date();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        return "User{" + "name=" + name + ", createAt=" + createAt + '}';
    }

    private static final long serialVersionUID = -1972632007905165977L;

}
