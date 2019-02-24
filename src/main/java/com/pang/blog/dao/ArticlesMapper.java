package com.pang.blog.dao;

import com.pang.blog.entity.Articles;
import com.pang.blog.entity.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ArticlesMapper {
    List<Articles> selectRealList(Map<String,Object> data);

    List<Articles> selectByGroup(Map<String,Object> data);

    List<Articles> selectMaxClick();

    int selectSumArticle();

    int selectAllSumArticle();

    List<Articles> selectByPage(Map<String,Object> data);

    List<Articles> selectAllArticle();

    int deleteByPrimaryKey(Integer id);

    int insert(Articles record);

    int insertSelective(Articles record);

    Articles selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Articles record);

    int updateByPrimaryKey(Articles record);
}