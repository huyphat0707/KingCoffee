package com.example.kingcoffee.model;

import java.util.Date;

public class Oder {

    int id;
    String tenSp;
    double giatien;
    Date ngayGoi;
    int soluong;
    String idTable;

    public Oder() {
    }

    public Oder(String tenSp, double giatien, Date ngayGoi, int soluong, String idTable) {
        this.tenSp = tenSp;
        this.giatien = giatien;
        this.ngayGoi = ngayGoi;
        this.soluong = soluong;
        this.idTable = idTable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public double getGiatien() {
        return giatien;
    }

    public void setGiatien(double giatien) {
        this.giatien = giatien;
    }

    public Date getNgayGoi() {
        return ngayGoi;
    }

    public void setNgayGoi(Date ngayGoi) {
        this.ngayGoi = ngayGoi;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getIdTable() {
        return idTable;
    }

    public void setIdTable(String idTable) {
        this.idTable = idTable;
    }
}