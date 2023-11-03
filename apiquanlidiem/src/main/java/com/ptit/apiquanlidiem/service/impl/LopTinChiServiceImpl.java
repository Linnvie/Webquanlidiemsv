package com.ptit.apiquanlidiem.service.impl;

import com.ptit.apiquanlidiem.converter.LopTinChiConverter;
import com.ptit.apiquanlidiem.dto.ChiTietLTCResDto;
import com.ptit.apiquanlidiem.dto.LopTinChiDto;
import com.ptit.apiquanlidiem.dto.PageLTCResDto;
import com.ptit.apiquanlidiem.entity.GVLTCKey;
import com.ptit.apiquanlidiem.entity.GiangVienEntity;
import com.ptit.apiquanlidiem.entity.GiangVienLTCEntity;
import com.ptit.apiquanlidiem.entity.LopTinChiEntity;
import com.ptit.apiquanlidiem.exception.DuplicateResourceException;
import com.ptit.apiquanlidiem.exception.ResourceNotFoundException;
import com.ptit.apiquanlidiem.repository.GiangVienLTCRepository;
import com.ptit.apiquanlidiem.repository.GiangVienRepository;
import com.ptit.apiquanlidiem.repository.LopTinChiRepository;
import com.ptit.apiquanlidiem.service.LopTinChiService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LopTinChiServiceImpl implements LopTinChiService {

    @Autowired
    private LopTinChiRepository lopTinChiRepository;

    @Autowired
    private GiangVienLTCRepository giangVienLTCRepository;

    @Autowired
    private GiangVienRepository giangVienRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LopTinChiConverter lopTinChiConverter;

    @Override
    public PageLTCResDto findAllByMaMonWithPaginate(String maMon, int currentPage, int perPage) {
        Pageable paging = PageRequest.of(currentPage, perPage ,  Sort.by("createdAt").descending());

        Page<LopTinChiEntity> pageListEntity
                = this.lopTinChiRepository.findAllByMaMon(maMon.toUpperCase(),paging);

        return pageListEntity.getTotalElements() > 0
                ? new PageLTCResDto(pageListEntity.getTotalPages(),
                pageListEntity.getTotalElements(),
                pageListEntity.getSize(),
                pageListEntity.getNumberOfElements(),
                pageListEntity.getNumber() + 1,
                pageListEntity.isFirst(),
                pageListEntity.isLast(),
                pageListEntity.getContent().stream()
                        .map(item -> lopTinChiConverter.toDtoSimp(item))
                        .collect(Collectors.toList()))
                : null;
    }

    @Override
    public PageLTCResDto findAllByMaMonAndHocKiAndNamWithPaginate(String maMon, int hocKi, int nam, int currentPage, int perPage) {
        Pageable paging = PageRequest.of(currentPage, perPage ,  Sort.by("createdAt").descending());

        Page<LopTinChiEntity> pageListEntity
                = this.lopTinChiRepository.findAllByMaMonAndHocKiAndNam(maMon.toUpperCase(),hocKi,nam,paging);

        return pageListEntity.getTotalElements() > 0
                ? new PageLTCResDto(pageListEntity.getTotalPages(),
                pageListEntity.getTotalElements(),
                pageListEntity.getSize(),
                pageListEntity.getNumberOfElements(),
                pageListEntity.getNumber() + 1,
                pageListEntity.isFirst(),
                pageListEntity.isLast(),
                pageListEntity.getContent().stream()
                        .map(item -> lopTinChiConverter.toDtoSimp(item))
                        .collect(Collectors.toList()))
                : null;
    }

    @Override
    public LopTinChiDto findOneByMaLopTinChi(String maLTC) {
        LopTinChiEntity entity= this.lopTinChiRepository.findOneByMaLopTinChi(maLTC.toUpperCase())
                .orElseThrow(()->new ResourceNotFoundException("Không tồn taị lớp tín chỉ này"));
        return modelMapper.map(entity, LopTinChiDto.class);
    }

    @Override
    public ChiTietLTCResDto findChiTietByMaLopTinChi(String maLTC) {
        LopTinChiEntity entity= this.lopTinChiRepository.findOneByMaLopTinChi(maLTC.toUpperCase())
                .orElseThrow(()->new ResourceNotFoundException("Không tồn taị lớp tín chỉ này"));
        return this.lopTinChiConverter.toDto(entity);
    }

    @Override
    public List<ChiTietLTCResDto> findAllByMaGVAndHKNam(String maGV, int hocKi, int nam) {
        GiangVienEntity giangVienEntity= this.giangVienRepository.findOneByMaGiangVien(maGV.toUpperCase())
                .orElseThrow(()->new ResourceNotFoundException("Không tồn taị giảng viên này"));
        List<LopTinChiEntity> list = this.lopTinChiRepository.findAllByGiangVienAndHKAndNam(giangVienEntity.getMaGiangVien(),hocKi,nam);
        return list==null
                ?null
                :list.stream().map(entity -> this.lopTinChiConverter.toDto(entity)).collect(Collectors.toList());
    }

    @Override
    public PageLTCResDto findAllLTCByKeyWithPaginate(String keyWord, int currentPage, int perPage) {
        Pageable paging = PageRequest.of(currentPage, perPage
              ,  Sort.by("createdAt").descending()
                );

        Page<LopTinChiEntity> pageListEntity
                = this.lopTinChiRepository.findAllByKey(keyWord, paging);

        return pageListEntity.getTotalElements() > 0
                ? new PageLTCResDto(pageListEntity.getTotalPages(),
                pageListEntity.getTotalElements(),
                pageListEntity.getSize(),
                pageListEntity.getNumberOfElements(),
                pageListEntity.getNumber() + 1,
                pageListEntity.isFirst(),
                pageListEntity.isLast(),
                pageListEntity.getContent().stream()
                        .map(item -> lopTinChiConverter.toDtoSimp(item))
                        .collect(Collectors.toList()))
                : null;
    }

    @Override
    @Transactional
    public void save(LopTinChiDto newLTC) {
        newLTC.setMaLopTinChi(newLTC.getMaLopTinChi().toUpperCase());
        LopTinChiEntity lopTinChi = this.lopTinChiRepository.findOneByMaLopTinChi(newLTC.getMaLopTinChi())
                .orElse(null);
        if(lopTinChi!=null){
            throw new DuplicateResourceException("Lớp tín chỉ đã tồn tại");
        }
        newLTC.setDong(false);
        this.lopTinChiRepository.save(modelMapper.map(newLTC, LopTinChiEntity.class));
        for(String maGV : newLTC.getListGiangVien()){
            GiangVienEntity giangVien = this.giangVienRepository.findOneByMaGiangVien(maGV.toUpperCase())
                    .orElseThrow(()->new ResourceNotFoundException("Không tồn tại giảng viên "+maGV));
            GiangVienLTCEntity giangVienLTCEntity = new GiangVienLTCEntity();
            giangVienLTCEntity.setId(new GVLTCKey(giangVien.getMaGiangVien(), newLTC.getMaLopTinChi()));
            giangVienLTCEntity.setGiangVien(giangVien);
            giangVienLTCEntity.setLopTinChi(lopTinChi);

            this.giangVienLTCRepository.save(giangVienLTCEntity);

        }

    }

    @Override
    @Transactional
    public void update(LopTinChiDto newLTC) {
        newLTC.setMaLopTinChi(newLTC.getMaLopTinChi().toUpperCase());
        LopTinChiEntity lopTinChi= this.lopTinChiRepository.findOneByMaLopTinChi(newLTC.getMaLopTinChi())
                .orElseThrow(() ->new ResourceNotFoundException("Lớp tín chỉ đã tồn tại!"));
        this.lopTinChiRepository.save(modelMapper.map(newLTC, LopTinChiEntity.class));
        for(GiangVienLTCEntity giangVienLTC : lopTinChi.getListGiangVienLTC()){
            this.giangVienLTCRepository.delete(giangVienLTC);
        }
        for(String maGV : newLTC.getListGiangVien()){
            GiangVienEntity giangVien = this.giangVienRepository.findOneByMaGiangVien(maGV.toUpperCase())
                    .orElseThrow(()->new ResourceNotFoundException("Không tồn tại giảng viên "+maGV));
            GiangVienLTCEntity giangVienLTCEntity = new GiangVienLTCEntity();
            giangVienLTCEntity.setId(new GVLTCKey(giangVien.getMaGiangVien(), newLTC.getMaLopTinChi()));
            giangVienLTCEntity.setGiangVien(giangVien);
            giangVienLTCEntity.setLopTinChi(lopTinChi);

            this.giangVienLTCRepository.save(giangVienLTCEntity);

        }
    }
}
