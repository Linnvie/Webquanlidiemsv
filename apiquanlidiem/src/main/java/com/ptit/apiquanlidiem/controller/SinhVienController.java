package com.ptit.apiquanlidiem.controller;

import com.ptit.apiquanlidiem.dto.BaseDto;
import com.ptit.apiquanlidiem.dto.PageLTCResDto;
import com.ptit.apiquanlidiem.dto.PageSinhVienResDto;
import com.ptit.apiquanlidiem.dto.SinhVienDtoReq;
import com.ptit.apiquanlidiem.service.SinhVienService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class SinhVienController {

    @Autowired
    private SinhVienService sinhVienService;

    @GetMapping(path="/sinhvien")
    @ResponseBody
    public ResponseEntity<?> findOneByMssv(@RequestParam(value = "mssv") String mssv){

        return ResponseEntity.ok().body(new BaseDto(HttpStatus.OK.series().name()
                ,HttpStatus.OK.value()
                ,"Succeed"
                ,this.sinhVienService.findOneByMssv(mssv)));
    }

    @GetMapping(path="/sinhvien/lop")
    @ResponseBody
    public ResponseEntity<?> findAllSinhVienByMaLop(
            @RequestParam(value = "maLop") String maLop,
            @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
            @RequestParam(value = "perPage", defaultValue = "10") int perPage){

        PageSinhVienResDto pageSVRes = this.sinhVienService.findAllSinhVienByMaLop(maLop,currentPage-1,perPage);

        BaseDto responseDataDto = new BaseDto();
        responseDataDto.setStatus(HttpStatus.OK.series().name());
        responseDataDto.setCode(HttpStatus.OK.value());
        responseDataDto.setData(pageSVRes);
        responseDataDto.setMessage(pageSVRes == null ? "Không có sinh viên nào của lớp này" :"Succeed");

        return ResponseEntity.ok().body(responseDataDto);
    }

    @GetMapping(path="/sinhvien/loptinchi")
    @ResponseBody
    public ResponseEntity<?> findAllByMaMonWithPaginate(
            @RequestParam(value = "maLTC") String maLTC,
            @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
            @RequestParam(value = "perPage", defaultValue = "10") int perPage){

        PageSinhVienResDto pageSVRes = this.sinhVienService.findAllSinhVienByMaLopTinChi(maLTC,currentPage-1,perPage);

        BaseDto responseDataDto = new BaseDto();
        responseDataDto.setStatus(HttpStatus.OK.series().name());
        responseDataDto.setCode(HttpStatus.OK.value());
        responseDataDto.setData(pageSVRes);
        responseDataDto.setMessage(pageSVRes == null ? "Không có sinh viên nào của lớp tín chỉ này" :"Succeed");

        return ResponseEntity.ok().body(responseDataDto);
    }

    @PutMapping("/sinhvien")
    public ResponseEntity<BaseDto> updateSV(@RequestBody @Valid SinhVienDtoReq newSV) {
        sinhVienService.update(newSV);

        return ResponseEntity.ok()
                .body(new BaseDto(HttpStatus.OK.series().name(),200,"Chỉnh sửa thành công!"));
    }
}
