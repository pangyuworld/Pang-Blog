package com.pang.blog.service;

import com.pang.blog.dao.ConfigMapper;
import com.pang.blog.entity.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author pang
 * @version V1.0
 * @ClassName: ConfigService
 * @Package com.pang.blog.service
 * @description: Config业务
 * @date 2019/1/29 12:09
 */
@Service
public class ConfigService {
    @Autowired
    private ConfigMapper mapper;
    public String getByKey(String key){
        Config config=mapper.selectByPrimaryKey(key);
        if (config==null){
            return "";
        }else {
            return config.getBlogValue();
        }
    }
    public List<Config> getAllConfig(){
        return mapper.getConfigList();
    }
    public int setConfigs(List<Config> configList){
        return mapper.upadteConfigList(configList);
    }
}
