package com.example.prmproject.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Voucher")
public class Voucher {
    @PrimaryKey(autoGenerate = true)
    private int voucherId;
    private String voucherName;
    private String startDate;
    private String endDate;
    private int value;

    public Voucher() {
    }

    public Voucher(String voucherName, String startDate, String endDate, int value) {
        this.voucherName = voucherName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.value = value;
    }

    public int getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(int voucherId) {
        this.voucherId = voucherId;
    }

    public String getVoucherName() {
        return voucherName;
    }

    public void setVoucherName(String voucherName) {
        this.voucherName = voucherName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
