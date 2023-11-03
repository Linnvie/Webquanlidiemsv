package com.ptit.apiquanlidiem.controller;

import com.ptit.apiquanlidiem.dto.BaseDto;
import com.ptit.apiquanlidiem.service.NganhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class NganhController {

    @Autowired
    private NganhService nganhService;

    @GetMapping(path="/allnganh")
    @ResponseBody
    public ResponseEntity<?> findAllGiangVienActive(){

        return ResponseEntity.ok().body(new BaseDto(HttpStatus.OK.series().name()
                ,HttpStatus.OK.value()
                ,"Succeed"
                ,this.nganhService.findAll()));
    }
}
