package com.ptit.apiquanlidiem.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class SinhVienDtoReq extends SinhVienDto{

    private AccountDtoReq account;

    public AccountDtoReq getAccount() {
        return account;
    }

    public void setAccount(AccountDtoReq account) {
        this.account = account;
    }

    public SinhVienDtoReq() {
    }


}
