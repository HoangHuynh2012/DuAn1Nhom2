package com.example.duan1.admin.modeladmin;

public class KhachHangMail {
    String userId, email, password, imageUrl, role;

    public KhachHangMail() {
    }

    public KhachHangMail(String userId, String email, String password, String imageUrl, String role) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.imageUrl = imageUrl;
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
