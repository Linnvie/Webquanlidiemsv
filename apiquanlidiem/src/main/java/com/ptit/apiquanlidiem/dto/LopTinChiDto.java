package com.ptit.apiquanlidiem.dto;

import com.ptit.apiquanlidiem.entity.GiangVienLTCEntity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LopTinChiDto {

    @NotBlank(message = "Mã lớp tín chỉ không được để trống")
    @Length(max = 20)
    private String maLopTinChi;

    @NotBlank(message = "Mã môn không được để trống")
    private String maMon;

    @NotNull(message = "Học kì không được để trống")
    @Min(value = 1, message = "Một năm chỉ gồm 4 học kì")
    @Max(value = 4, message = "Một năm chỉ gồm 4 học kì")
    private int hocKi;

    @NotNull(message = "Năm không được để trống")
    private int nam;

    @NotNull(message = "Trạng thái không được để trống")
    private Boolean trangThai;

    private Boolean dong;

    @NotNull(message = "Số sinh viên tối thiểu không được để trống")
    @Min(value = 1, message = "Số sinh viên tối thiểu phải lớn hơn 1")
    @Max(value = 200, message = "Số sinh viên tối thiểu không được vượt quá 200")
    private int soSVToiThieu;

    @NotNull(message = "Số sinh viên tối đa không được để trống")
    @Min(value = 1, message = "Số sinh viên tối đa phải lớn hơn 1")
    @Max(value = 500, message = "Số sinh viên tối đa không được vượt quá 500")
    private int soSVToiDa;

    private int slDangKi;

    private List<String> listGiangVien;

    public String getMaLopTinChi() {
        return maLopTinChi;
    }

    public void setMaLopTinChi(String maLopTinChi) {
        this.maLopTinChi = maLopTinChi;
    }

    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    public int getHocKi() {
        return hocKi;
    }

    public void setHocKi(int hocKi) {
        this.hocKi = hocKi;
    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    public Boolean getDong() {
        return dong;
    }

    public void setDong(Boolean dong) {
        this.dong = dong;
    }

    public int getSoSVToiThieu() {
        return soSVToiThieu;
    }

    public void setSoSVToiThieu(int soSVToiThieu) {
        this.soSVToiThieu = soSVToiThieu;
    }

    public int getSoSVToiDa() {
        return soSVToiDa;
    }

    public void setSoSVToiDa(int soSVToiDa) {
        this.soSVToiDa = soSVToiDa;
    }

    public int getSlDangKi() {
        return slDangKi;
    }

    public void setSlDangKi(int slDangKi) {
        this.slDangKi = slDangKi;
    }

    public List<String> getListGiangVien() {
        return listGiangVien;
    }

    public void setListGiangVien(List<String> listGiangVien) {
        this.listGiangVien = listGiangVien;
    }
}
