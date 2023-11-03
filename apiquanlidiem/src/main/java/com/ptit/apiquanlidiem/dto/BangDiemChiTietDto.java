package com.ptit.apiquanlidiem.dto;

import jakarta.validation.constraints.*;

public class BangDiemChiTietDto {

    private BangDiemChiTietKeyDto id;

    @Digits(integer = 2, fraction = 2,message="Phần thập phân Điểm hệ 1 chỉ gồm 2 chứ số ")
    @DecimalMin(value = "0.0", inclusive = true,message="Điểm hệ 1 phải lớn hơn 0.0")
    @DecimalMax(value = "10.0", inclusive = true,message="Điểm hệ 1 phải bé hơn 10.0")
    private Float diemHe1;

    @Digits(integer = 2, fraction = 2,message="Phần thập phân Điểm hệ 2 chỉ gồm 2 chứ số ")
    @DecimalMin(value = "0.0", inclusive = true,message="Điểm hệ 2 phải lớn hơn 0.0")
    @DecimalMax(value = "10.0", inclusive = true,message="Điểm hệ 2 phải bé hơn 10.0")
    private Float diemHe2;


    @Digits(integer = 2, fraction = 2,message="Phần thập phân Điểm hệ 3 chỉ gồm 2 chứ số ")
    @DecimalMin(value = "0.0", inclusive = true,message="Điểm hệ 3 phải lớn hơn 0.0")
    @DecimalMax(value = "10.0", inclusive = true,message="Điểm hệ 3 phải bé hơn 10.0")
    private Float diemHe3;

    @Digits(integer = 2, fraction = 2,message="Phần thập phân Điểm hệ 4 chỉ gồm 2 chứ số ")
    @DecimalMin(value = "0.0", inclusive = true,message="Điểm hệ 4 phải lớn hơn 0.0")
    @DecimalMax(value = "10.0", inclusive = true,message="Điểm hệ 4 phải bé hơn 10.0")
    private Float diemHe4;


    @Digits(integer = 2, fraction = 2,message="Phần thập phân Điểm hệ 5 chỉ gồm 2 chứ số ")
    @DecimalMin(value = "0.0", inclusive = true,message="Điểm hệ 5 phải lớn hơn 0.0")
    @DecimalMax(value = "10.0", inclusive = true,message="Điểm hệ 5 phải bé hơn 10.0")
    private Float diemHe5;

    @Min(value =0, message ="Lần học phải >=1" )
    @Max(value =100, message ="Lần học phải <=100" )
    private Integer lan;

    public BangDiemChiTietKeyDto getId() {
        return id;
    }

    public void setId(BangDiemChiTietKeyDto id) {
        this.id = id;
    }
    public Float getDiemHe1() {
        return diemHe1;
    }
    public void setDiemHe1(Float diemHe1) {
        this.diemHe1 = diemHe1;
    }
    public Float getDiemHe2() {
        return diemHe2;
    }
    public void setDiemHe2(Float diemHe2) {
        this.diemHe2 = diemHe2;
    }
    public Float getDiemHe3() {
        return diemHe3;
    }
    public void setDiemHe3(Float diemHe3) {
        this.diemHe3 = diemHe3;
    }

    public Float getDiemHe4() {
        return diemHe4;
    }

    public void setDiemHe4(Float diemHe4) {
        this.diemHe4 = diemHe4;
    }

    public Float getDiemHe5() {
        return diemHe5;
    }

    public void setDiemHe5(Float diemHe5) {
        this.diemHe5 = diemHe5;
    }
    public Integer getLan() {
        return lan;
    }
    public void setLan(Integer lan) {
        this.lan = lan;
    }
}
