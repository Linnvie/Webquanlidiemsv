package com.ptit.apiquanlidiem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

public class QuanTriVienDto {

    @NotBlank(message = "Mã quản trị viên không được để trống")
    @Length(min = 8, message = "Mã quản trị viên phải từ 8 kí tự trở lên")
    private String maQTV;

    @NotBlank(message = "Họ lót không được để trống")
    @Length(min = 1, message = "Họ lót phải từ 1 kí tự trở lên")
    private String hoLot;

    @NotBlank(message = "Password không được để trống")
    private String ten;

    @NotNull(message = "Giới tính không được để trống")
    private Boolean gioiTinh;

    @NotNull(message = "Ngaỳ sinh không được để trống")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date ngaySinh;

    private String diaChi;

    private String soTaiKhoan;

    @NotNull(message = "Chức vụ không được để trống")
    private String chucVu;

    public QuanTriVienDto(String maQTV, String hoLot, String ten, @NotNull(message = "Giới tính không được để trống") Boolean gioiTinh, @NotNull(message = "Ngaỳ sinh không được để trống") Date ngaySinh, String diaChi, String soTaiKhoan, @NotNull(message = "Chức vụ không được để trống") String chucVu) {
        this.maQTV = maQTV;
        this.hoLot = hoLot;
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.soTaiKhoan = soTaiKhoan;
        this.chucVu = chucVu;
    }

    public QuanTriVienDto() {
    }

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

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }
}