package com.ptit.apiquanlidiem.repository;

import com.ptit.apiquanlidiem.entity.LopTinChiEntity;
import com.ptit.apiquanlidiem.entity.MonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LopTinChiRepository extends JpaRepository<LopTinChiEntity, String> {

    Page<LopTinChiEntity> findAllByMaMon(String maMon, Pageable pageable);

    Page<LopTinChiEntity> findAllByMaMonAndHocKiAndNam(String maMon, int hocKi, int nam,Pageable pageable);

    Optional<LopTinChiEntity> findOneByMaLopTinChi(String maLTC);

    @Query(value = "From LopTinChiEntity where maLopTinChi ILIKE concat('%',:keyWord,'%')",
            countQuery = "From LopTinChiEntity where maLopTinChi ILIKE concat('%',:keyWord,'%')")
    Page<LopTinChiEntity> findAllByKey(@Param("keyWord")String keyWord, Pageable pageable);


//    @Query(value = "From GiangVienLTCEntity where id.maGV= :maGV and lopTinChi.hocKi= :hocKi and lopTinChi.nam= :nam")
//    List<LopTinChiEntity> findAllByGiangVienAndHKAndNam(@Param("maGV")String maGV,
//                                                        @Param("hocKi") int hocKi,
//                                                        @Param("nam") int nam);

    @Query(value = "Select ma_lop_tin_chi, hoc_ki,nam, sl_dang_ki,so_sv_toi_da,so_sv_toi_thieu,trang_thai,dong, ma_mon, loptinchi.created_at, loptinchi.created_by,loptinchi.updated_at, loptinchi.updated_by From loptinchi " +
            "inner join giangvien_ltc on giangvien_ltc.ma_ltc=loptinchi.ma_lop_tin_chi and giangvien_ltc.ma_gv= :maGV " +
            "where lopTinChi.hoc_ki= :hocKi and lopTinChi.nam= :nam", nativeQuery = true)
    List<LopTinChiEntity> findAllByGiangVienAndHKAndNam(@Param("maGV")String maGV,
                                                        @Param("hocKi") int hocKi,
                                                        @Param("nam") int nam);

}
