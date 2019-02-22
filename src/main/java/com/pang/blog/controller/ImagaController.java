package com.pang.blog.controller;

import com.pang.blog.jwt.Token;
import com.pang.blog.kit.RestJson;
import com.pang.blog.service.ImgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * @author pang
 * @version V1.0
 * @ClassName: ImagaController
 * @Package com.pang.blog.controller
 * @description: 上传图片控制器
 * @date 2019/2/21 16:09
 */
@Api(tags = "上传图片")
@RestController
public class ImagaController {
    @Autowired
    private ImgService imgService;

    @Token
    @ApiOperation("文章图片上传")
    @RequestMapping(value = "admin/article/upload",method = RequestMethod.POST)
    public RestJson<Map<String,Object>> uploadArticle(@RequestParam("file") MultipartFile file){
        return this.upload(file,"article");
    }
    @Token
    @ApiOperation("配置图片上传")
    @RequestMapping(value = "admin/config/upload",method = RequestMethod.POST)
    public RestJson<Map<String,Object>> uploadConfig(@RequestParam("file") MultipartFile file){
        return this.upload(file,"config");
    }
    private RestJson<Map<String,Object>> upload(@RequestParam("file") MultipartFile file,String type){
        RestJson<Map<String,Object>> restJson=new RestJson<>();
        try {
            Map<String,Object> map=imgService.uploadImg(file,type);
            restJson.setData(map)
                    .setMsg("上传图片成功")
                    .setSuccess(true)
                    .setStatus(200);
        } catch (IOException e) {
            e.printStackTrace();
            restJson.setData(null)
                    .setMsg("上传图片失败")
                    .setSuccess(false)
                    .setStatus(500);
        }finally {
            return restJson;
        }
    }
}
