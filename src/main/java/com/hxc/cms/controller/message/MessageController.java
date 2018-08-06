package com.hxc.cms.controller.message;


import com.hxc.cms.annotation.CheckLogin;
import com.hxc.cms.aspect.TokenAspect;
import com.hxc.cms.dto.Result;
import com.hxc.cms.model.Message;
import com.hxc.cms.model.UserInfo;
import com.hxc.cms.service.message.MessageService;
import com.hxc.cms.service.user.UserService;
import com.hxc.cms.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class MessageController {
    
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;
   
    @CheckLogin
    @GetMapping("/messages")
    public Result getMessage(HttpServletRequest request){
        String token = request.getHeader(TokenAspect.TOKEN_ATTRIBUTE_NAME);
        UserInfo user = userService.getUserInfoByToken(token);
        Message messageParam = new Message();
        messageParam.setCompanyCode(user.getCompanyCode());
        List<Message> news = messageService.findMessages(messageParam);
        return ResultUtil.success(news);
    }
    
    
    @CheckLogin
    @PostMapping("/message/save")
    public Result saveMessage(HttpServletRequest request,Message message){
        String token = request.getHeader(TokenAspect.TOKEN_ATTRIBUTE_NAME);
        UserInfo user = userService.getUserInfoByToken(token);
        message.setCompanyCode(user.getCompanyCode());
        messageService.save(message);
        return ResultUtil.success(message);
    }
    
    @CheckLogin
    @PutMapping("/message/update")
    public Result update(HttpServletRequest request,Message message){
        messageService.update (message);
        return ResultUtil.success(message);
    }
    
    @CheckLogin
    @DeleteMapping("/message/delete")
    public Result delete(HttpServletRequest request,Message message){
        messageService.delete(message.getId());
        return ResultUtil.success(message);
    }
    
    @CheckLogin
    @DeleteMapping("/message/deletes")
    public Result deletes(HttpServletRequest request,List<Integer> ids){
        messageService.deletes(ids);
        return ResultUtil.success(ids);
    }
}
