package com.example.duan1.admin.modeladmin;

public class DoanhThu {
    private String sttDT,tenmonDT,tongDT;

    public DoanhThu() {
    }

    public DoanhThu(String sttDT, String tenmonDT, String tongDT) {
        this.sttDT = sttDT;
        this.tenmonDT = tenmonDT;
        this.tongDT = tongDT;
    }

    public String getSttDT() {
        return sttDT;
    }

    public void setSttDT(String sttDT) {
        this.sttDT = sttDT;
    }

    public String getTenmonDT() {
        return tenmonDT;
    }

    public void setTenmonDT(String tenmonDT) {
        this.tenmonDT = tenmonDT;
    }

    public String getTongDT() {
        return tongDT;
    }

    public void setTongDT(String tongDT) {
        this.tongDT = tongDT;
    }
}
