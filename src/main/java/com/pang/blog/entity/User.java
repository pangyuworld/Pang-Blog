package com.pang.blog.entity;

import com.pang.blog.dao.UserMapper;
import com.pang.blog.kit.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class User {
    private String username;

    private String hashword;

    private String salt;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getHashword() {
        return hashword;
    }

    public void setHashword(String hashword) {
        this.hashword = hashword == null ? null : hashword.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

}