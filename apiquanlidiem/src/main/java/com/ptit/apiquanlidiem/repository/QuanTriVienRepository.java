package com.ptit.apiquanlidiem.repository;

import com.ptit.apiquanlidiem.entity.QuanTriVienEntity;
import com.ptit.apiquanlidiem.entity.SinhVienEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface QuanTriVienRepository extends JpaRepository<QuanTriVienEntity, String> {

    Optional<QuanTriVienEntity> findOneByMaQTV(String maQTV);

}

