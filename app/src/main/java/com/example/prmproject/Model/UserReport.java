package com.example.prmproject.Model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "UserReport", foreignKeys = {
        @ForeignKey(entity = User.class, parentColumns = "userId", childColumns = "shopId"),
        @ForeignKey(entity = User.class, parentColumns = "userId", childColumns = "userId"),
        @ForeignKey(entity = Report.class, parentColumns = "reportId", childColumns = "reportId")
})
public class UserReport {
    @PrimaryKey(autoGenerate = true)
    private int userReportId;
    private String description;
    private int shopId;
    private int userId;
    private int reportId;

    public UserReport() {
    }

    public UserReport(String description, int shopId, int userId, int reportId) {
        this.description = description;
        this.shopId = shopId;
        this.userId = userId;
        this.reportId = reportId;
    }

    public int getUserReportId() {
        return userReportId;
    }

    public void setUserReportId(int userReportId) {
        this.userReportId = userReportId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }
}
