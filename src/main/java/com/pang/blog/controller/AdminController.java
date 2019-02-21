package com.pang.blog.controller;

import com.alibaba.fastjson.JSON;
import com.pang.blog.entity.Config;
import com.pang.blog.entity.User;
import com.pang.blog.jwt.Token;
import com.pang.blog.kit.PasswordUtil;
import com.pang.blog.kit.RestJson;
import com.pang.blog.service.AdminService;
import com.pang.blog.service.ConfigService;
import com.pang.blog.service.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pang
 * @version V1.0
 * @ClassName: AdminController
 * @Package com.pang.blog.controller
 * @description: 管理员控制器
 * @date 2019/2/10 19:54
 */
@RestController
@Api(tags = "管理员控制器")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private ConfigService configService;
    @Autowired
    private TokenService tokenService;

    @ApiOperation("登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public RestJson<Map> login(String username, String password) {
        RestJson<Map> restJson = new RestJson<>();
        String salt = adminService.getSalt();
        User userTemp = adminService.getUser(username);
        if (userTemp == null) {
            restJson.setSuccess(false)
                    .setStatus(404)
                    .setMsg("用户名不存在");
            return restJson;
        }
        if (adminService.isRightPassword(password, salt)) {
            User user = adminService.getUser(username);
            String token = tokenService.getToken(user);
            Map map = new HashMap<String, String>();
            map.put("username", username);
            map.put("token", token);
            restJson.setMsg("登陆成功")
                    .setSuccess(true)
                    .setStatus(200)
                    .setData(map);
            return restJson;
        } else {
            restJson.setSuccess(false)
                    .setStatus(404)
                    .setMsg("用户名密码不正确");
            return restJson;
        }
    }


    @ApiOperation("更改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "oldUsername", value = "旧用户名", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "admin/update", method = RequestMethod.PUT)
    public RestJson updateUser(String username, String password, String oldUsername) {
        RestJson restJson = new RestJson();
        String salt = adminService.getSalt();
        String hashword = PasswordUtil.SHA256(password + salt);
        User user = new User();
        user.setSalt(salt);
        user.setHashword(hashword);
        user.setUsername(username);
        adminService.updateUser(user);
        restJson.setStatus(200)
                .setSuccess(true)
                .setMsg("更新账号密码");
        return restJson;
    }


    @Token
    @ApiOperation("查找所有配置")
    @RequestMapping(value = "admin/config", method = RequestMethod.GET)
    public RestJson<List<Config>> getAllConfig() {
        RestJson<List<Config>> restJson = new RestJson<>();
        restJson.setMsg("查找所有配置")
                .setSuccess(true)
                .setStatus(200)
                .setData(configService.getAllConfig());
        return restJson;
    }

    @Token
    @ApiOperation("更新配置")
    @ApiImplicitParam(name = "configList", value = "配置列表", required = true, paramType = "query", dataType = "List")
    @RequestMapping(value = "admin/config", method = RequestMethod.PUT)
    public RestJson<List<Config>> setConfigs(String str) {
        List<Config> configList = JSON.parseArray(str, Config.class);
        RestJson<List<Config>> restJson = new RestJson<>();
        configService.setConfigs(configList);
        restJson.setData(configList)
                .setStatus(200)
                .setSuccess(true)
                .setMsg("更新所有配置");
        return restJson;
    }
    /*@RequestMapping(value = "/addddd")
    public void sss(){
        User user=new User();
        user.setUsername("admin");
        user.setSalt("be5e0323a9195ade5f56695ed9f2eb6b036f3e6417115d0cbe2fb9d74d8740406838dc84f152014b39a2414fb3530a40bc028a9e87642bd03cf5c36a1f70801e");
        user.setHashword(PasswordUtil.SHA256("123456"+user.getSalt()));
        adminService.add(user);
    }*/
}