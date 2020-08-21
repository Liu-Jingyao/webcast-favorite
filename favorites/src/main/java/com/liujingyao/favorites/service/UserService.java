package com.liujingyao.favorites.service;

import com.liujingyao.favorites.entity.User;
import org.springframework.data.jpa.repository.Query;

/**
 * 用户service
 */
public interface UserService {
    /**
     * 根据用户名查找用户实体
     */
    public User findByUser_name(String user_name);

    /**
     * 添加或修改用户信息
     */
    public void save(User user);
}
