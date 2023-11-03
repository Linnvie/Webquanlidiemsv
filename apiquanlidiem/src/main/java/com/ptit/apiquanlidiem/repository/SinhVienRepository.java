package com.ptit.apiquanlidiem.repository;

import com.ptit.apiquanlidiem.entity.SinhVienEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SinhVienRepository extends JpaRepository<SinhVienEntity, String> {
    Optional<SinhVienEntity> findOneByMssv(String mssv);

    public Page<SinhVienEntity> findAllByMaLop(String maLop, Pageable pageable);

    @Query(value = "SELECT sinhvien.mssv, ho_lot, ten, gioi_tinh,ngay_sinh, ma_khoa,ma_nganh, ma_lop, nien_khoa, he_hoc,account_id, so_tai_khoan,dia_chi, status,sinhvien.created_at, sinhvien.created_by,sinhvien.updated_at, sinhvien.updated_by " +
            "FROM bangdiemchitiet INNER JOIN sinhvien " +
            "ON bangdiemchitiet.mssv=sinhvien.mssv " +
            "WHERE ma_ltc = :maLTC order by sinhvien.mssv asc",
            countQuery="SELECT COUNT(*) "+
                    "FROM bangdiemchitiet INNER JOIN sinhvien " +
                    "ON bangdiemchitiet.mssv=sinhvien.mssv " +
                    "WHERE ma_ltc = :maLTC order by sinhvien.mssv asc", nativeQuery = true)
    Page<SinhVienEntity> findAllByMaLTC(@Param("maLTC") String maLTC, Pageable pageable);
}
