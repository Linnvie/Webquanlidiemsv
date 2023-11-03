package com.ptit.apiquanlidiem.service;

import com.ptit.apiquanlidiem.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GiangVienService {

    List<GiangVienSelectDto> findAllActive();

    GiangVienDto findOneByMaGV(String maGV);

    void save(GiangVienDto giangVienDto);

    void update(GiangVienDto giangVienDto);

    PageGiangVienResDto findAllGiangVienByKeyWithPaginate(String keyWord,int currentPage,int perPage);

    PageGiangVienResDto findAllGiangVienFilterWithPaginate(Boolean active,int currentPage,int perPage);
}
