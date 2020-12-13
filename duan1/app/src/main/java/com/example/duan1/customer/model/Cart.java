package com.example.duan1.customer.model;

public class Cart {
    String id;
    String tensp, gia, soluongsp, tenkhachhang;
    String tongtien;
    public Cart() {
    }

    public Cart(String id, String tensp, String gia, String soluongsp, String tenkhachhang, String tongtien) {
        this.id = id;
        this.tensp = tensp;
        this.gia = gia;
        this.soluongsp = soluongsp;
        this.tenkhachhang = tenkhachhang;
        this.tongtien = tongtien;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getSoluongsp() {
        return soluongsp;
    }

    public void setSoluongsp(String soluongsp) {
        this.soluongsp = soluongsp;
    }

    public String getTenkhachang() {
        return tenkhachhang;
    }

    public void setTenkhachang(String tenkhachang) {
        this.tenkhachhang = tenkhachang;
    }

    public String getTongtien() {
        return tongtien;
    }

    public void setTongtien(String tongtien) {
        this.tongtien = tongtien;
    }
}

