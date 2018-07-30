package com.hxc.cms.handle;

import com.hxc.cms.dto.Result;
import com.hxc.cms.enums.ResultEnum;
import com.hxc.cms.exception.ApplicationException;
import com.hxc.cms.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 */
@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        if(e instanceof ApplicationException){
            ApplicationException exception = (ApplicationException)e;
            return ResultUtil.error(exception.getCode(),exception.getMessage());
        }else{
            logger.error("系统日常={}",e);
            return ResultUtil.error(ResultEnum.UNKOWN_ERROR.getCode(),ResultEnum.UNKOWN_ERROR.getMessage());
        }
    }

}
