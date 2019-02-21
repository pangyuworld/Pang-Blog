package com.pang.blog.dao;

import com.pang.blog.entity.Message;

import java.util.List;
import java.util.Map;

public interface MessageMapper {
    int selectSumMessage();

    List<Message> selectByPage(Map<String,Object> data);

    List<Message> selectAll();

    int deleteByPrimaryKey(Integer id);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKeyWithBLOBs(Message record);

    int updateByPrimaryKey(Message record);
}