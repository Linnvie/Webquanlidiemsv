package com.ptit.apiquanlidiem.service;

import com.ptit.apiquanlidiem.dto.ChiTietLTCResDto;
import com.ptit.apiquanlidiem.dto.LopTinChiDto;
import com.ptit.apiquanlidiem.dto.PageLTCResDto;
import com.ptit.apiquanlidiem.dto.PageMonResDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LopTinChiService {

    PageLTCResDto findAllByMaMonWithPaginate(String maMon, int currentPage, int perPage);

    PageLTCResDto findAllByMaMonAndHocKiAndNamWithPaginate(String maMon,int hocKi, int nam, int currentPage, int perPage);

    LopTinChiDto findOneByMaLopTinChi(String maLTC);

    ChiTietLTCResDto findChiTietByMaLopTinChi(String maLTC);

    List<ChiTietLTCResDto> findAllByMaGVAndHKNam(String maGV, int hocKi, int nam);

    PageLTCResDto findAllLTCByKeyWithPaginate(String keyWord, int currentPage, int perPage);

    void save(LopTinChiDto newLTC);

    void update(LopTinChiDto newLTC);
}
