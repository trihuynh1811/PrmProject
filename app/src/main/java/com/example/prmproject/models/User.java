package com.example.prmproject.models;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private int usersID;
    private String accountName;
    private String avatar;
    private String email;
    private String password;
    private String phone;
    private boolean userStatus;
    private double accountBalance;
    private String role;
    private boolean enabled;
    private String username;


    protected User(Parcel in) {
        usersID = in.readInt();
        accountName = in.readString();
        avatar = in.readString();
        email = in.readString();
        password = in.readString();
        phone = in.readString();
        userStatus = in.readByte() != 0;
        accountBalance = in.readDouble();
        role = in.readString();
        enabled = in.readByte() != 0;
        username = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(usersID);
        dest.writeString(accountName);
        dest.writeString(avatar);
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(phone);
        dest.writeByte((byte) (userStatus ? 1 : 0));
        dest.writeDouble(accountBalance);
        dest.writeString(role);
        dest.writeByte((byte) (enabled ? 1 : 0));
        dest.writeString(username);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "usersID=" + usersID +
                ", accountName='" + accountName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", userStatus=" + userStatus +
                ", accountBalance=" + accountBalance +
                ", role='" + role + '\'' +
                ", enabled=" + enabled +
                ", username='" + username + '\'' +
                '}';
    }
}
