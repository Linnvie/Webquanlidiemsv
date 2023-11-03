package com.ptit.apiquanlidiem.exception;

public class InternalServerErrorException extends RuntimeException {

    public InternalServerErrorException(String msg) {
        super(msg);
    }
}