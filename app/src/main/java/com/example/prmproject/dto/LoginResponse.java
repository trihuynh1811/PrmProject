package com.example.prmproject.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.prmproject.models.User;

public class LoginResponse implements Parcelable {
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

    protected LoginResponse(Parcel in) {
        status = in.readString();
        userInfo = in.readParcelable(User.class.getClassLoader());
        access_token = in.readString();
        refresh_token = in.readString();
    }

    public static final Creator<LoginResponse> CREATOR = new Creator<LoginResponse>() {
        @Override
        public LoginResponse createFromParcel(Parcel in) {
            return new LoginResponse(in);
        }

        @Override
        public LoginResponse[] newArray(int size) {
            return new LoginResponse[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeParcelable((Parcelable) userInfo, flags);
        dest.writeString(access_token);
        dest.writeString(refresh_token);
    }

    @Override
    public int describeContents() {
        return 0;
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

    @Override
    public String toString() {
        return "LoginResponse{" +
                "status='" + status + '\'' +
                ", userInfo=" + userInfo +
                ", access_token='" + access_token + '\'' +
                ", refresh_token='" + refresh_token + '\'' +
                '}';
    }
}
