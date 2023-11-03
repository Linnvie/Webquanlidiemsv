package com.ptit.apiquanlidiem.service;

import com.ptit.apiquanlidiem.dto.LopTinChiDto;
import com.ptit.apiquanlidiem.dto.MonDto;
import com.ptit.apiquanlidiem.dto.MonSelectDto;
import com.ptit.apiquanlidiem.dto.PageMonResDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MonService {

    void save(MonDto mon);

    void update(MonDto mon);

    MonDto findOneByMaMon(String maMon);

    List<MonSelectDto> findAll();

    PageMonResDto findAllMonByKeyWithPaginate(String keyWord, int currentPage, int perPage);
}
