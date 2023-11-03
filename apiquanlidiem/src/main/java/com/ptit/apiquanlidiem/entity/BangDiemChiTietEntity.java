package com.ptit.apiquanlidiem.entity;

import jakarta.persistence.*;

@Entity
@Table(name="bangdiemchitiet")
public class BangDiemChiTietEntity extends BaseEntity{

    @EmbeddedId
    private BangDiemChiTietKey id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="mssv",insertable = false, updatable = false)
    private SinhVienEntity sinhVien;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ma_ltc",insertable = false, updatable = false)
    private LopTinChiEntity lopTinChi;

    @Column(name="diem_he_1", nullable=true)
    private Float diemHe1;

    @Column(name="diem_he_2", nullable=true)
    private Float diemHe2;

    @Column(name="diem_he_3", nullable=true)
    private Float diemHe3;

    @Column(name="diem_he_4", nullable=true)
    private Float diemHe4;

    @Column(name="diem_he_5", nullable=true)
    private Float diemHe5;

    @Column(name="lan", nullable=false, length=2)
    private Integer lan;

    public BangDiemChiTietKey getId() {
        return id;
    }

    public void setId(BangDiemChiTietKey id) {
        this.id = id;
    }

    public SinhVienEntity getSinhVien() {
        return sinhVien;
    }

    public void setSinhVien(SinhVienEntity sinhVien) {
        this.sinhVien = sinhVien;
    }

    public LopTinChiEntity getLopTinChi() {
        return lopTinChi;
    }

    public void setLopTinChi(LopTinChiEntity lopTinChi) {
        this.lopTinChi = lopTinChi;
    }

    public Float getDiemHe1() {
        return diemHe1;
    }

    public void setDiemHe1(Float diemHe1) {
        this.diemHe1 = diemHe1;
    }

    public Float getDiemHe2() {
        return diemHe2;
    }

    public void setDiemHe2(Float diemHe2) {
        this.diemHe2 = diemHe2;
    }

    public Float getDiemHe3() {
        return diemHe3;
    }

    public void setDiemHe3(Float diemHe3) {
        this.diemHe3 = diemHe3;
    }

    public Float getDiemHe4() {
        return diemHe4;
    }

    public void setDiemHe4(Float diemHe4) {
        this.diemHe4 = diemHe4;
    }

    public Float getDiemHe5() {
        return diemHe5;
    }

    public void setDiemHe5(Float diemHe5) {
        this.diemHe5 = diemHe5;
    }

    public Integer getLan() {
        return lan;
    }

    public void setLan(Integer lan) {
        this.lan = lan;
    }


}
