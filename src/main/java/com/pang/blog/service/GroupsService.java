package com.pang.blog.service;

import com.pang.blog.dao.GroupsMapper;
import com.pang.blog.entity.Groups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author pang
 * @version V1.0
 * @ClassName: GroupsService
 * @Package com.pang.blog.service
 * @description: 分组事务层
 * @date 2019/2/1 18:41
 */
@Service
public class GroupsService {
    @Autowired
    private GroupsMapper mapper;

    public List<Groups> getAllGroups(){
        return mapper.selectAllGroups();
    }

    public Groups getGroupById(int id){
        return mapper.selectByPrimaryKey(id);
    }
    public int getNumArticle(int groupId){
        return mapper.getNumArticle(groupId);
    }
    public int changeGroup(List<Groups> groups){
        return mapper.upadteGroupList(groups);
    }
}
