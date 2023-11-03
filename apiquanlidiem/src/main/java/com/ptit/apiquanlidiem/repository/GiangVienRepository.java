package com.ptit.apiquanlidiem.repository;

import com.ptit.apiquanlidiem.entity.GiangVienEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GiangVienRepository  extends JpaRepository<GiangVienEntity, String> {

    @Query("From GiangVienEntity where account.status=true")
    List<GiangVienEntity> findAllActive();

    @Query(value = "From GiangVienEntity where account.status= :active",
            countQuery = "SELECT count(*) From GiangVienEntity where account.status= :active")
    Page<GiangVienEntity> findFilterActive(@Param("active") Boolean active,  Pageable pageable);

    Optional<GiangVienEntity> findOneByMaGiangVien(String maGiangVien);

    Page<GiangVienEntity> findAllByMaKhoa(String maKhoa, Pageable pageable);

    @Query(value = "From GiangVienEntity where maGiangVien ILIKE concat('%',:keyWord,'%') or ten LIKE concat('%',:keyWord,'%') or hoLot LIKE concat('%',:keyWord,'%')",
            countQuery = "SELECT count(*) From GiangVienEntity where maGiangVien ILIKE concat('%',:keyWord,'%') or ten LIKE concat('%',:keyWord,'%') or hoLot LIKE concat('%',:keyWord,'%')")
    Page<GiangVienEntity> findByKey(@Param("keyWord") String keyWord, Pageable pageable);
}
