package com.ptit.apiquanlidiem.dto;

import com.ptit.apiquanlidiem.vo.RoleEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class ResponseLoginDto {

    private String accessToken;
    private String refreshToken;

    private RoleEnum role;


    public ResponseLoginDto() {
        super();
    }

    public ResponseLoginDto(String accessToken, String refreshToken, RoleEnum role) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.role = role;
    }

    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    public String getRefreshToken() {
        return refreshToken;
    }
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }
}
