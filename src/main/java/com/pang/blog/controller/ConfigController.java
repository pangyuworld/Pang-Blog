package com.pang.blog.controller;

import com.pang.blog.kit.RestJson;
import com.pang.blog.service.ConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pang
 * @version V1.0
 * @ClassName: ConfigController
 * @Package com.pang.blog.controller
 * @description: Config控制器
 * @date 2019/1/29 12:14
 */
@RestController
@Api(tags="配置表")
public class ConfigController {
    @Autowired
    private ConfigService configService;


    @ApiOperation("获得配置")
    @ApiImplicitParam(name = "key",value = "配置名",paramType = "path",dataType = "String",required = true,example = "blog-name,blog-message")
    @RequestMapping(value = "config/{key}",method= RequestMethod.GET)
    public RestJson<String> getBlogName(@PathVariable String key){
        RestJson<String> restJson=new RestJson<>();
        String blogName=configService.getByKey(key);
        if (blogName.length()<1){
            restJson.setSuccess(false)
                    .setStatus(404)
                    .setMsg("没有找到配置");
        }else {
            restJson.setData(blogName)
                    .setMsg("获得" + key)
                    .setStatus(200)
                    .setSuccess(true);
        }
        return restJson;
    }
}
