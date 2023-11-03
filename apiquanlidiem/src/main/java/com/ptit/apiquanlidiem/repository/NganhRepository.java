package com.ptit.apiquanlidiem.repository;
import com.ptit.apiquanlidiem.entity.NganhEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NganhRepository extends JpaRepository<NganhEntity, String> {

    Optional<NganhEntity> findOneByMaNganh(String maNganh);
}
