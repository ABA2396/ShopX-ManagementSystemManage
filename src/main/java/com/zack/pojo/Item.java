package com.zack.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    private String name;
    private int cost;
    private int num;
}
