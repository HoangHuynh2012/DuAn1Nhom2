package com.example.duan1.admin.modeladmin;

public class NhanVien {
    String id,tennguoidung,username,password,gmail,sdt,role;

    public NhanVien() {
    }

    public NhanVien(String id, String tennguoidung, String username, String password, String gmail, String sdt,String role) {
        this.id = id;
        this.tennguoidung = tennguoidung;
        this.username = username;
        this.password = password;
        this.gmail = gmail;
        this.sdt = sdt;
        this.role = role;
    }

    public void setTennguoidung(String tennguoidung) {
        this.tennguoidung = tennguoidung;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTennguoidung() {
        return tennguoidung;
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

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
