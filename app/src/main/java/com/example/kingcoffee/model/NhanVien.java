package com.example.kingcoffee.model;

public class NhanVien {
    private String maNhanVien;
    private  String tenNhanVien;
    private int time;
    private double price;
    public NhanVien() {
    }

    public NhanVien(String maNhanVien, String tenNhanVien, int time, double price) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.time = time;
        this.price = price;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    @Override
    public String toString() {
        return "NhanVien{" + "\nMaNhanVien: " + maNhanVien + "\nTenNhanVien: " + tenNhanVien + "\nSoGio: " + time +
     "\nSoTien: " + price +"\n} ";
    }
}
