package com.ptit.apiquanlidiem.controller;

import com.ptit.apiquanlidiem.dto.BaseDto;
import com.ptit.apiquanlidiem.dto.LopDto;
import com.ptit.apiquanlidiem.dto.PageLopResDto;
import com.ptit.apiquanlidiem.service.LopService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1")
public class LopController {

    @Autowired
    private LopService lopService;

    @GetMapping(path="/lop/search")
    @ResponseBody
    public ResponseEntity<?> findAllMonByKeyWithPaginate(
            @RequestParam(value = "keyWord", defaultValue = "") String keyWord,
            @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
            @RequestParam(value = "perPage", defaultValue = "10") int perPage){

        PageLopResDto pageLopRes = this.lopService.findAllLopByKeyWithPaginate(keyWord,currentPage-1,perPage);

        BaseDto responseDataDto = new BaseDto();
        responseDataDto.setStatus(HttpStatus.OK.series().name());
        responseDataDto.setCode(HttpStatus.OK.value());
        responseDataDto.setData(pageLopRes);
        responseDataDto.setMessage(pageLopRes == null ? "Không có lớp nào phù hợp kết quả tìm kiếm" :"Succeed");

        return ResponseEntity.ok().body(responseDataDto);
    }

    @PostMapping("/lop")
    public ResponseEntity<BaseDto> createLTC(@RequestBody @Valid LopDto newLop) {
        lopService.save(newLop);
        return ResponseEntity.status(201).body(new BaseDto(HttpStatus.CREATED.series().name(),201,"Created succeed!"));
    }

    @PutMapping("/lop")
    public ResponseEntity<BaseDto> updateLTC(@RequestBody @Valid LopDto newLop) {
        lopService.update( newLop);
        return ResponseEntity.status(201).body(new BaseDto(HttpStatus.OK.series().name(),200,"Updated succeed!"));
    }

    @GetMapping(path="/alllop")
    public ResponseEntity<?> findAllLop(){

        return ResponseEntity.ok().body(new BaseDto(HttpStatus.OK.series().name()
                ,HttpStatus.OK.value()
                ,"Succeed"
                ,this.lopService.findAll()));
    }
}
