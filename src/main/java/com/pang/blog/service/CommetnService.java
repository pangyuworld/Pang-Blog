package com.pang.blog.service;

import com.pang.blog.dao.CommentMapper;
import com.pang.blog.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author pang
 * @version V1.0
 * @ClassName: CommetnService
 * @Package com.pang.blog.service
 * @description: 文章评论事务
 * @date 2019/2/2 10:07
 */
@Service
public class CommetnService {
    @Autowired
    private CommentMapper mapper;

    public List<Comment> getCommentByArticleId(Integer articleId){
        return mapper.selectByArticleId(articleId);
    }

    public int addComment(Comment comment){
        return mapper.insert(comment);
    }

    public int getNum(int id){
        return mapper.numByArticleId(id);
    }

    public int deleteComment(int id){
        return mapper.deleteByPrimaryKey(id);
    }
}
