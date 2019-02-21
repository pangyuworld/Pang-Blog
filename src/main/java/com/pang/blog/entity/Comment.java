package com.pang.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("文章评论")
public class Comment {
    @ApiModelProperty("评论id")
    private Integer id;

    @ApiModelProperty("评论内容")
    private String commentTexts;

    @ApiModelProperty("评论人名字")
    private String username;


    @ApiModelProperty("评论人邮箱")
    private String mail;

    @ApiModelProperty("评论人ip")
    private String ip;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty("评论时间")
    private Date time;

    @ApiModelProperty("评论的文章id")
    private Integer articleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommentTexts() {
        return commentTexts;
    }

    public void setCommentTexts(String commentTexts) {
        this.commentTexts = commentTexts == null ? null : commentTexts.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }
}
