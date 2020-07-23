package com.example.kingcoffee.model;

import java.util.Date;

public class NguoiDung {
    private String userName;
    private String passWord;
    private String fullname;
    private String phone;
    private Date ngaysinh;
    public NguoiDung() {
    }

    public NguoiDung(String userName, String passWord, String fullname, String phone, Date ngaysinh) {
        this.userName = userName;
        this.passWord = passWord;
        this.fullname = fullname;
        this.phone = phone;
        this.ngaysinh = ngaysinh;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    @Override
    public String toString() {
        return "Người dùng{ \nUserName: " + userName + "\nPassword: " + passWord + "\nPhone: " + phone + "\nHoTen: " + fullname + "\nNgaysinh: " + userName +"\n}";
    }
}
