package com.ptit.apiquanlidiem.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RoleEnum {
    TEACHER("TEACHER"),
    ADMIN("ADMIN"),
    STUDENT("STUDENT");

    @Getter
    private final String role;

}
