package com.ptit.apiquanlidiem.repository;

import com.ptit.apiquanlidiem.entity.GVLTCKey;
import com.ptit.apiquanlidiem.entity.GiangVienEntity;
import com.ptit.apiquanlidiem.entity.GiangVienLTCEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GiangVienLTCRepository extends JpaRepository<GiangVienLTCEntity, GVLTCKey> {

    Optional<GiangVienLTCEntity> findOneById(GVLTCKey key);


    List<GiangVienLTCEntity> findByGiangVien(GiangVienEntity giangVien);
}
