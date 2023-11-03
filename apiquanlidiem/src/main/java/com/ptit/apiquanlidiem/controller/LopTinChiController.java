package com.ptit.apiquanlidiem.controller;

import com.ptit.apiquanlidiem.dto.*;
import com.ptit.apiquanlidiem.service.LopTinChiService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class LopTinChiController {
    @Autowired
    private LopTinChiService lopTinChiService;

    @GetMapping(path="/lop-tin-chi-all")
    @ResponseBody
    public ResponseEntity<?> findAllByMaMonWithPaginate(
            @RequestParam(value = "maMon") String maMon,
            @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
            @RequestParam(value = "perPage", defaultValue = "10") int perPage){

        PageLTCResDto pageLTCRes = this.lopTinChiService.findAllByMaMonWithPaginate(maMon,currentPage-1,perPage);

        BaseDto responseDataDto = new BaseDto();
        responseDataDto.setStatus(HttpStatus.OK.series().name());
        responseDataDto.setCode(HttpStatus.OK.value());
        responseDataDto.setData(pageLTCRes);
        responseDataDto.setMessage(pageLTCRes == null ? "Không có lớp tín chỉ nào của môn này" :"Succeed");

        return ResponseEntity.ok().body(responseDataDto);
    }

    @GetMapping(path="/lop-tin-chi")
    @ResponseBody
    public ResponseEntity<?> findAllByMaMonAndHocKiAndNamWithPaginate(
            @RequestParam(value = "maMon") String maMon,
            @RequestParam(value = "hocKi", defaultValue = "1") int hocKi,
            @RequestParam(value = "nam", defaultValue = "-1") int nam,
            @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
            @RequestParam(value = "perPage", defaultValue = "10") int perPage){

        if(nam==-1){
            nam= LocalDate.now().getYear();
        }
        PageLTCResDto pageLTCRes = this.lopTinChiService.findAllByMaMonAndHocKiAndNamWithPaginate(maMon,hocKi,nam,currentPage-1,perPage);

        BaseDto responseDataDto = new BaseDto();
        responseDataDto.setStatus(HttpStatus.OK.series().name());
        responseDataDto.setCode(HttpStatus.OK.value());
        responseDataDto.setData(pageLTCRes);
        responseDataDto.setMessage(pageLTCRes == null ? "Không có lớp tín chỉ nào của môn này học kì này" :"Succeed");

        return ResponseEntity.ok().body(responseDataDto);
    }
    @GetMapping(path="/lop-tin-chi/giangvien")
    @ResponseBody
    public ResponseEntity<?> findAllByMaGVAndHocKiAndNam(
            @RequestParam(value = "maGV") String maGV,
            @RequestParam(value = "hocKi", defaultValue = "1") int hocKi,
            @RequestParam(value = "nam", defaultValue = "-1") int nam){

        if(nam==-1){
            nam= LocalDate.now().getYear();
        }
        List<ChiTietLTCResDto> listLTCRes = this.lopTinChiService.findAllByMaGVAndHKNam(maGV,hocKi,nam);

        BaseDto responseDataDto = new BaseDto();
        responseDataDto.setStatus(HttpStatus.OK.series().name());
        responseDataDto.setCode(HttpStatus.OK.value());
        responseDataDto.setData(listLTCRes);
        responseDataDto.setMessage(listLTCRes == null ? "Không có lớp tín chỉ nào học kì này" :"Succeed");

        return ResponseEntity.ok().body(responseDataDto);
    }

    @GetMapping(path="/lop-tin-chi/one")
    @ResponseBody
    public ResponseEntity<?> findOneByMaLTC(@RequestParam(value = "maLTC") String maLTC){

        return ResponseEntity.ok().body(new BaseDto(HttpStatus.OK.series().name()
                ,HttpStatus.OK.value()
                ,"Succeed"
                ,this.lopTinChiService.findChiTietByMaLopTinChi(maLTC)));
    }

    @GetMapping(path="/lop-tin-chi/search")
    @ResponseBody
    public ResponseEntity<?> findAllMonByKeyWithPaginate(
            @RequestParam(value = "keyWord", defaultValue = "") String keyWord,
            @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
            @RequestParam(value = "perPage", defaultValue = "10") int perPage){

        PageLTCResDto pageLTCRes = this.lopTinChiService.findAllLTCByKeyWithPaginate(keyWord,currentPage-1,perPage);

        BaseDto responseDataDto = new BaseDto();
        responseDataDto.setStatus(HttpStatus.OK.series().name());
        responseDataDto.setCode(HttpStatus.OK.value());
        responseDataDto.setData(pageLTCRes);
        responseDataDto.setMessage(pageLTCRes == null ? "Không có lớp tín chỉ nào phù hợp kết quả tìm kiếm" :"Succeed");

        return ResponseEntity.ok().body(responseDataDto);
    }

    @PostMapping("/lop-tin-chi")
    public ResponseEntity<BaseDto> createLTC(@RequestBody @Valid LopTinChiDto newLTC) {
        lopTinChiService.save(newLTC);
        return ResponseEntity.status(201).body(new BaseDto(HttpStatus.CREATED.series().name(),201,"Created succeed!"));
    }

    @PutMapping("/lop-tin-chi")
    public ResponseEntity<BaseDto> updateLTC(@RequestBody @Valid LopTinChiDto newLTC) {
        lopTinChiService.update( newLTC);
        return ResponseEntity.status(201).body(new BaseDto(HttpStatus.OK.series().name(),200,"Updated succeed!"));
    }
}
