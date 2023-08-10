package com.zack.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zack.mapper.ItemMapper;
import com.zack.pojo.Item;
import com.zack.service.ItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional
public class ItemServiceImpl
        extends ServiceImpl<ItemMapper, Item>
        implements ItemService {

    @Override
    public IPage<Item> getItemByOpr(Page<Item> pages, String name) {
        QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(name)){
            queryWrapper.like("name", name);
        }
        queryWrapper.orderByDesc("id");

        Page<Item> itemPage = baseMapper.selectPage(pages, queryWrapper);

        return itemPage;
    }

    @Override
    public int saveItem(Item item) {
        int result = baseMapper.insert(item);
        return result;
    }

    @Override
    public int updateItem(Item item) {
        int result = baseMapper.updateById(item);
        return result;
    }

    @Override
    public int delItem(int id) {
        int result = baseMapper.deleteById(id);
        return result;
    }
//
//    @Override
//    public User checkLogin(String name, String password) {
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper
//                .eq("name",name)
//                .eq("password",password);
//
//        User user = baseMapper.selectOne(queryWrapper);
//        return user;
//    }
//
//    @Override
//    public User login(LoginForm loginForm) {
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("name",loginForm.getUsername());
//        queryWrapper.eq("password",loginForm.getPassword());
//
//        User user = baseMapper.selectOne(queryWrapper);
//        return user;
//    }
//
//    @Override
//    public User getUserById(int id) {
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("id",id);
//        User user = baseMapper.selectOne(queryWrapper);
//        return user;
//    }
}
