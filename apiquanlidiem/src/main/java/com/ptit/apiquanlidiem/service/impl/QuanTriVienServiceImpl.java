package com.ptit.apiquanlidiem.service.impl;

import com.ptit.apiquanlidiem.dto.QuanTriVienDto;
import com.ptit.apiquanlidiem.dto.SinhVienDto;
import com.ptit.apiquanlidiem.entity.AccountEntity;
import com.ptit.apiquanlidiem.entity.QuanTriVienEntity;
import com.ptit.apiquanlidiem.entity.SinhVienEntity;
import com.ptit.apiquanlidiem.exception.DuplicateResourceException;
import com.ptit.apiquanlidiem.exception.ResourceNotFoundException;
import com.ptit.apiquanlidiem.repository.AccountRepository;
import com.ptit.apiquanlidiem.repository.QuanTriVienRepository;
import com.ptit.apiquanlidiem.repository.RoleRepository;
import com.ptit.apiquanlidiem.service.QuanTriVienService;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.ptit.apiquanlidiem.vo.RoleEnum.STUDENT;

@Service
public class QuanTriVienServiceImpl implements QuanTriVienService {

    @Autowired
    private QuanTriVienRepository quanTriVienRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public QuanTriVienDto findOneByMaQTV(String maQTV) {

        QuanTriVienEntity entity= this.quanTriVienRepository.findOneByMaQTV(maQTV.toUpperCase())
                .orElseThrow(()->new ResourceNotFoundException("Không tồn taị quản trị viên này"));
        return modelMapper.map(entity, QuanTriVienDto.class);
    }

    @Override
    public void save(QuanTriVienDto quanTriVienDto) {
        quanTriVienDto.setMaQTV( quanTriVienDto.getMaQTV().toUpperCase());
        if(this.quanTriVienRepository.findOneByMaQTV(quanTriVienDto.getMaQTV().toUpperCase()).orElse(null)!=null) {
            throw new DuplicateResourceException("Mã quản trị viên đã tồn tại");
        }
        AccountEntity account= new AccountEntity();
        account.setUsername(quanTriVienDto.getMaQTV());
        account.setRole(roleRepository.findOneById(STUDENT)
                .orElseThrow(()-> new ResourceNotFoundException("Role không hợp lệ")));
        account.setPassword(BCrypt.hashpw("1234", BCrypt.gensalt(12)));
        account.setStatus(true);
        QuanTriVienEntity quanTriVienEntity= modelMapper.map(quanTriVienDto, QuanTriVienEntity.class);
        quanTriVienEntity.setAccount(account);

        this.quanTriVienRepository.save(quanTriVienEntity);
    }

    @Override
    public void update(QuanTriVienDto quanTriVienDto) {
        quanTriVienDto.setMaQTV(quanTriVienDto.getMaQTV().toUpperCase());
        AccountEntity account = accountRepository.findOneByUsername(quanTriVienDto.getMaQTV())
                .orElseThrow(() ->new DuplicateResourceException("Không tồn tại tài khoản!"));
        QuanTriVienEntity qtv= modelMapper.map(quanTriVienDto, QuanTriVienEntity.class);
        qtv.setAccount(account);
        quanTriVienRepository.save(qtv);
    }
}
