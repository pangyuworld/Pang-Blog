package com.pang.blog.dao;

import com.pang.blog.entity.Config;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface ConfigMapper {
    int upadteConfigList(List<Config> configList);

    List<Config> getConfigList();

    int deleteByPrimaryKey(String blogKey);

    int insert(Config record);

    int insertSelective(Config record);

    Config selectByPrimaryKey(String blogKey);

    int updateByPrimaryKeySelective(Config record);

    int updateByPrimaryKey(Config record);
}