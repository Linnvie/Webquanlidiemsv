package com.ptit.apiquanlidiem.service.impl;

import com.ptit.apiquanlidiem.dto.GiangVienSelectDto;
import com.ptit.apiquanlidiem.dto.NganhSelectDto;
import com.ptit.apiquanlidiem.entity.GiangVienEntity;
import com.ptit.apiquanlidiem.entity.NganhEntity;
import com.ptit.apiquanlidiem.repository.NganhRepository;
import com.ptit.apiquanlidiem.service.NganhService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NganhServiceImpl implements NganhService {

    @Autowired
    private NganhRepository nganhRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<NganhSelectDto> findAll() {
        List<NganhEntity> listNganh = this.nganhRepository.findAll();
        return listNganh.stream().map(item -> modelMapper.map(item, NganhSelectDto.class))
                .collect(Collectors.toList());
    }
}
