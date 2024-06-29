package com.example.prmproject.models;

public class User {
    private int usersID;
    private String accountName;
    private String avatar;
    private String email;
    private String password;
    private String phone;
    private boolean userStatus;
    private double accountBalance;
    private String role;

    public User(int usersID, String accountName, String avatar, String email, String password, String phone, boolean userStatus, double accountBalance, String role) {
        this.usersID = usersID;
        this.accountName = accountName;
        this.avatar = avatar;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.userStatus = userStatus;
        this.accountBalance = accountBalance;
        this.role = role;
    }

    public int getUsersID() {
        return usersID;
    }

    public void setUsersID(int usersID) {
        this.usersID = usersID;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
