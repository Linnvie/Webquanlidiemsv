package com.ptit.apiquanlidiem.service;

import com.ptit.apiquanlidiem.dto.AccountDto;
import com.ptit.apiquanlidiem.dto.ChangePassDto;
import com.ptit.apiquanlidiem.dto.ForgetPassDto;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {

    void changePassword( ChangePassDto changePassDto);

    void forgetPassword( ForgetPassDto forgetPassDto);

    void updateAccount(AccountDto accountDto);

    AccountDto findOneByUsername(String username);
}
