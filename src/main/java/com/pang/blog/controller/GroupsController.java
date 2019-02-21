package com.pang.blog.controller;

import com.alibaba.fastjson.JSON;
import com.pang.blog.entity.Config;
import com.pang.blog.entity.Groups;
import com.pang.blog.jwt.Token;
import com.pang.blog.kit.RestJson;
import com.pang.blog.service.GroupsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author pang
 * @version V1.0
 * @ClassName: GroupsController
 * @Package com.pang.blog.controller
 * @description: 分组控制器
 * @date 2019/2/1 18:43
 */
@Api(tags = "分组表")
@RestController
public class GroupsController {
    @Autowired
    private GroupsService groupsService;

    @ApiOperation( "查找所有分组")
    @RequestMapping(value = "groups",method = RequestMethod.GET)
    public RestJson<List<Groups>> getAll(){
        RestJson<List<Groups>> restJson=new RestJson<>();
        restJson.setData(groupsService.getAllGroups())
                .setStatus(200)
                .setSuccess(true)
                .setMsg("获得分组列表");
        return restJson;
    }

    @ApiOperation( "根据ID查找分组")
    @RequestMapping(value = "groups/{id}",method = RequestMethod.GET)
    public RestJson<Groups> getOneGroup(@PathVariable int id){
        RestJson<Groups> restJson=new RestJson<>();
        if (id>0) {
            restJson.setMsg("根据ID查找分组")
                    .setSuccess(true)
                    .setStatus(200)
                    .setData(groupsService.getGroupById(id));
        }else{
            restJson.setMsg("根据ID查找分组")
                    .setSuccess(true)
                    .setStatus(200)
                    .setData(null);
        }
        return restJson;
    }
    @ApiOperation( "查找分组内文章数")
    @RequestMapping(value = "groups/num/{id}",method = RequestMethod.GET)
    public RestJson<Integer> getArticleNum(@PathVariable int id){
        RestJson<Integer> restJson=new RestJson<>();
        int num=groupsService.getNumArticle(id);
        restJson.setMsg("查找分组内文章数")
                .setSuccess(true)
                .setStatus(200)
                .setData(num);
        return restJson;
    }

    @Token
    @ApiOperation("修改分组信息")
    @RequestMapping(value = "admin/group",method = RequestMethod.PUT)
    public RestJson<Integer> cupdateGroup(String str){
        List<Groups> groups = JSON.parseArray(str, Groups.class);
        RestJson<Integer> restJson=new RestJson<>();
        restJson.setData(groupsService.changeGroup(groups))
                .setStatus(200)
                .setSuccess(true)
                .setMsg("修改分组信息");
        return restJson;
    }
}
