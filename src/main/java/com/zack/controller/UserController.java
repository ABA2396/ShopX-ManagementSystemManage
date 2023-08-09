package com.zack.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zack.pojo.LoginForm;
import com.zack.pojo.User;
import com.zack.service.UserService;
import com.zack.utils.JwtHelper;
import com.zack.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUsers/{pageNo}/{pageSize}")
    public Result getUser(
            @RequestParam(name = "name", required = false) String name,
            //@RequestParam(name = "pageNo", required = false, defaultValue = "1") int pageNo,
            @PathVariable("pageNo") int pageNo,
            //@RequestParam(name = "pageSize", required = false, defaultValue = "10")
            @PathVariable("pageSize") int pageSize
    ) {
        Page<User> pages = new Page<User>(pageNo, pageSize);
        IPage<User> iPage = userService.getUserByOpr(pages, name);

        return Result.ok(iPage);
    }

    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        System.out.println(user);
        User user1 = userService.getOne(new QueryWrapper<User>().eq("name", user.getName()));
        if (user1 != null) {
            return Result.fail().message("用户名已存在");
        }
        userService.saveUser(user);
        return Result.ok();
    }

    @PutMapping("/edit")
    public Result edit(@RequestBody User user) {
        System.out.println(user);
        int res = userService.updateUser(user);
        return Result.ok();
    }

    @DeleteMapping("/del")
    public Result del(@RequestParam("id") int id) {
//        JSONObject jsonObject = JSONObject.parseObject(id);
//        Integer id2 = (Integer) jsonObject.get("id");
//        System.out.println("id:" + id2);

        int res = userService.delUser(id);
        return Result.ok();
    }

    @PostMapping("/login")
    public Result login(@RequestBody LoginForm loginForm) {
        User user = userService.login(loginForm);
        if (user != null) {
            //用户名密码正确
            String token = JwtHelper.createToken(Integer.valueOf(user.getId()).longValue(), user.getAuth());
            return Result.ok(token);
        } else {
            //用户名或密码有误
            return Result.fail().message("用户名或密码有误");
        }
    }

    @GetMapping("/getInfo")
    public Result getInfo(String token) {
        int id = Integer.parseInt(JwtHelper.getUserId(token).toString());
        User user = userService.getUserById(id);
        return Result.ok(user);
    }

}
