package com.ptit.apiquanlidiem.dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

public class MonDto {

    @NotBlank(message="Mã môn không được để trống")
    @Length(min = 3, max = 20 ,message="Mã môn phải từ 3-20 kí tự")
    private String maMon;

    @NotBlank(message="Tên môn không được để trống")
    @Length(max = 255,message="Tên môn không được vượt quá 255 kí tự")
    private String tenMon;

    @NotNull(message="Số tín chỉ không được để trống")
    @Min(value = 1, message = "Số tín chỉ phải >=1")
    @Max(value = 5, message = "Số tín chỉ phải <=5")
    private Integer soTinChi;

    @NotNull(message="Số tiết lí thuyêt không được để trống")
    @Min(value = 0, message = "Số tiết lí thuyết phải >=0")
    @Max(value = 200, message = "Số  tiết lí thuyết phải <=200")
    private Integer soTietLiThuyet;

    @NotNull(message="Số tíêt thực hành không được để trống")
    @Min(value = 0, message = "Số tiết thực hành phải >=0")
    @Max(value = 200, message = "Số  tiết thực hành phải <=200")
    private Integer soTietThucHanh;

    @NotNull(message="Hệ số 1 không được để trống")
    @Digits(integer = 1, fraction = 2,message="Phần thập phân Hệ số 1 chỉ gồm 2 chứ số ")
    @DecimalMin(value = "0.0", inclusive = false,message="Hệ số 1 phải lớn hơn 0.0")
    @DecimalMax(value = "1.0", inclusive = true,message="Hệ số 1 phải bé hơn 1.0")
    private Float heSo1;

    @NotNull(message="Hệ số 2 không được để trống")
    @Digits(integer = 1, fraction = 2,message="Phần thập phân Hệ số 2 chỉ gồm 2 chứ số ")
    @DecimalMin(value = "0.0", inclusive = false,message="Hệ số 2 phải lớn hơn 0.0")
    @DecimalMax(value = "1.0", inclusive = true,message="Hệ số 2 phải bé hơn 1.0")
    private Float heSo2;

    @NotNull(message="Hệ số 3 không được để trống")
    @Digits(integer = 1, fraction = 2,message="Phần thập phân Hệ số 3 chỉ gồm 2 chứ số ")
    @DecimalMin(value = "0.0", inclusive = false,message="Hệ số 3 phải lớn hơn 0.0")
    @DecimalMax(value = "1.0", inclusive = true,message="Hệ số 3 phải bé hơn 1.0")
    private Float heSo3;


    @Digits(integer = 1, fraction = 2,message="Phần thập phân Hệ số 4 chỉ gồm 2 chứ số ")
    @DecimalMin(value = "0.0", inclusive = true,message="Hệ số 4 phải lớn hơn 0.0")
    @DecimalMax(value = "1.0", inclusive = true,message="Hệ số 4 phải bé hơn 1.0")
    private float heSo4;


    @Digits(integer = 1, fraction = 2,message="Phần thập phân Hệ số 5 chỉ gồm 2 chứ số ")
    @DecimalMin(value = "0.0", inclusive = true,message="Hệ số 5 phải lớn hơn 0.0")
    @DecimalMax(value = "1.0", inclusive = true,message="Hệ số 5 phải bé hơn 1.0")
    private float heSo5;

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

    public float getHeSo4() {
        return heSo4;
    }

    public void setHeSo4(float heSo4) {
        this.heSo4 = heSo4;
    }

    public float getHeSo5() {
        return heSo5;
    }

    public void setHeSo5(float heSo5) {
        this.heSo5 = heSo5;
    }

    public MonDto() {
        super();
    }

}
