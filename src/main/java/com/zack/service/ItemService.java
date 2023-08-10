package com.zack.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zack.pojo.Item;


public interface ItemService extends IService<Item> {
    IPage<Item> getItemByOpr(Page<Item> pages, String name);

    int saveItem(Item item);

    int updateItem(Item item);

    int delItem(int id);
}
