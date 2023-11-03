package com.ptit.apiquanlidiem.repository;

import com.ptit.apiquanlidiem.entity.RoleEntity;
import com.ptit.apiquanlidiem.vo.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, RoleEnum> {

    Optional<RoleEntity> findOneById(RoleEnum id);
}
