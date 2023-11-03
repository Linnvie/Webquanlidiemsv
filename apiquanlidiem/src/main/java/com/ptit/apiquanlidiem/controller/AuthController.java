package com.ptit.apiquanlidiem.controller;

import com.ptit.apiquanlidiem.dto.BaseDto;
import com.ptit.apiquanlidiem.dto.RequestLoginDto;
import com.ptit.apiquanlidiem.dto.ResponseLoginDto;
import com.ptit.apiquanlidiem.service.impl.AuthenticationServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    @Autowired
    private AuthenticationServiceImpl authenticationService;


    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody @Valid RequestLoginDto request) throws UnsupportedEncodingException {
            ResponseLoginDto response=authenticationService.authenticate(request);

            return ResponseEntity.ok(new BaseDto(HttpStatus.OK.series().name(),200,"Succeed",response));
    }
}
