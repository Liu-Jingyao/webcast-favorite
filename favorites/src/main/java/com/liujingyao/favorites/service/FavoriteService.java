package com.liujingyao.favorites.service;
import com.liujingyao.favorites.entity.Favorite;
import com.sun.javafx.scene.traversal.Direction;

import java.util.List;

/**
 * 收藏记录service
 */
public interface FavoriteService {
    /**
     * 查询指定用户的收藏
     */
    public List<Favorite> findByUser_id(Integer user_id);

    /**
     * 由主播id找到对应的收藏记录
     */
    public Favorite findByFavorite_Id(Integer favorite_id);

    /**
     * 根据平台或主播名查询指定用户下的收藏记录
     */
    public List<Favorite> findByUser_idAndNameLikeOrPlatformLike(Integer user_id, String keyword);

    /**
     * 添加或修改收藏记录
     */
    public void save(Favorite favorite);

    /**
     * 根据主播id删除一条收藏记录
     */
    public void delete(Integer favorite_id);
}
