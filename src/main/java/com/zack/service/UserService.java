package com.zack.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zack.pojo.LoginForm;
import com.zack.pojo.User;

public interface UserService extends IService<User> {

    IPage<User> getUserByOpr(Page<User> pages, String name);

    int saveUser(User user);

    int updateUser(User user);

    int delUser(int id);

    User checkLogin(String name, String password);

    User login(LoginForm loginForm);

    User getUserById(int id);
}
