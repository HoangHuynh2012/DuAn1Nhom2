package com.example.duan1.employee.model;

public class HoaDonChiTiet {
   String tensp,giasp,soluong;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(String tensp, String giasp, String soluong) {
        this.tensp = tensp;
        this.giasp = giasp;
        this.soluong = soluong;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getGiasp() {
        return giasp;
    }

    public void setGiasp(String giasp) {
        this.giasp = giasp;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }
}
