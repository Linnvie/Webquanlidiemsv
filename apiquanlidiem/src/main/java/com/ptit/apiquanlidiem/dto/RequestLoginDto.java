package com.ptit.apiquanlidiem.dto;

import jakarta.validation.constraints.NotNull;
import org.hibernate.MappingException;
import org.hibernate.validator.constraints.Length;

public class RequestLoginDto {

    @NotNull(message = "Username không được để trống")
    @Length(min = 5, max = 20, message = "Username không hợp lệ")
    private String username;

    @NotNull
    @Length( max = 50, message = "Password không được quá 50 kí tự")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
