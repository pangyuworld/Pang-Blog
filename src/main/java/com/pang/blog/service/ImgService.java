package com.pang.blog.service;

import com.pang.blog.config.ImageConfig;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author pang
 * @version V1.0
 * @ClassName: ImgService
 * @Package com.pang.blog.service
 * @description: 图片处理事务
 * @date 2019/2/21 15:36
 */
@Service
public class ImgService {
    @Autowired
    private ImageConfig config;

    public Map<String,Object> uploadImg(MultipartFile file, String relativelyPath) throws IOException {
        Map<String, Object> resMap = new HashMap<>();
        String[] IMAGE_TYPE = config.getImageType().split(",");
        String path = null;
        boolean flag = false;
        for (String type : IMAGE_TYPE) {
            if (StringUtils.endsWithIgnoreCase(file.getOriginalFilename(), type)) {
                flag = true;
                break;
            }
        }
        if (flag) {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            // 获得文件类型
            String fileType = file.getContentType();
            // 获得文件后缀名称
            String imageName = fileType.substring(fileType.indexOf("/") + 1);
            // 原名称
            String oldFileName = file.getOriginalFilename();
            // 新名称
            String newFileName = uuid + "." + imageName;
            // 年月日文件夹
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String basedir = sdf.format(new Date());
            // 进行压缩(大于4M)
            if (file.getSize() > config.getFileSize()) {
                // 重新生成
                String newUUID = UUID.randomUUID().toString().replaceAll("-", "");
                newFileName = newUUID + "." + imageName;
                path = config.getPath() +relativelyPath+ "/" + basedir + "/" + newUUID + "." + imageName;
                // 如果目录不存在则创建目录
                File oldFile = new File(path);
                if (!oldFile.exists()) {
                    oldFile.mkdirs();
                }
                file.transferTo(oldFile);
                // 压缩图片
                Thumbnails.of(oldFile).scale(config.getScaleRatio()).toFile(path);
                // 显示路径
                resMap.put("path", "/"+relativelyPath + basedir + "/" + newUUID + "." + imageName);
            } else {
                path = config.getPath() +relativelyPath+ "/" + basedir + "/" + uuid + "." + imageName;
                // 如果目录不存在则创建目录
                File uploadFile = new File(path);
                if (!uploadFile.getParentFile().exists()) {
                    uploadFile.getParentFile().mkdirs();
                }
                file.transferTo(uploadFile);
                // 显示路径
                resMap.put("path", "/" +relativelyPath+ "/"+basedir + "/" + uuid + "." + imageName);
            }
            resMap.put("oldFileName", oldFileName);
            resMap.put("newFileName", newFileName);
            resMap.put("fileSize", file.getSize());
        } else {
            resMap.put("result", "图片格式不正确,支持png|jpg|jpeg");
        }
        return resMap;
    }
}
