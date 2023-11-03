package com.ptit.apiquanlidiem.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="mon")
public class MonEntity extends BaseEntity{
    @Id
    @Column(name="ma_mon", length=20)
    private String maMon;

    @Column(name="ten_mon", nullable=false)
    private String tenMon;

    @Column(name="so_tin_chi", nullable=false, length=2)
    private Integer soTinChi;

    @Column(name="so_tiet_li_thuyet", nullable=false, length=3)
    private Integer soTietLiThuyet;

    @Column(name="so_tiet_thuc_hanh", nullable=false, length=3)
    private Integer soTietThucHanh;


    @Column(name="he_so_1", nullable=false)
    private Float heSo1;

    @Column(name="he_so_2", nullable=false)
    private Float heSo2;

    @Column(name="he_so_3", nullable=false)
    private Float heSo3;

    @Column(name="he_so_4", nullable=false)
    private Float heSo4;

    @Column(name="he_so_5", nullable=false)
    private Float heSo5;

	@OneToMany(mappedBy="mon")
	private List<LopTinChiEntity> lopTCList = new ArrayList<>();

    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public Integer getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(Integer soTinChi) {
        this.soTinChi = soTinChi;
    }

    public Integer getSoTietLiThuyet() {
        return soTietLiThuyet;
    }

    public void setSoTietLiThuyet(Integer soTietLiThuyet) {
        this.soTietLiThuyet = soTietLiThuyet;
    }

    public Integer getSoTietThucHanh() {
        return soTietThucHanh;
    }

    public void setSoTietThucHanh(Integer soTietThucHanh) {
        this.soTietThucHanh = soTietThucHanh;
    }

    public Float getHeSo1() {
        return heSo1;
    }

    public void setHeSo1(Float heSo1) {
        this.heSo1 = heSo1;
    }

    public Float getHeSo2() {
        return heSo2;
    }

    public void setHeSo2(Float heSo2) {
        this.heSo2 = heSo2;
    }

    public Float getHeSo3() {
        return heSo3;
    }

    public void setHeSo3(Float heSo3) {
        this.heSo3 = heSo3;
    }

    public Float getHeSo4() {
        return heSo4;
    }

    public void setHeSo4(Float heSo4) {
        this.heSo4 = heSo4;
    }

    public Float getHeSo5() {
        return heSo5;
    }

    public void setHeSo5(Float heSo5) {
        this.heSo5 = heSo5;
    }

    public List<LopTinChiEntity> getLopTCList() {
        return lopTCList;
    }

    public void setLopTCList(List<LopTinChiEntity> lopTCList) {
        this.lopTCList = lopTCList;
    }
}
