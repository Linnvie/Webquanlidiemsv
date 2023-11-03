package com.ptit.apiquanlidiem.controller;

import com.ptit.apiquanlidiem.dto.*;
import com.ptit.apiquanlidiem.service.AccountService;
import com.ptit.apiquanlidiem.service.GiangVienService;
import com.ptit.apiquanlidiem.service.QuanTriVienService;
import com.ptit.apiquanlidiem.service.SinhVienService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private SinhVienService svService;

    @Autowired
    private GiangVienService gvService;

    @Autowired
    private QuanTriVienService qtvService;

    @GetMapping(path="/account")
    @ResponseBody
    public ResponseEntity<?> findOneByUsername(@RequestParam(value = "username") String username){

        return ResponseEntity.ok().body(new BaseDto(HttpStatus.OK.series().name()
                ,HttpStatus.OK.value()
                ,"Succeed"
                ,this.accountService.findOneByUsername(username)));
    }

    @PostMapping("/accountSV")
    public ResponseEntity<BaseDto> createSV(@RequestBody @Valid SinhVienDtoReq newSV) {
        svService.save(newSV);

        return ResponseEntity.status(201)
                .body(new BaseDto(HttpStatus.CREATED.series().name(),201,"Created succeed!"));
    }

    @PostMapping("/accountGV")
    public ResponseEntity<BaseDto> createGV(@RequestBody @Valid GiangVienDto newGV) {
        gvService.save(newGV);

        return ResponseEntity.status(201)
                .body(new BaseDto(HttpStatus.CREATED.series().name(),201,"Created succeed!"));
    }

    @PostMapping("/accountQTV")
    public ResponseEntity<BaseDto> createQTV(@RequestBody @Valid QuanTriVienDto newQTV) {
        qtvService.save(newQTV);

        return ResponseEntity.status(201)
                .body(new BaseDto(HttpStatus.CREATED.series().name(),201,"Created succeed!"));
    }

    @PutMapping("/accountchangePw")
    public ResponseEntity<BaseDto> changePassword(@RequestBody ChangePassDto changePassDto) {
        accountService.changePassword(changePassDto);
        return ResponseEntity.ok()
                .body(new BaseDto(HttpStatus.OK.series().name(),200,"Đổi mật khẩu thành công!"));
    }

    @PutMapping("/accountforgetPw")
    public ResponseEntity<BaseDto> forgetPassword(@RequestBody ForgetPassDto forgetPassDto) {
        accountService.forgetPassword(forgetPassDto);
        return ResponseEntity.ok()
                .body(new BaseDto(HttpStatus.OK.series().name(),200,"Đổi mật khẩu thành công vui lòng check mail để lấy mật khẩu đăng nhâp, sau khi đăng nhập thành công vui lòng đổi lại mật khẩu!"));
    }

    @PostMapping("/account")
    public ResponseEntity<BaseDto> updateAccount(@RequestBody @Valid AccountDto newAccount) {
        accountService.updateAccount(newAccount);
        return ResponseEntity.ok()
                .body(new BaseDto(HttpStatus.OK.series().name(),200,"Chỉnh sửa thành công!"));

    }

    @GetMapping(path="/quantrivien")
    @ResponseBody
    public ResponseEntity<?> findOneByMaQTV(@RequestParam(value = "maQTV") String maQTV){

        return ResponseEntity.ok().body(new BaseDto(HttpStatus.OK.series().name()
                ,HttpStatus.OK.value()
                ,"Succeed"
                ,this.qtvService.findOneByMaQTV(maQTV)));
    }
}
