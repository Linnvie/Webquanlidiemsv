package com.ptit.apiquanlidiem.model;

import com.ptit.apiquanlidiem.entity.AccountEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {
    AccountEntity accountEntity;



    public CustomUserDetails() {
        super();
    }

    public CustomUserDetails(AccountEntity accountEntity) {
        super();
        this.accountEntity = accountEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//		 return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
        return null;
    }

    @Override
    public String getPassword() {
        return accountEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return accountEntity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
