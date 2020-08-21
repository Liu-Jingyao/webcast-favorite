package com.liujingyao.favorites.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 收藏记录实体
 */
@Entity
@Table(name = "favorite")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "hander", "fieldHandler"})
public class Favorite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer favorite_id;

    @NotEmpty(message = "addfail")
    @Column(length = 100)
    private String platform;

    @NotEmpty(message = "addfail")
    @Column(length = 100)
    private String classification;

    @NotEmpty(message = "addfail")
    @Column(length = 100)
    private String name;

    @NotEmpty(message = "addfail")
    @Column(length = 200)
    private String url;

    @JoinColumn(name = "user_id")
    private Integer user_id;

    public void setFavorite_id(Integer favorite_id) {
        this.favorite_id = favorite_id;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getFavorite_id() {
        return favorite_id;
    }

    public String getPlatform() {
        return platform;
    }

    public String getClassification() {
        return classification;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public Integer getUser_id() {
        return user_id;
    }
}
