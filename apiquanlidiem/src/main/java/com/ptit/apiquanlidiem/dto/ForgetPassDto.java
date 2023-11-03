package com.ptit.apiquanlidiem.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class ForgetPassDto {

    @NotBlank(message="Username không được để trống")
    @Length(min = 3, max = 20 ,message="Username không hợp lệ")
    private String username;

//    @NotBlank(message="Password mới không được để trống")
//    @Length(min = 8,message="Password phải từ 8 kí tự trở lên")
//    private String newPassword;
//
//    @NotBlank(message="Password xác nhận không được để trống")
//    @Length(min = 8,message="Password phải từ 8 kí tự trở lên")
//    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public String getNewPassword() {
//        return newPassword;
//    }
//
//    public void setNewPassword(String newPassword) {
//        this.newPassword = newPassword;
//    }
//
//    public String getConfirmPassword() {
//        return confirmPassword;
//    }
//
//    public void setConfirmPassword(String confirmPassword) {
//        this.confirmPassword = confirmPassword;
//    }
}
