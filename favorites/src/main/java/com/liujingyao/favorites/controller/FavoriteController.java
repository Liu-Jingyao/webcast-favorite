package com.liujingyao.favorites.controller;

import com.liujingyao.favorites.Util.Consts;
import com.liujingyao.favorites.entity.Favorite;
import com.liujingyao.favorites.entity.User;
import com.liujingyao.favorites.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/favorites")
@Controller
public class FavoriteController {
    @Autowired
    FavoriteService favoriteService;

    /**
     * 显示用户收藏
     */
    /*@ResponseBody
    @GetMapping
    public Object favoriteList(HttpServletRequest request){
        List<Favorite> favoriteList = new ArrayList<>();
        if(!request.isRequestedSessionIdValid()) {
            return "登录后方可查看收藏！";
        }
        User user = (User) request.getSession().getAttribute(Consts.CURRENT_USER);
        favoriteList.addAll(favoriteService.findByUser_id(user.getUser_id()));
        return favoriteList.isEmpty() ? "收藏夹为空！" : favoriteList;
    }*/
    @GetMapping
    public String favoriteList(HttpServletRequest request, Model model){
        List<Favorite> favoriteList = new ArrayList<>();
        if(!request.isRequestedSessionIdValid()) {
            return "needLogin";
        }
        User user = (User) request.getSession().getAttribute(Consts.CURRENT_USER);
        favoriteList.addAll(favoriteService.findByUser_id(user.getUser_id()));
        if(favoriteList.isEmpty()) {
            model.addAttribute("user", user);
            model.addAttribute("favoriteList", null);
        } else {
            //map.put("favoriteList",favoriteList);
            model.addAttribute("user", user);
            model.addAttribute("favoriteList", favoriteList);
        }
        return "favoritesPage";
    }

    /**
     * 用户添加一条收藏
     * 使用BindingResult校验参数不为空
     */
    //@ResponseBody
    @PostMapping(value = "/add")
    public String FavoriteAdd(@Valid Favorite favorite, BindingResult bindingResult, HttpServletRequest request, Model model) {
        if(bindingResult.hasErrors()) {
            return bindingResult.getFieldError().getDefaultMessage();
        }
        if(!request.isRequestedSessionIdValid()) {
            return "needLogin";
        }
        User user = (User) request.getSession().getAttribute(Consts.CURRENT_USER);
        favorite.setUser_id(user.getUser_id());
        favoriteService.save(favorite);

        List<Favorite> favoriteList = new ArrayList<>();
        favoriteList.addAll(favoriteService.findByUser_id(user.getUser_id()));
        if(favoriteList.isEmpty()) {
            model.addAttribute("user", user);
            model.addAttribute("favoriteList", null);
        } else {
            //map.put("favoriteList",favoriteList);
            model.addAttribute("user", user);
            model.addAttribute("favoriteList", favoriteList);
        }
        return "favoritesPage";
    }

    /**
     * 用户根据关键字查询自己的收藏
     * 关键字直接写在url里
     * SQL语句"%" + keyword + "%" 实现模糊查询
     */
    /*@GetMapping("/find/{keyword}")
    public Object favoriteList(@PathVariable("keyword") String keyword, HttpServletRequest request){ */
    @GetMapping("/find")
    public String favoriteList(@RequestParam("keyword") String keyword, HttpServletRequest request,  Model model){
        List<Favorite> favoriteList = new ArrayList<>();
        if(!request.isRequestedSessionIdValid()) {
            return "needLogin";
        }
        User user = (User) request.getSession().getAttribute(Consts.CURRENT_USER);
        favoriteList.addAll(favoriteService.findByUser_idAndNameLikeOrPlatformLike(user.getUser_id(), "%" + keyword + "%"));
        if(favoriteList.isEmpty()) {
            model.addAttribute("user", user);
            model.addAttribute("favoriteList", null);
        } else {
            model.addAttribute("user", user);
            model.addAttribute("favoriteList", favoriteList);
        }
        return "favoritesPage";
    }

    /**
     * 用户根据id修改自己的收藏
     */
    @PostMapping("/update")
    public String favoriteUpdate(@RequestParam("id") Integer id, HttpServletRequest request, Model model){
        System.out.println("进入update!");
        String temp;
//        boolean flag = false;
        if(!request.isRequestedSessionIdValid()) {
            return "needlogin";
        }
        User user = (User) request.getSession().getAttribute(Consts.CURRENT_USER);
        Favorite favorite = favoriteService.findByFavorite_Id(id);
        /*if(favorite == null) {
            return "无法找到id为" + id + "的收藏记录！";
        }
        if(favorite.getUser_id() != user.getUser_id()) {
            return "只能修改自己账号下的收藏！";
        }*/
        if((temp = request.getParameter("platform")) != null) {
            favorite.setPlatform(temp);
//            flag = true;
        }
        if((temp = request.getParameter("classification")) != null) {
            favorite.setClassification(temp);
//            flag = true;

        }
        if((temp = request.getParameter("name")) != null) {
            favorite.setName(temp);
//            flag = true;
        }
        if((temp = request.getParameter("url")) != null) {
            favorite.setUrl(temp);
//            flag = true;
        }
        /*if(!flag) {
            return "请修改至少一个字段！";
        }*/
        favoriteService.save(favorite);
        List<Favorite> favoriteList = new ArrayList<>();
        favoriteList.addAll(favoriteService.findByUser_id(user.getUser_id()));
        if(favoriteList.isEmpty()) {
            model.addAttribute("user", user);
            model.addAttribute("favoriteList", null);
        } else {
            model.addAttribute("user", user);
            model.addAttribute("favoriteList", favoriteList);
        }
        return "favoritesPage";
    }


    /**
     * 用户根据id删除自己的收藏
     * id直接写在url里
     */
    @ResponseBody
//    @DeleteMapping("/delete")
    @PostMapping("/delete")
    public String favoriteDelete(@RequestParam("id") Integer id, HttpServletRequest request, Model model){
        Favorite favorite = new Favorite();
        if(!request.isRequestedSessionIdValid()) {
            return "needlogin";
        }
        User user = (User) request.getSession().getAttribute(Consts.CURRENT_USER);
        favorite = favoriteService.findByFavorite_Id(id);
        /*if(favorite == null) {
            return "无法找到id为" + id + "的收藏记录！";
        }
        if(favorite.getUser_id() != user.getUser_id()) {
            return "只能删除自己账号下的收藏！";
        }*/
        favoriteService.delete(id);
        List<Favorite> favoriteList = new ArrayList<>();
        if(!request.isRequestedSessionIdValid()) {
            return "needLogin";
        }
        favoriteList.addAll(favoriteService.findByUser_id(user.getUser_id()));
        if(favoriteList.isEmpty()) {
            model.addAttribute("user", user);
            model.addAttribute("favoriteList", null);
        } else {model.addAttribute("user", user);
            model.addAttribute("favoriteList", favoriteList);
        }
        return "favoritesPage";
    }
}
