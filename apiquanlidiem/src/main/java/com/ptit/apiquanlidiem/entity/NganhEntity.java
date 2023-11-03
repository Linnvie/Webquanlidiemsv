package com.ptit.apiquanlidiem.entity;

import jakarta.persistence.*;

@Entity
@Table(name="nganh")
public class NganhEntity extends BaseEntity{

    @Id
    @Column(name="ma_nganh",length=20)
    private String maNganh;

    @Column(name="ten_nganh", nullable=false)
    private String tenNganh;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="ma_khoa",insertable = false, updatable = false)
    private KhoaEntity khoa;

    public String getMaNganh() {
        return maNganh;
    }

    public void setMaNganh(String maNganh) {
        this.maNganh = maNganh;
    }

    public String getTenNganh() {
        return tenNganh;
    }

    public void setTenNganh(String tenNganh) {
        this.tenNganh = tenNganh;
    }

    public KhoaEntity getKhoa() {
        return khoa;
    }

    public void setKhoa(KhoaEntity khoa) {
        this.khoa = khoa;
    }
}
