package com.example.duan1.customer.model;

public class NguoiDungMode {
    String userid,tennguoidung,username,password,sdt,gmail,imageUrl,role,diachi;

    public NguoiDungMode() {
    }

    public NguoiDungMode(String userid, String tennguoidung, String username, String password, String sdt, String gmail, String imageUrl, String role,String diachi) {
        this.userid = userid;
        this.tennguoidung = tennguoidung;
        this.username = username;
        this.password = password;
        this.sdt = sdt;
        this.gmail = gmail;
        this.imageUrl = imageUrl;
        this.role = role;
        this.diachi = diachi;
    }

    public NguoiDungMode(String userid, String tennguoidung, String username, String password, String sdt, String gmail, String imageUrl, String role) {
        this.userid = userid;
        this.tennguoidung = tennguoidung;
        this.username = username;
        this.password = password;
        this.sdt = sdt;
        this.gmail = gmail;
        this.imageUrl = imageUrl;
        this.role = role;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getTennguoidung() {
        return tennguoidung;
    }

    public void setTennguoidung(String tennguoidung) {
        this.tennguoidung = tennguoidung;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
