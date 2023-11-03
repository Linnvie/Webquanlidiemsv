package com.ptit.apiquanlidiem.repository;

import com.ptit.apiquanlidiem.entity.KhoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KhoaRepository extends JpaRepository<KhoaEntity, String> {

    Optional<KhoaEntity> findOneByMaKhoa(String maKhoa);
}
