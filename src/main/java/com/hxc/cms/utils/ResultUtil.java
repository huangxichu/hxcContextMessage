package com.hxc.cms.utils;

import com.hxc.cms.dto.Result;
import com.hxc.cms.enums.ResultEnum;

public class ResultUtil {

    public static Result success(Object object){
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMessage(ResultEnum.SUCCESS.getMessage());
        result.setData(object);
        return result;
    }

    public static Result success(){
        return success(null);
    }

    public static Result error(String code, String message){
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
    
    public static Result error(ResultEnum resultEnum){
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMessage(resultEnum.getMessage());
        return result;
    }
}
