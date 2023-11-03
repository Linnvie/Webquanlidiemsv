package com.ptit.apiquanlidiem.controller;

import com.ptit.apiquanlidiem.dto.*;
import com.ptit.apiquanlidiem.service.BangDiemChiTietService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BangDiemChiTietController {

    @Autowired
    private BangDiemChiTietService bangDiemChiTietService;

    @GetMapping(path="/diem/{mssv}")
    @ResponseBody
    public ResponseEntity<?> findAllBangDiemCTByMSSV(
            @PathVariable String mssv){

       List<BangDiemTheoKiDto> listBangDiem = this.bangDiemChiTietService.findAllByMSSV(mssv);

        BaseDto responseDataDto = new BaseDto();
        responseDataDto.setStatus(HttpStatus.OK.series().name());
        responseDataDto.setCode(HttpStatus.OK.value());
        responseDataDto.setData(listBangDiem);
        responseDataDto.setMessage(listBangDiem == null ? "Bạn chưa đăng kí môn học nào" :"Succeed");

        return ResponseEntity.ok().body(responseDataDto);
    }

    @GetMapping(path="/diem/search/{mssv}")
    @ResponseBody
    public ResponseEntity<?> findAllBangDiemCTByMSSVAndHocKiAndNam(
            @PathVariable String mssv,
            @RequestParam(value = "nam", defaultValue = "-1") int nam,
            @RequestParam(value = "hocKi", defaultValue = "1") int hocKi){

        if(nam==-1){
            nam= LocalDate.now().getYear();
        }

        List<TTBangDiemDto> listBangDiem = this.bangDiemChiTietService.findAllByMSSVAndHKAndNam(mssv,hocKi,nam);

        BaseDto responseDataDto = new BaseDto();
        responseDataDto.setStatus(HttpStatus.OK.series().name());
        responseDataDto.setCode(HttpStatus.OK.value());
        responseDataDto.setData(listBangDiem);
        responseDataDto.setMessage(listBangDiem == null ? "Bạn chưa đăng kí môn học nào học kì này" :"Succeed");

        return ResponseEntity.ok().body(responseDataDto);
    }

    @GetMapping(path="/diem")
    @ResponseBody
    public ResponseEntity<?> findBangDiemCTByMssvAndMaLTC(
            @RequestParam String mssv, @RequestParam String maLTC){

        return ResponseEntity.ok().body(new BaseDto(HttpStatus.OK.series().name()
                ,HttpStatus.OK.value()
                ,"Succeed"
                ,this.bangDiemChiTietService.findOneByMSSVAndMaLTC(mssv,maLTC)));
    }

    @GetMapping(path="/diem/loptinchi")
    @ResponseBody
    public ResponseEntity<?> findAllBangDiemCTByMaLTC(@RequestParam String maLTC,
                 @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                @RequestParam(value = "perPage", defaultValue = "10") int perPage){

        return ResponseEntity.ok().body(new BaseDto(HttpStatus.OK.series().name()
                ,HttpStatus.OK.value()
                ,"Succeed"
                ,this.bangDiemChiTietService.findAllBangDiemCTByMaLTC(maLTC, currentPage-1, perPage)));
    }

    @PostMapping("/lop-tin-chi/đk-mon")
    public ResponseEntity<BaseDto> createLTC(@RequestBody @Valid BangDiemChiTietDto newBangDiemCT) {
        int result = this.bangDiemChiTietService.save(newBangDiemCT);
        return result==1
                ?ResponseEntity.status(201)
                .body(new BaseDto(HttpStatus.CREATED.series().name(),201,"Đăng kí thành công!"))
                :ResponseEntity.status(200)
                .body(new BaseDto(HttpStatus.OK.series().name(),200,"Đã đủ số lượng sinh viên"));
    }

    @PutMapping("/lop-tin-chi/nhap-diem")
    public ResponseEntity<BaseDto> nhapDiemLTC(@RequestBody @Valid BangDiemChiTietDto newBangDiemCT) {
        this.bangDiemChiTietService.update(newBangDiemCT);
        return ResponseEntity.ok()
                .body(new BaseDto(HttpStatus.OK.series().name(),HttpStatus.OK.value(),"Succeed"));
    }

    @PutMapping("/lop-tin-chi/nhap-diem-exel")
    public ResponseEntity<BaseDto> nhapDiemLTCFromExel(@RequestParam("fileExcel") MultipartFile fileExcel) {
        this.bangDiemChiTietService.getFromExcelFileAndSave(fileExcel);
        return ResponseEntity.ok()
                .body(new BaseDto(HttpStatus.OK.series().name(),HttpStatus.OK.value(),"Succeed"));
    }

    @GetMapping("bang-diem/excel")
    public ResponseEntity<BaseDto> generateExcelReport(
                                    @RequestParam String maLTC) throws Exception{
        byte[] excelBytes = bangDiemChiTietService.generateExcel(maLTC);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=bangdiem.xlsx");

        ByteArrayResource resource = new ByteArrayResource(excelBytes);

         ResponseEntity.ok()
                .headers(headers)
                .contentLength(excelBytes.length)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new BaseDto(HttpStatus.OK.series().name(),HttpStatus.OK.value(),"Succeed"));
        return ResponseEntity.ok()
                .body(new BaseDto(HttpStatus.OK.series().name(),HttpStatus.OK.value(),"Succeed"));
    }

}
