package com.pang.blog.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("分组实体类")
public class Groups {
    @ApiModelProperty("分组id")
    private Integer id;
    @ApiModelProperty("分组名")
    private String groupName;
    @ApiModelProperty("分组排序")
    private Byte groupOrder;
    @ApiModelProperty("分组是否显示")
    private Boolean groupShow;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public Byte getGroupOrder() {
        return groupOrder;
    }

    public void setGroupOrder(Byte groupOrder) {
        this.groupOrder = groupOrder;
    }

    public Boolean getGroupShow() {
        return groupShow;
    }

    public void setGroupShow(Boolean groupShow) {
        this.groupShow = groupShow;
    }
}