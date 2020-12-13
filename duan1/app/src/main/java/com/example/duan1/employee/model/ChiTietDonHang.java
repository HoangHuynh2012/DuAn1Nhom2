package com.example.duan1.employee.model;

public class ChiTietDonHang {
private String stt,tenSP,soluong,dongia,thanhtien;

    public ChiTietDonHang() {
    }

    public ChiTietDonHang(String stt, String tenSP, String soluong, String dongia, String thanhtien) {
        this.stt = stt;
        this.tenSP = tenSP;
        this.soluong = soluong;
        this.dongia = dongia;
        this.thanhtien = thanhtien;
    }

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }

    public String getDongia() {
        return dongia;
    }

    public void setDongia(String dongia) {
        this.dongia = dongia;
    }

    public String getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(String thanhtien) {
        this.thanhtien = thanhtien;
    }
}
