package com.ptit.apiquanlidiem.service.impl;

import com.ptit.apiquanlidiem.dto.*;
import com.ptit.apiquanlidiem.entity.MonEntity;
import com.ptit.apiquanlidiem.exception.DuplicateResourceException;
import com.ptit.apiquanlidiem.exception.ResourceNotFoundException;
import com.ptit.apiquanlidiem.repository.MonRepository;
import com.ptit.apiquanlidiem.service.MonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MonServiceImpl implements MonService {

    @Autowired
    private MonRepository monRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public void save(MonDto mon) {
        if(mon.getHeSo1()+mon.getHeSo2()+mon.getHeSo3()+mon.getHeSo4()+mon.getHeSo5()!=1) {
            throw new IllegalArgumentException("Tổng các hệ số phải bằng 1");
        }
        mon.setMaMon(mon.getMaMon().toUpperCase());
        if(monRepository.findOneByMaMon(mon.getMaMon()).orElse(null)!=null){
            throw new DuplicateResourceException("Môn học đã tồn tại!");
        }
        monRepository.save(modelMapper.map(mon, MonEntity.class));

    }

    @Override
    public void update(MonDto mon) {
        if(mon.getHeSo1()+mon.getHeSo2()+mon.getHeSo3()+mon.getHeSo4()+mon.getHeSo5()!=1) {
            throw new IllegalArgumentException("Tổng các hệ số phải bằng 1");
        }
        mon.setMaMon(mon.getMaMon().toUpperCase());
        monRepository.findOneByMaMon(mon.getMaMon())
                .orElseThrow(() ->new DuplicateResourceException("Không tồn tại Môn học!"));
        monRepository.save(modelMapper.map(mon, MonEntity.class));
    }

    @Override
    public MonDto findOneByMaMon(String maMon) {
        MonEntity entity= this.monRepository.findOneByMaMon(maMon.toUpperCase())
                .orElseThrow(()->new ResourceNotFoundException("Không tồn taị môn này"));
        return modelMapper.map(entity, MonDto.class);
    }

    @Override
    public List<MonSelectDto> findAll() {
        List<MonEntity> listMon = this.monRepository.findAll();
        return listMon.stream().map(item -> modelMapper.map(item, MonSelectDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PageMonResDto findAllMonByKeyWithPaginate(String keyWord, int currentPage, int perPage) {
        Pageable paging = PageRequest.of(currentPage, perPage);

        Page<MonEntity> pageListEntity
                = this.monRepository.findByKey(keyWord, paging);

        return pageListEntity.getTotalElements() > 0
                ? new PageMonResDto(pageListEntity.getTotalPages(),
                pageListEntity.getTotalElements(),
                pageListEntity.getSize(),
                pageListEntity.getNumberOfElements(),
                pageListEntity.getNumber() + 1,
                pageListEntity.isFirst(),
                pageListEntity.isLast(),
                pageListEntity.getContent().stream()
                        .map(item -> modelMapper.map(item, MonDto.class))
                        .collect(Collectors.toList()))
                : null;
    }

}

