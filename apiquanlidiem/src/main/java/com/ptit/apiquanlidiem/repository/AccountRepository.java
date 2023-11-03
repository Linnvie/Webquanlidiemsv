package com.ptit.apiquanlidiem.repository;

import com.ptit.apiquanlidiem.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    Optional<AccountEntity> findOneByUsername(String username);

}
