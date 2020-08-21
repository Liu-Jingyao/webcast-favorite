package com.liujingyao.favorites.repository;

import com.liujingyao.favorites.entity.Favorite;
import com.liujingyao.favorites.entity.FavoriteFilterDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer>, JpaSpecificationExecutor<Favorite> {
    /**
     * 通过用户Id查询用户收藏
     */
    @Query(value = "select * from favorite where user_id=?1", nativeQuery = true)
    List<Favorite> findByUser_id(Integer user_id);

    /**
     * 通过用户Id和关键字查询用户收藏
     */
    @Query(value = "select * from favorite where user_id=?1 and (`name` like ?2 or platform like ?2)", nativeQuery = true)
    List<Favorite> findByUser_idAndNameLikeOrPlatformLike(Integer user_id, String keyword);
}
