package com.hxc.cms.dto;

import com.hxc.cms.model.UserInfo;

public class UserLoginToken implements java.io.Serializable{
    
    private static final long serialVersionUID = 1L;
	
	private UserInfo userInfo;
	
	private String token;
	
	public UserInfo getUserInfo() {
		return userInfo;
	}
	
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}