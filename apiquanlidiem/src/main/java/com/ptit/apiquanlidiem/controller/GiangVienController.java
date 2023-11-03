package com.ptit.apiquanlidiem.controller;

import com.ptit.apiquanlidiem.dto.*;
import com.ptit.apiquanlidiem.service.GiangVienService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class GiangVienController {

    @Autowired
    private GiangVienService giangVienService;

    @GetMapping(path="/giangvien")
    @ResponseBody
    public ResponseEntity<?> findOneByMssv(@RequestParam(value = "maGV") String maGV){

        return ResponseEntity.ok().body(new BaseDto(HttpStatus.OK.series().name()
                ,HttpStatus.OK.value()
                ,"Succeed"
                ,this.giangVienService.findOneByMaGV(maGV)));
    }

    @GetMapping(path="/allgiangvien")
    @ResponseBody
    public ResponseEntity<?> findAllGiangVienActive(){

        return ResponseEntity.ok().body(new BaseDto(HttpStatus.OK.series().name()
                ,HttpStatus.OK.value()
                ,"Succeed"
                ,this.giangVienService.findAllActive()));
    }


    @GetMapping(path="/giangvien/search")
    @ResponseBody
    public ResponseEntity<?> findAllGiangVienByKeyWithPaginate(
            @RequestParam(value = "keyWord", defaultValue = "") String keyWord,
            @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
            @RequestParam(value = "perPage", defaultValue = "10") int perPage){

        PageGiangVienResDto pageGVRes = this.giangVienService.findAllGiangVienByKeyWithPaginate(keyWord,currentPage-1,perPage);

        BaseDto responseDataDto = new BaseDto();
        responseDataDto.setStatus(HttpStatus.OK.series().name());
        responseDataDto.setCode(HttpStatus.OK.value());
        responseDataDto.setData(pageGVRes);
        responseDataDto.setMessage(pageGVRes == null ? "Không có giảng viên học nào phù hợp kết quả tìm kiếm" :"Succeed");

        return ResponseEntity.ok().body(responseDataDto);
    }

    @GetMapping(path="/giangvien/filter")
    @ResponseBody
    public ResponseEntity<?> findAllGiangVienFilterByKeyWithPaginate(
            @RequestParam(value = "active", defaultValue = "true") Boolean active,
            @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
            @RequestParam(value = "perPage", defaultValue = "10") int perPage){

        PageGiangVienResDto pageGVRes = this.giangVienService.findAllGiangVienFilterWithPaginate(active,currentPage-1,perPage);

        BaseDto responseDataDto = new BaseDto();
        responseDataDto.setStatus(HttpStatus.OK.series().name());
        responseDataDto.setCode(HttpStatus.OK.value());
        responseDataDto.setData(pageGVRes);
        responseDataDto.setMessage(pageGVRes == null ? "Không có giảng viên học nào" :"Succeed");

        return ResponseEntity.ok().body(responseDataDto);
    }

    @PutMapping("/giangvien")
    public ResponseEntity<BaseDto> updateSV(@RequestBody @Valid GiangVienDto newGV) {
        giangVienService.update(newGV);

        return ResponseEntity.ok()
                .body(new BaseDto(HttpStatus.OK.series().name(),200,"Chỉnh sửa thành công!"));
    }
}
