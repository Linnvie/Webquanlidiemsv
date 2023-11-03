package com.ptit.apiquanlidiem.service.impl;

import com.ptit.apiquanlidiem.dto.*;
import com.ptit.apiquanlidiem.entity.*;
import com.ptit.apiquanlidiem.exception.DuplicateResourceException;
import com.ptit.apiquanlidiem.exception.ResourceNotFoundException;
import com.ptit.apiquanlidiem.repository.AccountRepository;
import com.ptit.apiquanlidiem.repository.GiangVienRepository;
import com.ptit.apiquanlidiem.repository.RoleRepository;
import com.ptit.apiquanlidiem.service.GiangVienService;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import static com.ptit.apiquanlidiem.vo.RoleEnum.STUDENT;

@Service
public class GiangVienServiceImpl implements GiangVienService {

    @Autowired
    private GiangVienRepository giangVienRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<GiangVienSelectDto> findAllActive() {
        List<GiangVienEntity> listGV = this.giangVienRepository.findAllActive();
        return listGV.stream().map(item -> modelMapper.map(item, GiangVienSelectDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public GiangVienDto findOneByMaGV(String maGV) {
        GiangVienEntity entity= this.giangVienRepository.findOneByMaGiangVien(maGV.toUpperCase())
                .orElseThrow(()->new ResourceNotFoundException("Không tồn taị giảng viên này"));
        return modelMapper.map(entity, GiangVienDto.class);
    }

    @Override
    public void save(GiangVienDto giangVienDto) {
        giangVienDto.setMaGiangVien(giangVienDto.getMaGiangVien().toUpperCase());
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
        if(this.giangVienRepository.findOneByMaGiangVien(giangVienDto.getMaGiangVien()).orElse(null)!=null) {
            throw new DuplicateResourceException("Mã giảng viên đã tồn tại");
        }
        AccountEntity account= new AccountEntity();
        account.setUsername(giangVienDto.getMaGiangVien());


        account.setRole(roleRepository.findOneById(STUDENT)
                .orElseThrow(()-> new ResourceNotFoundException("Role không hợp lệ")));
        String pw=outputFormat.format(giangVienDto.getNgaySinh()).replace("-","");
        account.setPassword(BCrypt.hashpw(pw, BCrypt.gensalt(12)));
        account.setStatus(true);
        GiangVienEntity giangVienEntity= modelMapper.map(giangVienDto, GiangVienEntity.class);
        giangVienEntity.setAccount(account);

        this.giangVienRepository.save( giangVienEntity);
    }

    @Override
    public void update(GiangVienDto giangVienDto) {
        giangVienDto.setMaGiangVien(giangVienDto.getMaGiangVien().toUpperCase());
        AccountEntity account = accountRepository.findOneByUsername(giangVienDto.getMaGiangVien())
                .orElseThrow(() ->new DuplicateResourceException("Không tồn tại tài khoản!"));
        GiangVienEntity gv= modelMapper.map(giangVienDto, GiangVienEntity.class);
        gv.setAccount(account);
        giangVienRepository.save(gv);
    }

    @Override
    public PageGiangVienResDto findAllGiangVienByKeyWithPaginate(String keyWord, int currentPage, int perPage) {
        Pageable paging = PageRequest.of(currentPage, perPage);

        Page<GiangVienEntity> pageListEntity
                = this.giangVienRepository.findByKey(keyWord, paging);

        return pageListEntity.getTotalElements() > 0
                ? new PageGiangVienResDto(pageListEntity.getTotalPages(),
                pageListEntity.getTotalElements(),
                pageListEntity.getSize(),
                pageListEntity.getNumberOfElements(),
                pageListEntity.getNumber() + 1,
                pageListEntity.isFirst(),
                pageListEntity.isLast(),
                pageListEntity.getContent().stream()
                        .map(item -> modelMapper.map(item, GiangVienDto.class))
                        .collect(Collectors.toList()))
                : null;
    }

    @Override
    public PageGiangVienResDto findAllGiangVienFilterWithPaginate(Boolean active, int currentPage, int perPage) {
        Pageable paging = PageRequest.of(currentPage, perPage);

        Page<GiangVienEntity> pageListEntity
                = this.giangVienRepository.findFilterActive(active, paging);

        return pageListEntity.getTotalElements() > 0
                ? new PageGiangVienResDto(pageListEntity.getTotalPages(),
                pageListEntity.getTotalElements(),
                pageListEntity.getSize(),
                pageListEntity.getNumberOfElements(),
                pageListEntity.getNumber() + 1,
                pageListEntity.isFirst(),
                pageListEntity.isLast(),
                pageListEntity.getContent().stream()
                        .map(item -> modelMapper.map(item, GiangVienDto.class))
                        .collect(Collectors.toList()))
                : null;
    }
}
