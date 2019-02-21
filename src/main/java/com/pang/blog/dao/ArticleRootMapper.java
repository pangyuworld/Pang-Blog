package com.pang.blog.dao;

import com.pang.blog.entity.ArticleRoot;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleRootMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleRoot record);

    int insertSelective(ArticleRoot record);

    ArticleRoot selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleRoot record);

    int updateByPrimaryKeyWithBLOBs(ArticleRoot record);
}