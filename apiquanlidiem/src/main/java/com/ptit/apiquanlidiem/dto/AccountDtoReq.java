package com.ptit.apiquanlidiem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class AccountDtoReq extends AccountDto{

    private Long id;

    @NotBlank(message="Password không được để trống")
    @Length(min = 8,message="Password phải từ 8 kí tự trở lên")
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public AccountDtoReq() {
    }

    public AccountDtoReq(Long id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
