package com.example.kingcoffee.model;

public class ThucDon {
    private String tenTheLoai;
    private String tenSanPham;
    private double giaSanPham;

    public ThucDon() {
    }

    public ThucDon(String tenTheLoai, String tenSanPham, double giaSanPham) {
        this.tenTheLoai = tenTheLoai;
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public double getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(double giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    @Override
    public String toString() {
        return ""+ getTenSanPham();
    }
}
