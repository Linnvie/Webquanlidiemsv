package com.ptit.apiquanlidiem.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class LopDto {

    @NotBlank(message = "Mã lớp không được để trống")
    @Length(max = 20)
    private String maLop;

    @NotBlank(message = "Tên lớp không được để trống")
    private String tenLop;

    @NotBlank(message = "Khoa không được để trống")
    @Length(max = 20)
    private String maKhoa;

//    @NotBlank(message = "Ngành không được để trống")
//    @Length(max = 20)
    private String maNganh;

    @NotBlank(message = "Niên khóa không được để trống")
    private String nienKhoa;

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

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }

    public String getMaNganh() {
        return maNganh;
    }

    public void setMaNganh(String maNganh) {
        this.maNganh = maNganh;
    }

    public String getNienKhoa() {
        return nienKhoa;
    }

    public void setNienKhoa(String nienKhoa) {
        this.nienKhoa = nienKhoa;
    }
}
