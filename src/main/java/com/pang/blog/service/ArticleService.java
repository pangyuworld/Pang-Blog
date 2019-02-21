package com.pang.blog.service;

import com.pang.blog.dao.ArticleRootMapper;
import com.pang.blog.dao.ArticlesMapper;
import com.pang.blog.dao.CommentMapper;
import com.pang.blog.entity.ArticleRoot;
import com.pang.blog.entity.Articles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pang
 * @version V1.0
 * @ClassName: ArticleService
 * @Package com.pang.blog.service
 * @description: 文章事务
 * @date 2019/2/1 19:06
 */
@Service
public class ArticleService {
    @Autowired
    private ArticlesMapper mapper;
    @Autowired
    private ArticleRootMapper rootMapper;
    @Autowired
    private CommentMapper commentMapper;

    public List<Articles> getAllArticles() {
        return mapper.selectAllArticle();
    }

    public Articles getById(int id) {
        return mapper.selectByPrimaryKey(id);
    }

    public List<Articles> getAllArticles(int cuurIndex, int pageSize) {
        Map<String, Object> page = new HashMap<>();
        page.put("currIndex", (cuurIndex - 1) * pageSize);
        page.put("pageSize", pageSize);
        return mapper.selectByPage(page);
    }

    public List<Articles> getAllArticles(int group) {
        Map<String, Object> page = new HashMap<>();
        page.put("articleGroup", group);
        return mapper.selectByGroup(page);
    }

    public List<Articles> getArticlesByClickNum() {
        return mapper.selectMaxClick();
    }

    public Integer getSumArticles() {
        return mapper.selectSumArticle();
    }

    public Integer getAllSumArticles() {
        return mapper.selectAllSumArticle();
    }

    public int addClickNum(Articles articles) {
        return mapper.updateByPrimaryKey(articles);
    }

    public int addArticle(Articles article, ArticleRoot root) {
        mapper.insertSelective(article);
        root.setId(article.getId());
        rootMapper.insert(root);
        return article.getId();
    }

    public int updateArticle(Articles articles) {
        return mapper.updateByPrimaryKeySelective(articles);
    }

    public ArticleRoot getArticleRoot(int articleID){
        return rootMapper.selectByPrimaryKey(articleID);
    }

    public int updateArticle(Articles article,ArticleRoot articleRoot){
        mapper.updateByPrimaryKeySelective(article);
        rootMapper.updateByPrimaryKeyWithBLOBs(articleRoot);
        return 1;
    }

    public int deleteArticle(int id){
        mapper.deleteByPrimaryKey(id);
        rootMapper.deleteByPrimaryKey(id);
        commentMapper.deleteCommentByArticle(id);
        return 1;
    }
}