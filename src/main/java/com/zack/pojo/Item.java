package com.zack.pojo;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private Integer id;
    private String name;
    private Integer cost;
    private Integer num;
}
