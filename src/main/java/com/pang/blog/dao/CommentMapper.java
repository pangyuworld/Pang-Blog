package com.pang.blog.dao;

import com.pang.blog.entity.Comment;

import java.util.List;

public interface CommentMapper {
    int deleteCommentByArticle(int id);

    int numByArticleId(int id);

    List<Comment> selectByArticleId(Integer articleId);

    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
}