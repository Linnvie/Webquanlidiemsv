package com.ptit.apiquanlidiem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name="giangvien")
public class GiangVienEntity extends BaseEntity {
    @Id
    @Column(name="ma_giang_vien",length=20)
    private String maGiangVien;

    @Column(name="ho_lot", nullable=false, length=100)
    private String hoLot;

    @Column(name="ten", nullable=false, length=20)
    private String ten;

    @Column(name="gioi_tinh", nullable=false)
    private Boolean gioiTinh;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @Column(name="ngay_sinh")
    private Date ngaySinh;

    @Column(name="hoc_ham", nullable=false, length=100)
    private String hocHam;

    @Column(name="hoc_vi", nullable=false, length=100)
    private String hocVi;

    @Column(name="dia_chi", nullable=true)
    private String diaChi;

    @Column(name="so_tai_khoan", nullable=true)
    private String soTaiKhoan;

    @Column(name="ma_khoa", nullable=false, length=20)
    private String maKhoa;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="account_id")
    private AccountEntity account;

    //	@OneToMany(mappedBy="giangVien")
//	private Collection<GiangVienLTCEntity> listGVLTC = new ArrayList<>();
//
    public String getMaGiangVien() {
        return maGiangVien;
    }

    public void setMaGiangVien(String maGiangVien) {
        this.maGiangVien = maGiangVien;
    }

    public String getHoLot() {
        return hoLot;
    }

    public void setHoLot(String hoLot) {
        this.hoLot = hoLot;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Boolean getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getHocHam() {
        return hocHam;
    }

    public void setHocHam(String hocHam) {
        this.hocHam = hocHam;
    }

    public String getHocVi() {
        return hocVi;
    }

    public void setHocVi(String hocVi) {
        this.hocVi = hocVi;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoTaiKhoan() {
        return soTaiKhoan;
    }

    public void setSoTaiKhoan(String soTaiKhoan) {
        this.soTaiKhoan = soTaiKhoan;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

//	public Collection<GiangVienLTCEntity> getListGVLTC() {
//		return listGVLTC;
//	}
//
//	public void setListGVLTC(Collection<GiangVienLTCEntity> listGVLTC) {
//		this.listGVLTC = listGVLTC;
//	}



}
