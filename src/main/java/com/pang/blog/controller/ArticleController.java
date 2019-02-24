package com.pang.blog.controller;

import com.pang.blog.entity.ArticleRoot;
import com.pang.blog.entity.Articles;
import com.pang.blog.entity.Comment;
import com.pang.blog.jwt.Token;
import com.pang.blog.kit.HtmlUtil;
import com.pang.blog.kit.IpUtil;
import com.pang.blog.kit.RestJson;
import com.pang.blog.service.ArticleService;
import com.pang.blog.service.CommetnService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author pang
 * @version V1.0
 * @ClassName: ArticleController
 * @Package com.pang.blog.controller
 * @description: 文章控制器
 * @date 2019/2/1 19:08
 */
@Api(tags = "文章列表")
@RestController
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommetnService commetnService;

    @ApiOperation("获取文章列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "perpage", value = "每页数", required = false, dataType = "int", paramType = "query")
    })
    @RequestMapping(value = "articles", method = RequestMethod.GET)
    public RestJson<List<Articles>> getAllArticles(HttpServletRequest request) {
        RestJson<List<Articles>> restJson = new RestJson<>();
        List<Articles> articlesList;
        if (request.getParameter("page") != null) {
            articlesList = articleService.getAllArticles(Integer.parseInt(request.getParameter("page")), Integer.parseInt(request
                    .getParameter("perpage")));
        } else {
            articlesList = articleService.getAllArticles();
        }
        for (int i = 0; i < articlesList.size(); i++) {
            String str = HtmlUtil.delHTMLTag(articlesList.get(i).getTexts());
            int length=str.length();
            length=length>400?400:length;
            articlesList.get(i).setTexts(str.substring(0,length));
            if (length>=400){
                articlesList.get(i).setTexts(articlesList.get(i).getTexts()+" ...");
            }
        }
        restJson.setMsg("获取文章列表")
                .setSuccess(true)
                .setStatus(200)
                .setData(articlesList);
        return restJson;
    }

    @Token
    @ApiOperation("获取文章列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "perpage", value = "每页数", required = false, dataType = "int", paramType = "query")
    })
    @RequestMapping(value = "admin/articles", method = RequestMethod.GET)
    public RestJson<List<Articles>> getRealArticles(HttpServletRequest request) {
        RestJson<List<Articles>> restJson = new RestJson<>();
        List<Articles> articlesList;
        articlesList = articleService.getRealArticles(Integer.parseInt(request.getParameter("page")), Integer.parseInt(request
                .getParameter("perpage")));
        restJson.setMsg("获取文章管理列表")
                .setSuccess(true)
                .setStatus(200)
                .setData(articlesList);
        return restJson;
    }

    @ApiOperation("根据分组获取文章列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "group", value = "分组id", required = true, dataType = "int", paramType = "path")
    })
    @RequestMapping(value = "articles/group/{group}", method = RequestMethod.GET)
    public RestJson<List<Articles>> getByGropu(@PathVariable int group) {
        RestJson<List<Articles>> restJson = new RestJson<>();
        List<Articles> articlesList = articleService.getAllArticles(group);
        for (int i = 0; i < articlesList.size(); i++) {
            String str = HtmlUtil.delHTMLTag(articlesList.get(i).getTexts());
            int length=str.length();
            length=length>400?400:length;
            articlesList.get(i).setTexts(str.substring(0,length));
            if (length>=400){
                articlesList.get(i).setTexts(articlesList.get(i).getTexts()+" ...");
            }
        }
        restJson.setData(articlesList)
                .setStatus(200)
                .setSuccess(true)
                .setMsg("根据分组获取文章列表");
        return restJson;
    }

    @ApiOperation("获取文章")
    @ApiImplicitParam(name = "id", value = "文章id", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value = "article/{id}", method = RequestMethod.GET)
    public RestJson<Articles> getById(@PathVariable int id) {
        RestJson<Articles> restJson = new RestJson<>();
        Articles articles = articleService.getById(id);
        if (articles == null) {
            restJson.setMsg("文章没有找到")
                    .setSuccess(false)
                    .setStatus(404)
                    .setData(null);
        } else {
            articles.setClickNum(articles.getClickNum() + 1);
            restJson.setMsg("获取文章")
                    .setSuccess(true)
                    .setStatus(200)
                    .setData(articles);
            articleService.addClickNum(articles);
        }
        return restJson;
    }

    @ApiOperation("获取热门文章")
    @RequestMapping(value = "/articles/click", method = RequestMethod.GET)
    public RestJson<List<Articles>> getByClickNum() {
        RestJson<List<Articles>> restJson = new RestJson<>();
        List<Articles> articlesList = articleService.getArticlesByClickNum();
        if (articlesList.size() > 0) {
            restJson.setSuccess(true)
                    .setStatus(200)
                    .setData(articlesList)
                    .setMsg("获取热门文章");
        } else {
            restJson.setMsg("获取文章失败")
                    .setData(null)
                    .setStatus(404)
                    .setSuccess(false);
        }
        return restJson;
    }

    @ApiOperation("获取文章总数")
    @RequestMapping(value = "article/sum/number", method = RequestMethod.GET)
    public RestJson<Integer> getSumNumber() {
        RestJson<Integer> restJson = new RestJson<>();
        restJson.setMsg("文章总数")
                .setSuccess(true)
                .setStatus(200)
                .setData(articleService.getSumArticles());
        return restJson;
    }

    @Token
    @ApiOperation("获取文章总数")
    @RequestMapping(value = "admin/article/sum/number", method = RequestMethod.GET)
    public RestJson<Integer> getAllSumNumber() {
        RestJson<Integer> restJson = new RestJson<>();
        restJson.setMsg("文章总数")
                .setSuccess(true)
                .setStatus(200)
                .setData(articleService.getAllSumArticles());
        return restJson;
    }


    @ApiOperation("获取评论")
    @ApiImplicitParam(name = "id", value = "文章id", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value = "comment/{articleId}", method = RequestMethod.GET)
    public RestJson<List<Comment>> getComment(@PathVariable int articleId) {
        RestJson<List<Comment>> restJson = new RestJson<>();
        List<Comment> commentList = commetnService.getCommentByArticleId(articleId);
        restJson.setMsg("文章评论列表")
                .setSuccess(true)
                .setStatus(200)
                .setData(commentList);
        return restJson;
    }

    @ApiOperation("添加评论")
    @RequestMapping(value = "comment", method = RequestMethod.POST)
    public RestJson<Comment> addComment(Comment comment, HttpServletRequest request) {
        RestJson<Comment> restJson = new RestJson<>();
        comment.setTime(new Date());
        comment.setIp(IpUtil.getIpAddr(request));
        commetnService.addComment(comment);
        restJson.setMsg("添加评论")
                .setData(comment)
                .setStatus(201)
                .setSuccess(true);
        return restJson;
    }

    @Token
    @ApiOperation("添加文章")
    @RequestMapping(value = "admin/article", method = RequestMethod.POST)
    public RestJson<Integer> addArticle(Articles article, ArticleRoot root) {
        RestJson<Integer> restJson = new RestJson<>();
        int id = articleService.addArticle(article, root);
        restJson.setMsg("添加新文章")
                .setSuccess(true)
                .setStatus(200)
                .setData(id);
        return restJson;
    }

    @Token
    @ApiOperation("修改文章显示状态")
    @RequestMapping(value = "admin/article/show", method = RequestMethod.PUT)
    public RestJson<Integer> changeShow(Articles articles) {
        RestJson<Integer> restJson = new RestJson<>();
        int id = articleService.updateArticle(articles);
        restJson.setData(id)
                .setStatus(200)
                .setSuccess(true)
                .setMsg("修改文章显示状态");
        return restJson;
    }

    @ApiOperation("文章评论数量")
    @RequestMapping(value = "article/common/{id}", method = RequestMethod.GET)
    public RestJson<Integer> getCommonNum(@PathVariable int id) {
        RestJson<Integer> restJson = new RestJson<>();
        int num = commetnService.getNum(id);
        restJson.setMsg("查找评论数")
                .setSuccess(true)
                .setStatus(200)
                .setData(num);
        return restJson;
    }

    @Token
    @ApiOperation("获得文章元数据")
    @RequestMapping(value = "admin/article/{id}", method = RequestMethod.GET)
    public RestJson<ArticleRoot> getRoot(@PathVariable int id) {
        RestJson<ArticleRoot> restJson = new RestJson<>();
        ArticleRoot articleRoot = articleService.getArticleRoot(id);
        if (articleRoot == null) {
            articleRoot = new ArticleRoot();
            articleRoot.setId(id);
            articleRoot.setMarkdown(articleService.getById(id).getTexts());
        }
        restJson.setData(articleRoot)
                .setStatus(200)
                .setSuccess(true)
                .setMsg("获得文章元数据");
        return restJson;
    }

    @Token
    @ApiOperation("修改文章内容")
    @RequestMapping(value = "admin/article", method = RequestMethod.PUT)
    public RestJson<Integer> changeArticle(Articles articles, ArticleRoot articleRoot) {
        RestJson<Integer> restJson = new RestJson<>();
        int id = articleService.updateArticle(articles, articleRoot);

        restJson.setData(id)
                .setStatus(200)
                .setSuccess(true)
                .setMsg("修改文章内容");
        return restJson;
    }

    @Token
    @ApiOperation("删除文章")
    @RequestMapping(value = "admin/article/{id}", method = RequestMethod.DELETE)
    public RestJson<String> deleteArticle(@PathVariable int id) {
        RestJson<String> restJson = new RestJson<>();
        String title = articleService.getById(id).getTitle();
        articleService.deleteArticle(id);
        restJson.setMsg("删除文章:" + title)
                .setSuccess(true)
                .setStatus(200);
        return restJson;
    }

    @Token
    @ApiOperation("删除评论")
    @RequestMapping(value = "admin/comment/{id}", method = RequestMethod.DELETE)
    public RestJson<Integer> deleteCommon(@PathVariable int id) {
        RestJson<Integer> restJson = new RestJson<>();
        restJson.setStatus(200)
                .setSuccess(true)
                .setMsg("删除评论")
                .setData(commetnService.deleteComment(id));
        return restJson;
    }

}
