package com.example.kingcoffee.model;

import java.util.Date;

public class HoaDon {
    private int maHoaDon;
    private String idTable;
    private Date gio;
    private double sumMonney;
    public HoaDon() {
    }

    public HoaDon(int maHoaDon, String idTable, Date gio, double sumMonney) {
        this.maHoaDon = maHoaDon;
        this.idTable = idTable;
        this.gio = gio;
        this.sumMonney = sumMonney;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getIdTable() {
        return idTable;
    }

    public void setIdTable(String idTable) {
        this.idTable = idTable;
    }

    public Date getGio() {
        return gio;
    }

    public void setGio(Date gio) {
        this.gio = gio;
    }

    public double getSumMonney() {
        return sumMonney;
    }

    public void setSumMonney(double sumMonney) {
        this.sumMonney = sumMonney;
    }
}
