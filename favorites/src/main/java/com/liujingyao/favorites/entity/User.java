package com.liujingyao.favorites.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 用户实体
 */

@Entity
@Table(name="user")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "hander", "fieldHandler"})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    @NotEmpty(message = "loginfail")
    @Column(length = 100)
    private String user_name;

    @NotEmpty(message = "loginfail")
    @Column(length = 100)
    private String password;

    public User() {}

    public User setUser_id(Integer user_id) {
        this.user_id = user_id;
        return this;
    }

    public User setUser_name(String user_name) {
        this.user_name = user_name;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getPassword() {
        return password;
    }

}
