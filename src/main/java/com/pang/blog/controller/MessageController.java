package com.pang.blog.controller;

import com.pang.blog.entity.Message;
import com.pang.blog.kit.RestJson;
import com.pang.blog.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author pang
 * @version V1.0
 * @ClassName: MessageController
 * @Package com.pang.blog.controller
 * @description: 留言控制层
 * @date 2019/1/29 14:08
 */
@Api(tags = "留言表")
@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    @ApiOperation("查找所有留言")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "perpage", value = "每页数", required = false, dataType = "int", paramType = "query")
    })
    @RequestMapping(value = "messages",method = RequestMethod.GET)
    public RestJson<List<Message>> getAllMessages(HttpServletRequest request){
        RestJson<List<Message>> restJson=new RestJson<>();
        List<Message> messageList;
        if (request.getParameter("page") != null) {
            messageList = messageService.getAllMessages(Integer.parseInt(request.getParameter("page")), Integer.parseInt(request
                    .getParameter("perpage")));
        } else {
            messageList = messageService.getAllMessages();
        }
        restJson.setMsg("查找所有留言")
                .setStatus(200)
                .setSuccess(true)
                .setData(messageList);
        return restJson;
    }

    @ApiOperation("添加留言")
    @RequestMapping(value = "message",method = RequestMethod.POST)
    public RestJson<Message> addMessage(Message message){
        RestJson<Message> restJson=new RestJson<>();
        message.setTime(new Date());
        int id=messageService.addMessage(message);
        restJson.setData(messageService.getById(id))
                .setSuccess(true)
                .setStatus(200)
                .setMsg("添加一条留言");
        return restJson;
    }

    @RequestMapping(value = "message/sum/number",method = RequestMethod.GET)
    public RestJson<Integer> getSumNumber(){
        RestJson<Integer> restJson=new RestJson<>();
        restJson.setMsg("留言总数")
                .setSuccess(true)
                .setStatus(200)
                .setData(messageService.getSumMessageNumber());
        return restJson;
    }
}
