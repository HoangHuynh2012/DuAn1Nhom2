package com.example.duan1.employee.model;

public class HoaDonMoi {
   String oderid,nguoioder,ngaymua,diachi,ghichu,tongtien;

    public HoaDonMoi() {
    }

    public HoaDonMoi(String oderid, String nguoioder, String ngaymua, String diachi, String ghichu, String tongtien) {
        this.oderid = oderid;
        this.nguoioder = nguoioder;
        this.ngaymua = ngaymua;
        this.diachi = diachi;
        this.ghichu = ghichu;
        this.tongtien = tongtien;
    }

    public String getOderid() {
        return oderid;
    }

    public void setOderid(String oderid) {
        this.oderid = oderid;
    }

    public String getNguoioder() {
        return nguoioder;
    }

    public void setNguoioder(String nguoioder) {
        this.nguoioder = nguoioder;
    }

    public String getNgaymua() {
        return ngaymua;
    }

    public void setNgaymua(String ngaymua) {
        this.ngaymua = ngaymua;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public String getTongtien() {
        return tongtien;
    }

    public void setTongtien(String tongtien) {
        this.tongtien = tongtien;
    }
}
