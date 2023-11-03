package com.ptit.apiquanlidiem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class AccountDto {

    private Long id;

    @NotBlank(message="Username không được để trống")
    @Length(min = 3, max = 20 ,message="Username phải từ 3-20 kí tự")
    private String username;

    @NotNull(message = "Status không được null")
    private Boolean status;

    @NotBlank(message="Role không được để trống")
    @Length(min = 3,message="Role phải từ 3 kí tự trở lên")
    private String role;

    public AccountDto() {
    }

    public AccountDto(Long id, String username, @NotNull(message = "Status không được null") Boolean status, String role) {
        this.id = id;
        this.username = username;
        this.status = status;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
