package com.ptit.apiquanlidiem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

public class GiangVienDto {

    @NotBlank(message="Mã giảng viên không được để trống")
    @Length(min = 8,message="Mã giảng viên phải từ 8 kí tự trở lên")
    private String maGiangVien;

    @NotBlank(message="Họ lót không được để trống")
    @Length(min = 1,message="Họ lót phải từ 1 kí tự trở lên")
    private String hoLot;

    @NotBlank(message="Password không được để trống")
    private String ten;

    @NotNull(message = "Giới tính không được để trống")
    private Boolean gioiTinh;

    @NotNull(message = "Ngaỳ sinh không được để trống")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date ngaySinh;

    private String diaChi;

    private String soTaiKhoan;


    @NotNull(message = "Mã khoa không được để trống")
    private String maKhoa;

    @NotNull(message = "Học hàm không được để trống")
    private String hocHam;

    @NotNull(message = "Học vị không được để trống")
    private String hocVi;

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

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
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
}
