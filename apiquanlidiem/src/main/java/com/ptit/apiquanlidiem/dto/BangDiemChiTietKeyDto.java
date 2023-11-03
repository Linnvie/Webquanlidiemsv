package com.ptit.apiquanlidiem.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class BangDiemChiTietKeyDto {

    @NotBlank(message = "Mã số sinh viên không được để trống")
    @Length(max = 20)
    private String mssv;

    @NotBlank(message = "Mã lớp tín chỉ không được để trống")
    @Length(max = 20)
    private String maLTC;

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }
    public String getMaLTC() {
        return maLTC;
    }

    public void setMaLTC(String maLTC) {
        this.maLTC = maLTC;
    }
    public BangDiemChiTietKeyDto(String mssv, String maLTC) {
        super();
        this.mssv = mssv;
        this.maLTC = maLTC;
    }
    public BangDiemChiTietKeyDto() {
        super();
    }
}
