package com.ptit.apiquanlidiem.service;

import com.ptit.apiquanlidiem.dto.LopDto;
import com.ptit.apiquanlidiem.dto.LopSelectDto;
import com.ptit.apiquanlidiem.dto.PageLopResDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LopService {

    LopDto findOneByMaLop(String maLop);

    PageLopResDto findAllLopByKeyWithPaginate(String keyWord, int currentPage, int perPage);

    void save(LopDto newLop);

    void update(LopDto newLop);

    List<LopSelectDto> findAll();
}
