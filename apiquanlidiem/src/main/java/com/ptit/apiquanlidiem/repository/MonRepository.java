package com.ptit.apiquanlidiem.repository;

import com.ptit.apiquanlidiem.entity.MonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MonRepository extends JpaRepository<MonEntity, String> {

    public Optional<MonEntity> findOneByMaMon(String maMon);

    @Query(value = "From MonEntity where maMon ILIKE concat('%',:keyWord,'%') or tenMon LIKE concat('%',:keyWord,'%')",
            countQuery = "SELECT count(*) From MonEntity where maMon ILIKE concat('%',:keyWord,'%') or tenMon LIKE concat('%',:keyWord,'%')")
    public Page<MonEntity> findByKey(@Param("keyWord") String keyWord, Pageable pageable);



}
