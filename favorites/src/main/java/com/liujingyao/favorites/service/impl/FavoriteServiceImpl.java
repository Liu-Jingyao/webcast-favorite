package com.liujingyao.favorites.service.impl;

import com.liujingyao.favorites.entity.Favorite;
import com.liujingyao.favorites.entity.FavoriteFilterDto;
import com.liujingyao.favorites.repository.FavoriteRepository;
import com.liujingyao.favorites.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收藏记录service实现类
 */
@Service("favoriteService")
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private FavoriteRepository favoriteRepository;

    @Override
    public List<Favorite> findByUser_id(Integer user_id) {
        return favoriteRepository.findByUser_id(user_id);
    }

    @Override
    public Favorite findByFavorite_Id(Integer favorite_id) {
        return favoriteRepository.findById(favorite_id).get();
    }

    @Override
    public List<Favorite> findByUser_idAndNameLikeOrPlatformLike(Integer user_id, String keyword) {
        return favoriteRepository.findByUser_idAndNameLikeOrPlatformLike(user_id, keyword);
    }

    @Override
    public void save(Favorite favorite) {
        favoriteRepository.save(favorite);
    }

    @Override
    public void delete(Integer id) {
        favoriteRepository.deleteById(id);
    }

}
