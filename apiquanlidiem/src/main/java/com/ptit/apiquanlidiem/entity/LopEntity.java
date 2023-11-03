package com.ptit.apiquanlidiem.entity;

import jakarta.persistence.*;

@Entity
@Table(name="lop")
public class LopEntity extends BaseEntity{
    @Id
    @Column(name="ma_lop",length=20)
    private String maLop;

    @Column(name="ten_lop", nullable=false)
    private String tenLop;

    @Column(name="nienKhoa", nullable=false)
    private String nienKhoa;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ma_nganh")
    private NganhEntity nganh;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ma_khoa")
    private KhoaEntity khoa;

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public String getNienKhoa() {
        return nienKhoa;
    }

    public void setNienKhoa(String nienKhoa) {
        this.nienKhoa = nienKhoa;
    }

    public KhoaEntity getKhoa() {
        return khoa;
    }

    public void setKhoa(KhoaEntity khoa) {
        this.khoa = khoa;
    }

    public NganhEntity getNganh() {
        return nganh;
    }

    public void setNganh(NganhEntity nganh) {
        this.nganh = nganh;
    }
}