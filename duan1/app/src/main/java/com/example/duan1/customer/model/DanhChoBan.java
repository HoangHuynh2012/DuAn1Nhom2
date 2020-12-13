package com.example.duan1.customer.model;

public class DanhChoBan {
    int image;
    String title,descrip;

    public DanhChoBan(int image, String title, String descrip) {
        this.image = image;
        this.title = title;
        this.descrip = descrip;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }



}