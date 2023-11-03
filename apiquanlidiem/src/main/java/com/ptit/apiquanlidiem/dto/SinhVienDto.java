package com.ptit.apiquanlidiem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ptit.apiquanlidiem.vo.TrangThaiHocEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class SinhVienDto {

    @NotBlank(message="Mã số sinh viên không được để trống")
    @Length(min = 8,message="Mã số sinh viên phải từ 8 kí tự trở lên")
    private String mssv;

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

   // @NotNull(message = "Mã ngành không được để trống")
    private String maNganh;

    @NotNull(message = "Mã khoa không được để trống")
    private String maKhoa;

    @NotNull(message = "Mã lớp không được để trống")
    private String maLop;

    private String nienKhoa;

    @NotNull(message = "Hệ học không được để trống")
    private String heHoc;

    private String soTaiKhoan;

    private TrangThaiHocEnum status;

    public SinhVienDto() {
    }

    public SinhVienDto(String mssv, String hoLot, String ten, @NotNull(message = "Giới tính không được để trống") Boolean gioiTinh, Date ngaySinh, String diaChi, @NotNull(message = "Mã ngành không được để trống") String maNganh, @NotNull(message = "Mã khoa không được để trống") String maKhoa, @NotNull(message = "Mã lớp không được để trống") String maLop, String nienKhoa, @NotNull(message = "Hệ học không được để trống") String heHoc) {
        this.mssv = mssv;
        this.hoLot = hoLot;
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.maNganh = maNganh;
        this.maKhoa = maKhoa;
        this.maLop = maLop;
        this.nienKhoa = nienKhoa;
        this.heHoc = heHoc;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getMaNganh() {
        return maNganh;
    }

    public void setMaNganh(String maNganh) {
        this.maNganh = maNganh;
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
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

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getNienKhoa() {
        return nienKhoa;
    }

    public void setNienKhoa(String nienKhoa) {
        this.nienKhoa = nienKhoa;
    }

    public String getHeHoc() {
        return heHoc;
    }

    public void setHeHoc(String heHoc) {
        this.heHoc = heHoc;
    }

    public TrangThaiHocEnum getStatus() {
        return status;
    }

    public void setStatus(TrangThaiHocEnum status) {
        this.status = status;
    }

    public String getSoTaiKhoan() {
        return soTaiKhoan;
    }

    public void setSoTaiKhoan(String soTaiKhoan) {
        this.soTaiKhoan = soTaiKhoan;
    }

//    public Date getBirthdayInDate() {
//        String pattern = "yyyy-MM-dd";
//        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
//
//        String yearString = this.birthday.substring(0, 4);
//        String monthString = this.birthday.substring(5, 7);
//        String dayString = this.birthday.substring(8, 10);
//
//        int year = Integer.parseInt(yearString);
//        int month = Integer.parseInt(monthString);
//        int day = Integer.parseInt(dayString);
//
//        YearMonth yearMonth = YearMonth.of(year, month);
//        int dayInMonth = yearMonth.lengthOfMonth();
//
//        if(dayInMonth < day) {
//            throw new IllegalArgumentException("Invalid birthday!");
//        }
//
//        try {
//            return dateFormat.parse(this.birthday);
//
//        } catch (ParseException e) {
//            throw new IllegalArgumentException("Date must be in the format yyyy-MM-dd");
//        }
//    }
}
