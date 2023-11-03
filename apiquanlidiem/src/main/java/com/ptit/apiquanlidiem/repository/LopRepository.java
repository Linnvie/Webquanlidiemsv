package com.ptit.apiquanlidiem.repository;

import com.ptit.apiquanlidiem.entity.LopEntity;
import com.ptit.apiquanlidiem.entity.LopTinChiEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LopRepository extends JpaRepository<LopEntity, String> {
    Optional<LopEntity> findOneByMaLop(String maLop);

    @Query(value = "From LopEntity where maLop ILIKE concat('%',:keyWord,'%')",
            countQuery = "From LopEntity where maLop ILIKE concat('%',:keyWord,'%')")
    public Page<LopEntity> findAllByKey(@Param("keyWord")String keyWord, Pageable pageable);
}
