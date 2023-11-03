package com.ptit.apiquanlidiem.service.impl;

import com.ptit.apiquanlidiem.dto.PageSinhVienResDto;
import com.ptit.apiquanlidiem.dto.SinhVienDto;
import com.ptit.apiquanlidiem.entity.AccountEntity;
import com.ptit.apiquanlidiem.entity.RoleEntity;
import com.ptit.apiquanlidiem.entity.SinhVienEntity;
import com.ptit.apiquanlidiem.exception.DuplicateResourceException;
import com.ptit.apiquanlidiem.exception.ResourceNotFoundException;
import com.ptit.apiquanlidiem.repository.*;
import com.ptit.apiquanlidiem.service.SinhVienService;
import com.ptit.apiquanlidiem.vo.RoleEnum;
import com.ptit.apiquanlidiem.vo.TrangThaiHocEnum;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

import static com.ptit.apiquanlidiem.vo.RoleEnum.STUDENT;

@Service
public class SinhVienServiceImpl implements SinhVienService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SinhVienRepository svRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private LopRepository lopRepository;

    @Autowired
    private LopTinChiRepository lopTinChiRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public void save(SinhVienDto sinhVienDto) {
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");

        sinhVienDto.setMssv( sinhVienDto.getMssv().toUpperCase());
        if(svRepository.findOneByMssv(sinhVienDto.getMssv()).orElse(null)!=null) {
            throw new DuplicateResourceException("Mã số sinh viên đã tồn tại");
        }
        AccountEntity account= new AccountEntity();
        account.setUsername(sinhVienDto.getMssv());
        account.setRole(roleRepository.findOneById(STUDENT)
                .orElseThrow(()-> new ResourceNotFoundException("Role không hợp lệ")));
        String pw=outputFormat.format(sinhVienDto.getNgaySinh()).replace("-","");
        account.setPassword(BCrypt.hashpw(pw, BCrypt.gensalt(12)));
        account.setStatus(true);
        SinhVienEntity sinhVienEntity= modelMapper.map(sinhVienDto, SinhVienEntity.class);
        sinhVienEntity.setAccount(account);
        sinhVienEntity.setStatus(TrangThaiHocEnum.STUDYING);

        if(sinhVienDto.getMaNganh().equals("")){
            sinhVienEntity.setMaNganh(null);
        }

        svRepository.save(sinhVienEntity);
    }

    @Override
    public void update(SinhVienDto sinhVienDto) {
        sinhVienDto.setMssv(sinhVienDto.getMssv().toUpperCase());
        AccountEntity account = accountRepository.findOneByUsername(sinhVienDto.getMssv())
                .orElseThrow(() ->new DuplicateResourceException("Không tồn tại Sinh viên!"));
        SinhVienEntity sv= modelMapper.map(sinhVienDto, SinhVienEntity.class);
        sv.setAccount(account);

        if(sinhVienDto.getMaNganh()==null){
            sv.setMaNganh(null);
        }
        svRepository.save(sv);
    }

    @Override
    public SinhVienDto findOneByMssv(String mssv) {
        SinhVienEntity entity= this.svRepository.findOneByMssv(mssv.toUpperCase())
                .orElseThrow(()->new ResourceNotFoundException("Không tồn taị sinh viên này"));
        return modelMapper.map(entity, SinhVienDto.class);
    }

    @Override
    public PageSinhVienResDto findAllSinhVienByMaLop(String maLop, int currentPage, int perPage) {
        Pageable paging = PageRequest.of(currentPage,
                perPage, Sort.by("mssv").ascending());

        this.lopRepository.findOneByMaLop(maLop.toUpperCase())
                .orElseThrow(() -> new ResourceNotFoundException("Không tồn tại lớp này"));

        Page<SinhVienEntity> pageListEntity
                = this.svRepository.findAllByMaLop(maLop.toUpperCase(), paging);

        return pageListEntity.getTotalElements() > 0
                ? new PageSinhVienResDto(pageListEntity.getTotalPages(),
                pageListEntity.getTotalElements(),
                pageListEntity.getSize(),
                pageListEntity.getNumberOfElements(),
                pageListEntity.getNumber() + 1,
                pageListEntity.isFirst(),
                pageListEntity.isLast(),
                pageListEntity.getContent().stream()
                        .map(item -> modelMapper.map(item, SinhVienDto.class))
                        .collect(Collectors.toList()))
                : null;

    }

    @Override
    public PageSinhVienResDto findAllSinhVienByMaLopTinChi(String maLopTinChi, int currentPage, int perPage) {
        Pageable paging = PageRequest.of(currentPage, perPage);

        this.lopTinChiRepository.findOneByMaLopTinChi(maLopTinChi.toUpperCase())
                .orElseThrow(() -> new ResourceNotFoundException("Không tồn tại lớp tín chỉ này"));
        Page<SinhVienEntity> pageListEntity
                = this.svRepository.findAllByMaLTC(maLopTinChi.toUpperCase(), paging);

        return pageListEntity.getTotalElements() > 0
                ? new PageSinhVienResDto(pageListEntity.getTotalPages(),
                pageListEntity.getTotalElements(),
                pageListEntity.getSize(),
                pageListEntity.getNumberOfElements(),
                pageListEntity.getNumber() + 1,
                pageListEntity.isFirst(),
                pageListEntity.isLast(),
                pageListEntity.getContent().stream()
                        .map(item -> modelMapper.map(item, SinhVienDto.class))
                        .collect(Collectors.toList()))
                : null;
    }
}
