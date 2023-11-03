package com.ptit.apiquanlidiem.controller;

import com.ptit.apiquanlidiem.dto.*;
import com.ptit.apiquanlidiem.service.MonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MonController {

    @Autowired
    private MonService monService;

    @GetMapping(path="/mon/search")
    @ResponseBody
    public ResponseEntity<?> findAllMonByKeyWithPaginate(
                    @RequestParam(value = "keyWord", defaultValue = "") String keyWord,
                    @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                    @RequestParam(value = "perPage", defaultValue = "10") int perPage){

        PageMonResDto pageMonRes = this.monService.findAllMonByKeyWithPaginate(keyWord,currentPage-1,perPage);

        BaseDto responseDataDto = new BaseDto();
        responseDataDto.setStatus(HttpStatus.OK.series().name());
        responseDataDto.setCode(HttpStatus.OK.value());
        responseDataDto.setData(pageMonRes);
        responseDataDto.setMessage(pageMonRes == null ? "Không có môn học nào phù hợp kết quả tìm kiếm" :"Succeed");

        return ResponseEntity.ok().body(responseDataDto);
    }

    @GetMapping(path="/mon")
    @ResponseBody
    public ResponseEntity<?> findOneByMaMon(@RequestParam(value = "maMon") String maMon){

        return ResponseEntity.ok().body(new BaseDto(HttpStatus.OK.series().name()
                                        ,HttpStatus.OK.value()
                                        ,"Succeed"
                                        ,this.monService.findOneByMaMon(maMon)));
    }

    @GetMapping(path="/allmon")
    @ResponseBody
    public ResponseEntity<?> findAllMon(){

        return ResponseEntity.ok().body(new BaseDto(HttpStatus.OK.series().name()
                ,HttpStatus.OK.value()
                ,"Succeed"
                ,this.monService.findAll()));
    }

    @PostMapping("/mon")
    public ResponseEntity<BaseDto> createMon(@RequestBody @Valid MonDto newMon) {

        monService.save(newMon);
        return ResponseEntity.status(201)
                .body(new BaseDto(HttpStatus.CREATED.series().name(),201,"Created succeed!"));
    }

    @PutMapping("/mon")
    public ResponseEntity<BaseDto> updateMon(@RequestBody @Valid MonDto newMon) {

        monService.update(newMon);
        return ResponseEntity.status(201)
                .body(new BaseDto(HttpStatus.CREATED.series().name(),200,"Updated succeed!"));
    }
}
