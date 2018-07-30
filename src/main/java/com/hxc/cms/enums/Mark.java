package com.hxc.cms.enums;

import com.hxc.cms.utils.ObjectUtil;

public enum Mark {

    YES("1","是"),
    NO("0","否");

    private String code;

    private String name;

    Mark(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Mark getMarkByCode(String code){
        if(ObjectUtil.isNotBlank(code)){
            Mark[] marks = Mark.values();
            for(Mark item : marks){
                if(item.getCode().equals(code)){
                    return item;
                }
            }
        }
        return null;
    }

    public String getNameByCode(String code){
        if(ObjectUtil.isNotBlank(code)){
            Mark[] marks = Mark.values();
            for(Mark item : marks){
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
