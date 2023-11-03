package com.ptit.apiquanlidiem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="quantrivien")
@NoArgsConstructor
@AllArgsConstructor
public class QuanTriVienEntity extends BaseEntity{
    @Id
    @Column(name="ma_qtv", length=200)
    private String maQTV;

    @Column(name="ho_lot", nullable=false, length=100)
    private String hoLot;

    @Column(name="ten", nullable=false, length=20)
    private String ten;

    @Column(name="gioi_tinh", nullable=false)
    private Boolean gioiTinh;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @Column(name="ngay_sinh")
    private Date ngaySinh;

    @Column(name="chuc_vu", nullable=false)
    private String chucVu;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="account_id")
    private AccountEntity account;

    public String getMaQTV() {
        return maQTV;
    }

    public void setMaQTV(String maQTV) {
        this.maQTV = maQTV;
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

    @Column(name="dia_chi", nullable=true)
    private String diaChi;

    @Column(name="so_tai_khoan", nullable=true)
    private String soTaiKhoan;

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
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




}
