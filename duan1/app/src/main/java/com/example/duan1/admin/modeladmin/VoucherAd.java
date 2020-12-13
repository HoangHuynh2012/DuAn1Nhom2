package com.example.duan1.admin.modeladmin;

public class VoucherAd {
    private int image;
    private String tenKM,mota,maKM,ngaybatdau,ngayketthuc;

    public VoucherAd() {
    }

    public VoucherAd(int image, String tenKM, String mota, String maKM, String ngaybatdau, String ngayketthuc) {
        this.image = image;
        this.tenKM = tenKM;
        this.mota = mota;
        this.maKM = maKM;
        this.ngaybatdau = ngaybatdau;
        this.ngayketthuc = ngayketthuc;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTenKM() {
        return tenKM;
    }

    public void setTenKM(String tenKM) {
        this.tenKM = tenKM;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getMaKM() {
        return maKM;
    }

    public void setMaKM(String maKM) {
        this.maKM = maKM;
    }

    public String getNgaybatdau() {
        return ngaybatdau;
    }

    public void setNgaybatdau(String ngaybatdau) {
        this.ngaybatdau = ngaybatdau;
    }

    public String getNgayketthuc() {
        return ngayketthuc;
    }

    public void setNgayketthuc(String ngayketthuc) {
        this.ngayketthuc = ngayketthuc;
    }
}
