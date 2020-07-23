package com.example.kingcoffee.model;

import java.util.Date;

public class Kho {

    private String sanpham;
    private Date ngay;
    private int soluong;

    public Kho() {
    }

    public Kho(String sanpham, Date ngay, int soluong) {
        this.sanpham = sanpham;
        this.ngay = ngay;
        this.soluong = soluong;
    }

    public String getSanpham() {
        return sanpham;
    }

    public void setSanpham(String sanpham) {
        this.sanpham = sanpham;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
