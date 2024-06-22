package com.example.prmproject.Model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Users", foreignKeys = @ForeignKey(entity = Role.class, parentColumns = "roleId", childColumns = "roleId"))
public class User {
    @PrimaryKey(autoGenerate = true)
    private int userId;
    private String accountName;
    private String email;
    private String avatar;
    private String password;
    private String phone;
    private boolean userStatus;
    private double userWallet;
    private int roleId;

    public User() {
    }

    public User(String accountName, String email, String password) {
        this.accountName = accountName;
        this.email = email;
        this.password = password;
    }

    public User(int userId, String accountName, String email, String avatar, String password, String phone, boolean userStatus, double userWallet, int roleId) {
        this.userId = userId;
        this.accountName = accountName;
        this.email = email;
        this.avatar = avatar;
        this.password = password;
        this.phone = phone;
        this.userStatus = userStatus;
        this.userWallet = userWallet;
        this.roleId = roleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isUserStatus() {
        return userStatus;
    }

    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }

    public double getUserWallet() {
        return userWallet;
    }

    public void setUserWallet(double userWallet) {
        this.userWallet = userWallet;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

}