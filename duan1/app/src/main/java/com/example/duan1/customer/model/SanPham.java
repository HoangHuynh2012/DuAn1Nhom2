package com.example.duan1.customer.model;

public class SanPham {
    private String id,title,price,soluong,gioithieu,image;

    public SanPham(String id, String title, String price, String soluong, String gioithieu, String image) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.soluong = soluong;
        this.gioithieu = gioithieu;
        this.image = image;
    }

    public SanPham() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }

    public String getGioithieu() {
        return gioithieu;
    }

    public void setGioithieu(String gioithieu) {
        this.gioithieu = gioithieu;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
