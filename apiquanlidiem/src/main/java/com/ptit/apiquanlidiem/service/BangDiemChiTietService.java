package com.ptit.apiquanlidiem.service;


import com.ptit.apiquanlidiem.dto.BangDiemChiTietDto;
import com.ptit.apiquanlidiem.dto.BangDiemTheoKiDto;
import com.ptit.apiquanlidiem.dto.PageBangDiemResDto;
import com.ptit.apiquanlidiem.dto.TTBangDiemDto;
import com.ptit.apiquanlidiem.entity.BangDiemChiTietEntity;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Service
public interface BangDiemChiTietService {

    List<BangDiemTheoKiDto> findAllByMSSV(String mssv);

    List<TTBangDiemDto> findAllByMSSVAndHKAndNam(String mssv, int hocKi, int nam);

    PageBangDiemResDto findAllBangDiemCTByMaLTC(String maLTC, int currentPage, int perPage);

    TTBangDiemDto findOneByMSSVAndMaLTC(String mssv, String maLTC);

    int save( BangDiemChiTietDto bangDiemChiTiet);

    void update( BangDiemChiTietDto bangDiemChiTiet);

    void getFromExcelFileAndSave(MultipartFile file);


    byte[] generateExcel(String maLTC) ;
}
