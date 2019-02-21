package com.pang.blog.service;

import com.pang.blog.dao.MessageMapper;
import com.pang.blog.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pang
 * @version V1.0
 * @ClassName: MessageService
 * @Package com.pang.blog.service
 * @description: 留言Service
 * @date 2019/1/29 14:22
 */
@Service
public class MessageService {
    @Autowired
    private MessageMapper mapper;

    /**
     *  获得所有的留言
     * @author pang
     * @date 2019/1/29
     * @param
     * @return java.util.List<com.pang.blog.entity.Message>
     */
    public List<Message> getAllMessages(){
        return mapper.selectAll();
    }

    /**
     *  分页获取留言
     * @author pang
     * @date 2019/1/29
     * @param
     * @return java.util.List<com.pang.blog.entity.Message>
     */
    public List<Message> getAllMessages(int cuurIndex,int pageSize){
        Map<String,Object> page=new HashMap<>();
        page.put("currIndex",(cuurIndex-1)*pageSize);
        page.put("pageSize",pageSize);
        return mapper.selectByPage(page);
    }

    /**
     * 根据Id获得留言
     * @author pang
     * @date 2019/1/29
     * @param id
     * @return com.pang.blog.entity.Message
     */
    public Message getById(int id){
        return mapper.selectByPrimaryKey(id);
    }
    /**
     * 添加留言
     * @author pang
     * @date 2019/1/29
     * @param message
     * @return int
     */
    public int addMessage(Message message){
        return mapper.insert(message);
    }

    /**
     *  获得全部留言数
     * @author pang
     * @date 2019/1/29
     * @param
     * @return int
     */
    public Integer getSumMessageNumber(){
        return mapper.selectSumMessage();
    }
}
