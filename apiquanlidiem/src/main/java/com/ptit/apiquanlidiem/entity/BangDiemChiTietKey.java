package com.ptit.apiquanlidiem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class BangDiemChiTietKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name="mssv", length=20)
    private String mssv;

    @Column(name="ma_ltc", length=20)
    private String maLTC;

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public String getMaLTC() {
        return maLTC;
    }

    public void setMaLTC(String maLTC) {
        this.maLTC = maLTC;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public BangDiemChiTietKey(String mssv, String maLTC) {
        super();
        this.mssv = mssv;
        this.maLTC = maLTC;
    }

    public BangDiemChiTietKey() {
        super();
    }



}
