package com.ptit.apiquanlidiem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class GVLTCKey implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Column(name="ma_gv", length=20)
    private String maGV;

    @Column(name="ma_ltc",length=50)
    private String maLTC;

    public GVLTCKey() {
    }

    public GVLTCKey(String maGV, String maLTC) {
        this.maGV = maGV;
        this.maLTC = maLTC;
    }

    public String getMaGV() {
        return maGV;
    }

    public void setMaGV(String maGV) {
        this.maGV = maGV;
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




}