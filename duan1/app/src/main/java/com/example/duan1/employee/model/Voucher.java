package com.example.duan1.employee.model;

public class Voucher {
    private String tenKM,mota,makm,ngaybatdau,giatri;

    public Voucher() {
    }

    public Voucher(String tenKM, String mota, String makm, String ngaybatdau, String giatri) {
        this.tenKM = tenKM;
        this.mota = mota;
        this.makm = makm;
        this.ngaybatdau = ngaybatdau;
        this.giatri = giatri;
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

    public String getMakm() {
        return makm;
    }

    public void setMakm(String makm) {
        this.makm = makm;
    }

    public String getNgaybatdau() {
        return ngaybatdau;
    }

    public void setNgaybatdau(String ngaybatdau) {
        this.ngaybatdau = ngaybatdau;
    }

    public String getGiatri() {
        return giatri;
    }

    public void setGiatri(String giatri) {
        this.giatri = giatri;
    }
}
