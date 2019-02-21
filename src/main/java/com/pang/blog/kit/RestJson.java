package com.pang.blog.kit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author pang
 * @version V1.0
 * @ClassName: RestJson
 * @Package com.pang.blog.kit
 * @description: 返回的实体类，公用
 * @date 2019/1/29 12:21
 */
@Data
@Accessors(chain=true)
@ApiModel("restful风格api返回类")
public class RestJson<T> {
    @ApiModelProperty("数据")
    private T data;
    @ApiModelProperty("状态吗")
    private int status;
    @ApiModelProperty("消息")
    private String msg;
    @ApiModelProperty("是否成功")
    private boolean success;

    public RestJson() {
    }

    @Override
    public String toString() {
        return "{" +
                "\ndata: " + data +
                ",\nstatus: " + status +
                ",\nmsg: \"" + msg + '\"' +
                ",\nsuccess: " + success +
                "\n"+'}';
    }
}
