package com.hxc.cms.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Http请求返回的外层对象
 */
public class Result<T> implements Serializable {

    private String code;

    private String message;

    private T data;



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
