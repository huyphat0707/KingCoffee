package com.example.kingcoffee.model;

public class TheLoai {
    private String tenTheLoai;
    private int viTri;

    public TheLoai() {
    }

    public TheLoai(String tenTheLoai, int viTri) {
        this.tenTheLoai = tenTheLoai;
        this.viTri = viTri;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public int getViTri() {
        return viTri;
    }

    public void setViTri(int viTri) {
        this.viTri = viTri;
    }

    @Override
    public String toString() {
        return ""+ getTenTheLoai();
    }
}

