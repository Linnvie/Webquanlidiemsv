package com.ptit.apiquanlidiem.security.service.impl;


import com.ptit.apiquanlidiem.entity.AccountEntity;
import com.ptit.apiquanlidiem.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountEntity account = this.accountRepository
                .findOneByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found with email" + username));

        return new org.springframework.security.core.userdetails.User(account.getUsername(),account.getPassword(),mapRolesToAuthority(account));
    }

    /**
     * *
     * This method handles RoleEntity to GrantedAuthority
     * @return a list GrantedAuthority corresponding to the user's role, which can be used during authentication.
     */
    private Collection<GrantedAuthority> mapRolesToAuthority(AccountEntity account) {

        List<GrantedAuthority> authorities = account.getRole().getAuthorities().stream().collect(Collectors.toList());
        return authorities;
    }
}
