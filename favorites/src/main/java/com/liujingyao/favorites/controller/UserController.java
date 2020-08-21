package com.liujingyao.favorites.controller;

import com.liujingyao.favorites.Util.Consts;
import com.liujingyao.favorites.entity.Favorite;
import com.liujingyao.favorites.entity.FavoriteFilterDto;
import com.liujingyao.favorites.entity.User;
import com.liujingyao.favorites.repository.FavoriteRepository;
import com.liujingyao.favorites.service.FavoriteService;
import com.liujingyao.favorites.service.UserService;
import com.liujingyao.favorites.Util.StringUtil;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户Controller
 */
@RequestMapping(value = "/user")
@Controller
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 用户注册
     * 使用BindingResult校验参数不为空
     */
    @PostMapping("/register")
    public String register(@Valid User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return bindingResult.getFieldError().getDefaultMessage();
        } else if(userService.findByUser_name(user.getUser_name()) != null) {
            return  "nameUsed";
        } else  {
            user.setPassword(user.getPassword());
            userService.save(user);
            return "registerSuccess";
        }
    }

    /**
     * 用户登录
     * 使用BindingResult校验参数不为空
     * 使用HttpServletRequest储存登陆状态
     */
    @PostMapping("/login")
    public String login(@Valid User user, BindingResult bindingResult, HttpServletRequest request) {
        if(bindingResult.hasErrors()) {
            return bindingResult.getFieldError().getDefaultMessage();
        }
        User currentUser= userService.findByUser_name(user.getUser_name());
        if(currentUser != null && user.getPassword().equals(currentUser.getPassword())) {
            request.getSession().setAttribute(Consts.CURRENT_USER, currentUser);
            return "loginSuccess";
        }
        return "loginfail！";
    }

    /**
     * 用户登出
     * 将session无效化
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }
}
