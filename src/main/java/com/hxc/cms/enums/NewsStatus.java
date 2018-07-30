package com.hxc.cms.enums;

import com.hxc.cms.utils.ObjectUtil;

public enum NewsStatus {

    EDIT("0","编辑"),
    OPEN("1","发布"),
    CANCEL("9","作废");

    private String code;

    private String name;

    NewsStatus(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public NewsStatus getNewsStatusByCode(String code){
        if(ObjectUtil.isNotBlank(code)){
            NewsStatus[] items = NewsStatus.values();
            for(NewsStatus item : items){
                if(item.getCode().equals(code)){
                    return item;
                }
            }
        }
        return null;
    }

    public String getNameByCode(String code){
        if(ObjectUtil.isNotBlank(code)){
            NewsStatus[] items = NewsStatus.values();
            for(NewsStatus item : items){
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
