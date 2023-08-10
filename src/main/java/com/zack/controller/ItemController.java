package com.zack.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zack.pojo.Item;
import com.zack.pojo.User;
import com.zack.service.ItemService;
import com.zack.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/getItems/{pageNo}/{pageSize}")
    public Result getItem(
            @RequestParam(name = "name", required = false) String name,
            //@RequestParam(name = "pageNo", required = false, defaultValue = "1") int pageNo,
            @PathVariable("pageNo") int pageNo,
            //@RequestParam(name = "pageSize", required = false, defaultValue = "10")
            @PathVariable("pageSize") int pageSize
    ) {
        Page<Item> pages = new Page<Item>(pageNo, pageSize);
        IPage<Item> iPage = itemService.getItemByOpr(pages, name);

        return Result.ok(iPage);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Item item) {
        System.out.println(item);
        itemService.saveItem(item);
        return Result.ok();
    }

    @PutMapping("/edit")
    public Result edit(@RequestBody Item item) {
        System.out.println(item);
        int res = itemService.updateItem(item);
        return Result.ok();
    }

    @DeleteMapping("/del")
    public Result del(@RequestParam("id") int id) {
        int res = itemService.delItem(id);
        return Result.ok(res);
    }
}
