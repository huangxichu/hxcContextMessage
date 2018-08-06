package com.hxc.cms.enums;

public enum ResultEnum {
    UNKOWN_ERROR("-1","未知错误"),
    SUCCESS("0","成功"),
    LOGINCODE_IS_NULL("11","登录账户为空"),
    PASSWORD_IS_NULL("12","登录密码为空"),
    PASSWORD_IS_ERROR("13","登录密码错误"),
    USER_NOT_FIND("12","用户不存在"),
    TOKEN_ERROR_MESSAGE("1403","Bad or missing token!"),
    TOKEN_EXCEPTION_MESSAGE("1404","Bad or missing token!"),
    USER_LOGIN_CODE_IS_EXITS("2001","账号已经存在!")
    ;

    private String code;

    private String message;

    ResultEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
