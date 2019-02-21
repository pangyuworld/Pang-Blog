package com.pang.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author pang
 * @version V1.0
 * @ClassName: ImageConfig
 * @Package com.pang.blog.config
 * @description: 图片上传配置
 * @date 2019/2/21 15:42
 */
@Component
public class ImageConfig {

    /** 基础路径 **/
    @Value("${web.upload-path}")
    private String path;
    /** 压缩大小 **/
    @Value("${img.fileSize}")
    private long fileSize;
    /** 压缩比例 **/
    @Value("${img.scaleRatio}")
    private double scaleRatio;
    /** 图片类型 **/
    @Value("${img.imageType}")
    private String imageType;

    public String getPath() {
        return path;
    }

    public ImageConfig setPath(String path) {
        this.path = path;
        return this;
    }

    public long getFileSize() {
        return fileSize;
    }

    public ImageConfig setFileSize(long fileSize) {
        this.fileSize = fileSize;
        return this;
    }

    public double getScaleRatio() {
        return scaleRatio;
    }

    public ImageConfig setScaleRatio(double scaleRatio) {
        this.scaleRatio = scaleRatio;
        return this;
    }

    public String getImageType() {
        return imageType;
    }

    public ImageConfig setImageType(String imageType) {
        this.imageType = imageType;
        return this;
    }
}
