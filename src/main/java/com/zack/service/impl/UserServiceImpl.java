package com.zack.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zack.mapper.UserMapper;
import com.zack.pojo.LoginForm;
import com.zack.pojo.User;
import com.zack.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional
public class UserServiceImpl
        extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Override
    public IPage<User> getUserByOpr(Page<User> pages, String name) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(name)){
            queryWrapper.like("name", name);
        }
        queryWrapper.orderByDesc("id");

        Page<User> userPage = baseMapper.selectPage(pages, queryWrapper);

        return userPage;
    }

    @Override
    public int saveUser(User user) {
        int result = baseMapper.insert(user);
        return result;
    }

    @Override
    public int updateUser(User user) {
        int result = baseMapper.updateById(user);
        return result;
    }

    @Override
    public int delUser(int id) {
        int result = baseMapper.deleteById(id);
        return result;
    }

    @Override
    public User checkLogin(String name, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("name",name)
                .eq("password",password);

        User user = baseMapper.selectOne(queryWrapper);
        return user;
    }

    @Override
    public User login(LoginForm loginForm) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",loginForm.getUsername());
        queryWrapper.eq("password",loginForm.getPassword());

        User user = baseMapper.selectOne(queryWrapper);
        return user;
    }

    @Override
    public User getUserById(int id) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        User user = baseMapper.selectOne(queryWrapper);
        return user;
    }
}
