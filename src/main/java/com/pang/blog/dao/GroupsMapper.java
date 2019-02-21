package com.pang.blog.dao;

import com.pang.blog.entity.Groups;

import java.util.List;

public interface GroupsMapper {
    int upadteGroupList(List<Groups> groups);
    int getNumArticle(int id);

    List<Groups> selectAllGroups();

    int deleteByPrimaryKey(Integer id);

    int insert(Groups record);

    int insertSelective(Groups record);

    Groups selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Groups record);

    int updateByPrimaryKey(Groups record);
}