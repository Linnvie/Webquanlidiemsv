package com.ptit.apiquanlidiem.service.impl;

import com.ptit.apiquanlidiem.dto.*;
import com.ptit.apiquanlidiem.entity.LopEntity;
import com.ptit.apiquanlidiem.entity.LopTinChiEntity;
import com.ptit.apiquanlidiem.entity.NganhEntity;
import com.ptit.apiquanlidiem.exception.DuplicateResourceException;
import com.ptit.apiquanlidiem.exception.ResourceNotFoundException;
import com.ptit.apiquanlidiem.repository.KhoaRepository;
import com.ptit.apiquanlidiem.repository.LopRepository;
import com.ptit.apiquanlidiem.repository.NganhRepository;
import com.ptit.apiquanlidiem.service.LopService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LopServiceImpl implements LopService {

    @Autowired
    private LopRepository lopRepository;

    @Autowired
    private KhoaRepository khoaRepository;

    @Autowired
    private NganhRepository nganhRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public LopDto findOneByMaLop(String maLop) {
        LopEntity entity= this.lopRepository.findOneByMaLop(maLop.toUpperCase())
                .orElseThrow(()->new ResourceNotFoundException("Không tồn taị lớp tín chỉ này"));
        return modelMapper.map(entity, LopDto.class);
    }

    @Override
    public PageLopResDto findAllLopByKeyWithPaginate(String keyWord, int currentPage, int perPage) {

        Pageable paging = PageRequest.of(currentPage, perPage);

        Page<LopEntity> pageListEntity
                = this.lopRepository.findAllByKey(keyWord, paging);

        return pageListEntity.getTotalElements() > 0
                ? new PageLopResDto(pageListEntity.getTotalPages(),
                pageListEntity.getTotalElements(),
                pageListEntity.getSize(),
                pageListEntity.getNumberOfElements(),
                pageListEntity.getNumber() + 1,
                pageListEntity.isFirst(),
                pageListEntity.isLast(),
                pageListEntity.getContent().stream()
                        .map(item -> {
                            LopDto dto = modelMapper.map(item, LopDto.class);
                            dto.setMaKhoa(item.getKhoa().getMaKhoa());
                            dto.setMaNganh(item.getNganh()!=null?item.getNganh().getMaNganh():null);
                            return dto;
                        })
                        .collect(Collectors.toList()))
                : null;
    }

    @Override
    public void save(LopDto newLop) {
        newLop.setMaLop(newLop.getMaLop().toUpperCase());
        if(this.lopRepository.findOneByMaLop(newLop.getMaLop().toUpperCase()).orElse(null)!=null){
            throw new DuplicateResourceException("Lớp đã tồn tại!");
        }
        LopEntity lopEntity= modelMapper.map(newLop, LopEntity.class);
        lopEntity.setKhoa( this.khoaRepository.findOneByMaKhoa(newLop.getMaKhoa())
                .orElseThrow(() ->new ResourceNotFoundException("Không tồn tại Khoa!")));
        if(!newLop.getMaNganh().equals("")){
            lopEntity.setNganh(this.nganhRepository.findOneByMaNganh(newLop.getMaNganh())
                    .orElseThrow(() ->new ResourceNotFoundException("Không tồn tại Ngành!")));
        }
        this.lopRepository.save(lopEntity);
    }

    @Override
    public void update(LopDto newLop) {
        this.lopRepository.findOneByMaLop(newLop.getMaLop().toUpperCase())
                .orElseThrow(() ->new ResourceNotFoundException("Không tồn tại Lớp!"));
        LopEntity lopEntity= modelMapper.map(newLop, LopEntity.class);
       lopEntity.setKhoa( this.khoaRepository.findOneByMaKhoa(newLop.getMaKhoa())
               .orElseThrow(() ->new ResourceNotFoundException("Không tồn tại Khoa!")));
        if(newLop.getMaNganh()!=null){
            lopEntity.setNganh(this.nganhRepository.findOneByMaNganh(newLop.getMaNganh())
                    .orElseThrow(() ->new ResourceNotFoundException("Không tồn tại Ngành!")));
        }
        this.lopRepository.save(lopEntity);
    }

    @Override
    public List<LopSelectDto> findAll() {
        List<LopEntity> listLop = this.lopRepository.findAll(Sort.by("createdAt").descending());
        return listLop.stream().map(item -> modelMapper.map(item, LopSelectDto.class))
                .collect(Collectors.toList());
    }
}
