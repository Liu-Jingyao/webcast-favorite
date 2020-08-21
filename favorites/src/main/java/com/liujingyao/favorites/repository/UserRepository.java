package com.liujingyao.favorites.repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.liujingyao.favorites.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    /**
     * 根据用户名查找用户实体
     */
    @Query(value = "select * from user where user_name=?1", nativeQuery = true)
    public User findByUser_name(String user_name);

}
