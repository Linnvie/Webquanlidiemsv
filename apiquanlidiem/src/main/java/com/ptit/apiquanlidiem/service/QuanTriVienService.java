package com.ptit.apiquanlidiem.service;

import com.ptit.apiquanlidiem.dto.QuanTriVienDto;
import org.springframework.stereotype.Service;

@Service
public interface QuanTriVienService {

    QuanTriVienDto findOneByMaQTV(String maQTV);

    void save(QuanTriVienDto quanTriVienDto);

    void update(QuanTriVienDto quanTriVienDto);
}
