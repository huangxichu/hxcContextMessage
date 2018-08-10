package com.hxc.cms.enums;

import com.hxc.cms.utils.ObjectUtil;

public enum MessageStatus {

    YKLY("0","游客留言"),
    YLX("1","已联系"),
    CANCEL("9","忽略");

    private String code;

    private String name;

    MessageStatus(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public MessageStatus getMessageStatusByCode(String code){
        if(ObjectUtil.isNotBlank(code)){
            MessageStatus[] items = MessageStatus.values();
            for(MessageStatus item : items){
                if(item.getCode().equals(code)){
                    return item;
                }
            }
        }
        return null;
    }

    public String getNameByCode(String code){
        if(ObjectUtil.isNotBlank(code)){
            MessageStatus[] items = MessageStatus.values();
            for(MessageStatus item : items){
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
