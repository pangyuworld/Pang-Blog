package com.pang.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
@ApiModel("文章实体类")
public class Articles {
    @ApiModelProperty("文章id")
    private Integer id;
    @ApiModelProperty("文章内容")
    private String texts;
    @ApiModelProperty("文章创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date firstTime;
    @ApiModelProperty("文章修改时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date lastTime;
    @ApiModelProperty("点击量")
    private Integer clickNum;
    @ApiModelProperty("文章分组")
    private Integer articleGroup;
    @ApiModelProperty("文章标题")
    private String title;
    @ApiModelProperty("是否显示")
    private Boolean show;

    public Boolean getShow() {
        return show;
    }

    public Articles setShow(Boolean show) {
        this.show = show;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTexts() {
        return texts;
    }

    public void setTexts(String texts) {
        this.texts = texts == null ? null : texts.trim();
    }

    public Date getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(Date firstTime) {
        this.firstTime = firstTime;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Integer getClickNum() {
        return clickNum;
    }

    public void setClickNum(Integer clickNum) {
        this.clickNum = clickNum;
    }

    public Integer getArticleGroup() {
        return articleGroup;
    }

    public void setArticleGroup(Integer articleGroup) {
        this.articleGroup = articleGroup;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }
}