package com.ptit.apiquanlidiem.service.impl;

import com.ptit.apiquanlidiem.dto.AccountDtoReq;
import com.ptit.apiquanlidiem.dto.AccountDto;
import com.ptit.apiquanlidiem.dto.ChangePassDto;
import com.ptit.apiquanlidiem.dto.ForgetPassDto;
import com.ptit.apiquanlidiem.entity.AccountEntity;
import com.ptit.apiquanlidiem.exception.ResourceNotFoundException;
import com.ptit.apiquanlidiem.repository.AccountRepository;
import com.ptit.apiquanlidiem.service.AccountService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JavaMailSender mailSender;

    @Override
    public void changePassword( ChangePassDto changePassDto) {

        AccountEntity entity=accountRepository.findOneByUsername(changePassDto.getUsername().toUpperCase())
                .orElseThrow(() ->new ResourceNotFoundException("Không tồn tại tài khoản "+changePassDto.getUsername()));
        if(!passwordEncoder.matches(changePassDto.getCurrentPassword(), entity.getPassword())) {
            throw new IllegalArgumentException("Sai username hoặc mật khẩu hiện tại!");
        }
        entity.setPassword(BCrypt.hashpw(changePassDto.getNewPassword(), BCrypt.gensalt(12)));
        accountRepository.save(entity);
    }

    @Override
    public void forgetPassword(ForgetPassDto forgetPassDto) {

        String username = forgetPassDto.getUsername().split("@")[0];
        AccountEntity entity=accountRepository.findOneByUsername(username.toUpperCase())
                .orElseThrow(() ->new ResourceNotFoundException("Không tồn tại tài khoản "+forgetPassDto.getUsername()));
        Random generator = new Random();
        int random = generator.nextInt(99999999) + 100000;
        String mkmoi = String.valueOf(random);
        entity.setPassword(BCrypt.hashpw(mkmoi, BCrypt.gensalt(12)));
        accountRepository.save(entity);

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom("no-reply-email");
            helper.setTo(forgetPassDto.getUsername());
            helper.setSubject("Đổi mật khẩu thành công!");
            helper.setText("Mật khẩu mới của bạn là: " + mkmoi+"\n"+"Bạn nên đổi lại mật khẩu sau khi đăng nhập lại!");
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateAccount(AccountDto accountDto) {
        AccountEntity entity=accountRepository.findOneByUsername(accountDto.getUsername().toUpperCase())
                .orElseThrow(() ->new ResourceNotFoundException("Không tồn tại tài khoản "+accountDto.getUsername()));
//        if(accountDto.getId()==null){accountDto.setId(entity.getId());};
//        AccountEntity updateEntity=modelMapper.map(accountDto, AccountEntity.class);
//        updateEntity.setPassword(entity.getPassword());
        entity.setStatus(accountDto.getStatus());
       // entity.setRole(accountDto.getRole());
        accountRepository.save(entity);
    }

    @Override
    public AccountDto findOneByUsername(String username) {
        AccountEntity entity=accountRepository.findOneByUsername(username.toUpperCase())
                .orElseThrow(() ->new ResourceNotFoundException("Không tồn tại tài khoản "+username));

        return modelMapper.map(entity, AccountDto.class);
    }
}
