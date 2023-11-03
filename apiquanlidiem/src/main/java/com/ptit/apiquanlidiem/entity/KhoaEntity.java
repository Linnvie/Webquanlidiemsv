package com.ptit.apiquanlidiem.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="khoa")
public class KhoaEntity extends BaseEntity{
    @Id
    @Column(name="ma_khoa", length=20)
    private String maKhoa;

    @Column(name="ten_khoa", nullable=false)
    private String tenKhoa;

	@OneToMany(mappedBy="khoa")
	private List<LopEntity> lopList = new ArrayList<>();

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }

    public String getTenKhoa() {
        return tenKhoa;
    }

    public void setTenKhoa(String tenKhoa) {
        this.tenKhoa = tenKhoa;
    }

    public List<LopEntity> getLopList() {
        return lopList;
    }

    public void setLopList(List<LopEntity> lopList) {
        this.lopList = lopList;
    }
}
