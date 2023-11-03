package com.ptit.apiquanlidiem.service;

import com.ptit.apiquanlidiem.dto.PageSinhVienResDto;
import com.ptit.apiquanlidiem.dto.SinhVienDto;
import com.ptit.apiquanlidiem.dto.SinhVienDtoReq;
import com.ptit.apiquanlidiem.entity.SinhVienEntity;
import org.springframework.stereotype.Service;

@Service
public interface SinhVienService {

    void save(SinhVienDto sinhVienDto);

    void update(SinhVienDto sinhVienDto);

    SinhVienDto findOneByMssv(String mssv);

    PageSinhVienResDto findAllSinhVienByMaLop(String maLop, int currentPage, int perPage);

    PageSinhVienResDto findAllSinhVienByMaLopTinChi(String maLopTinChi, int currentPage, int perPage);
}
