/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaevcode.myunsplashclone.model.entity;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.jaevcode.myunsplashclone.model.embeddedId.PhotoId;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author @JAEVCODE
 */
@Entity
@Table(name = "photos", schema = "public")
public class Photo implements Serializable {

    @JsonUnwrapped
    @EmbeddedId
    private PhotoId nestId;

    @NotEmpty
    private String url;

    @Length(max = 100)
    @NotEmpty
    private String label;

    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @PrePersist
    public void prePersist() {
        createAt = new Date();
    }

    public PhotoId getNestId() {
        return nestId;
    }

    public void setNestId(PhotoId nestId) {
        this.nestId = nestId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    private static final long serialVersionUID = 3502720974112210014L;

}
