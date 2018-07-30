package com.hxc.cms.enums;

import com.hxc.cms.utils.ObjectUtil;

public enum Status {

    ENABLE("1","启用"),
    DISABLE("0","停用");

    private String code;

    private String name;

    Status(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Status getStatusByCode(String code){
        if(ObjectUtil.isNotBlank(code)){
            Status[] items = Status.values();
            for(Status item : items){
                if(item.getCode().equals(code)){
                    return item;
                }
            }
        }
        return null;
    }

    public String getNameByCode(String code){
        if(ObjectUtil.isNotBlank(code)){
            Status[] items = Status.values();
            for(Status item : items){
                if(item.getCode().equals(code)){
                    return item.getName();
                }
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
