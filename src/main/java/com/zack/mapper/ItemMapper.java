package com.zack.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zack.pojo.Item;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemMapper extends BaseMapper<Item> {
}
