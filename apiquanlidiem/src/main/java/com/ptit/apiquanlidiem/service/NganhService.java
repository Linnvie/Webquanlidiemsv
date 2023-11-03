package com.ptit.apiquanlidiem.service;

import com.ptit.apiquanlidiem.dto.GiangVienSelectDto;
import com.ptit.apiquanlidiem.dto.NganhSelectDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NganhService {

    List<NganhSelectDto> findAll();
}
