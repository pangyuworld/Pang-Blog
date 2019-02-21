package com.pang.blog.service;

import com.pang.blog.dao.ConfigMapper;
import com.pang.blog.dao.UserMapper;
import com.pang.blog.entity.User;
import com.pang.blog.kit.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pang
 * @version V1.0
 * @ClassName: AdminService
 * @Package com.pang.blog.service
 * @description: 管理员事务类
 * @date 2019/2/10 19:55
 */
@Service
public class AdminService {

    @Autowired
    private UserMapper mapper;


    public boolean isRightPassword(String password,String salt){
        String SHA256= PasswordUtil.SHA256(password+salt);
        if (SHA256.equals(mapper.getHash())){
            return true;
        }else {
            return false;
        }
    }

    public String getSalt(){
        return mapper.getSalt();
    }

    public int updateUser(User user){
        return mapper.updateUser(user);
    }

    public User getUser(String username){
        return mapper.selectUser(username);
    }
    /*public void add(User user){
        mapper.insert(user);
    }*/
}