package com.example.duan1.employee.model;

public class LichSu {

    private  String ma,tggiao,date,tongtien;

    public LichSu() {
    }

    public LichSu(String date, String ma, String tggiao, String tongtien ) {
        this.tongtien = tongtien;
        this.ma = ma;
        this.tggiao = tggiao;
        this.date = date;
    }

    public String getTongtien() {
        return tongtien;
    }

    public void setTongtien(String tongtien) {
        this.tongtien = tongtien;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTggiao() {
        return tggiao;
    }

    public void setTggiao(String tggiao) {
        this.tggiao = tggiao;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
