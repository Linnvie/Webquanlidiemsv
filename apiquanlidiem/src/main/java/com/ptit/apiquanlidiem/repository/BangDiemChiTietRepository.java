package com.ptit.apiquanlidiem.repository;

import com.ptit.apiquanlidiem.dto.TTBangDiemDto;
import com.ptit.apiquanlidiem.entity.BangDiemChiTietEntity;
import com.ptit.apiquanlidiem.entity.BangDiemChiTietKey;
import com.ptit.apiquanlidiem.entity.LopTinChiEntity;
import com.ptit.apiquanlidiem.entity.SinhVienEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BangDiemChiTietRepository extends JpaRepository<BangDiemChiTietEntity, BangDiemChiTietKey> {

    public Optional<BangDiemChiTietEntity> findOneById(BangDiemChiTietKey key);

    List<BangDiemChiTietEntity> findBySinhVien(SinhVienEntity sinhVien );

    Page<BangDiemChiTietEntity> findByLopTinChi(LopTinChiEntity lopTinChi, Pageable pageable);

    List<BangDiemChiTietEntity> findByLopTinChi(LopTinChiEntity lopTinChi);

    @Query(value = "From BangDiemChiTietEntity where sinhVien.mssv= :mssv and lopTinChi.hocKi= :hocKi and lopTinChi.nam= :nam")
    List<BangDiemChiTietEntity> findByMssvAndHocKiAndNam(@Param("mssv") String mssv,@Param("hocKi") int hocKi,@Param("nam")int nam);

}
