package com.ptit.apiquanlidiem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseDto {

    @JsonProperty("status")
    private String status;

    @JsonProperty("code")
    private int code;

    @JsonProperty("message")
    private Object message;

    @JsonProperty("data")
    private Object data;

    public BaseDto() {
    }

    public BaseDto(String status, int code, Object message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public BaseDto(String status, int code, Object message, Object data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
