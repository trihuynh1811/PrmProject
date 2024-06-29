package com.example.prmproject.dto;

import com.example.prmproject.models.User;

public class LoginResponse {
    private String status;
    private User userInfo;
    private String access_token;
    private String refresh_token;

    public LoginResponse(String status, User userInfo, String access_token, String refresh_token) {
        this.status = status;
        this.userInfo = userInfo;
        this.access_token = access_token;
        this.refresh_token = refresh_token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(User userInfo) {
        this.userInfo = userInfo;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }
}
