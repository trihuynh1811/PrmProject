package com.example.prmproject.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Reports")
public class Report {
    @PrimaryKey(autoGenerate = true)
    private int reportId;
    private String reportName;

    public Report() {
    }

    public Report(String reportName) {
        this.reportName = reportName;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }
}
