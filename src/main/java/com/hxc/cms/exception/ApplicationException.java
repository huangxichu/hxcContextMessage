package com.hxc.cms.exception;

import com.hxc.cms.enums.ResultEnum;

public class ApplicationException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private String code;

    public  ApplicationException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
