package com.ptit.apiquanlidiem.vo;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TrangThaiHocEnum {

    STUDYING, RESERVE, DROPOUT, COMPLETE;

    @JsonCreator
    public static TrangThaiHocEnum fromString(String value) {
        if (value != null) {
            for (TrangThaiHocEnum trangThaiHocEnum : TrangThaiHocEnum.values()) {
                if (value.equalsIgnoreCase(trangThaiHocEnum.name())) {
                    return trangThaiHocEnum;
                }
            }
        }
        throw new IllegalArgumentException("Invalid: " + value);
    }
}
