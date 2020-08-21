package com.liujingyao.favorites.service.impl;

import com.liujingyao.favorites.entity.User;
import com.liujingyao.favorites.repository.UserRepository;
import com.liujingyao.favorites.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户service实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUser_name(String user_name) {
        return userRepository.findByUser_name(user_name);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
