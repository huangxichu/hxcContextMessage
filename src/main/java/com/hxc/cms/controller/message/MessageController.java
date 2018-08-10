package com.hxc.cms.controller.message;


import com.hxc.cms.annotation.CheckLogin;
import com.hxc.cms.aspect.TokenAspect;
import com.hxc.cms.dto.Result;
import com.hxc.cms.model.Message;
import com.hxc.cms.model.UserInfo;
import com.hxc.cms.param.PageParam;
import com.hxc.cms.service.message.MessageService;
import com.hxc.cms.service.user.UserService;
import com.hxc.cms.utils.Constant;
import com.hxc.cms.utils.ObjectUtil;
import com.hxc.cms.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    @GetMapping("/message/page")
    public Result getMessages(HttpServletRequest request,
                                  @RequestParam(value = "page",required = true) Integer page,
                                  @RequestParam(value = "rows",required = true) Integer rows,
                                  @RequestParam(value = "relName",required = false) String relName,
                                  @RequestParam(value = "phone",required = false) String phone,
                                  @RequestParam(value = "sex",required = false) String sex,
                                  @RequestParam(value = "status",required = false) String status
    ){
        String token = request.getHeader(TokenAspect.TOKEN_ATTRIBUTE_NAME);
        UserInfo user = userService.getUserInfoByToken(token);
        Message messageParam = new Message();
        messageParam.setCompanyCode(user.getCompanyCode());
        if(ObjectUtil.isNotBlank(relName)){
            messageParam.setRelName(relName);
        }
        if(ObjectUtil.isNotBlank(phone)){
            messageParam.setPhone(phone);
        }
        if(ObjectUtil.isNotBlank(status)){
            messageParam.setStatus(status);
        }
        PageParam pageParam = new PageParam(ObjectUtil.numberFormat(page, Constant.PAGES),ObjectUtil.numberFormat(rows,Constant.ROWS));
        Page<Message> employees = messageService.findMessagesByPage(messageParam,pageParam);
        return ResultUtil.success(employees);
    }
    
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
