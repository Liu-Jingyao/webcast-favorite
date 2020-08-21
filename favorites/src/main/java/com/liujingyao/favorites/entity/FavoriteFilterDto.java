package com.liujingyao.favorites.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

/**
 *  这里本打算使用接口或Dto实体类实现过滤favorite_id和user_id字段，但没有成功
 */

public class FavoriteFilterDto {

    private Integer favorite_id;

    private String platform;

    private String classification;

    private String name;

    private String url;

    public FavoriteFilterDto() {}

    public FavoriteFilterDto(Integer favorite_id,
                             String platform,
                             String classification,
                             String name,
                             String url) {
        this.favorite_id = favorite_id;
        this.platform = platform;
        this.classification = classification;
        this.name = name;
        this.url = url;
    }

    public Integer getFavorite_id() {
        return favorite_id;
    }

    public void setFavorite_id(Integer favorite_id) {
        this.favorite_id = favorite_id;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
