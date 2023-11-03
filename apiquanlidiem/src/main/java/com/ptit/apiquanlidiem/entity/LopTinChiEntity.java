package com.ptit.apiquanlidiem.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="loptinchi")
public class LopTinChiEntity extends BaseEntity{
    @Id
    @Column(name="ma_lop_tin_chi", length=50)
    private String maLopTinChi;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ma_mon",insertable = false, updatable = false)
	private MonEntity mon;

	@OneToMany(mappedBy="lopTinChi")
	private List<BangDiemChiTietEntity> listBangDiemChiTiet = new ArrayList<>();

	@OneToMany(mappedBy="lopTinChi", fetch = FetchType.EAGER,cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<GiangVienLTCEntity> listGiangVienLTC = new ArrayList<>();

    @Column(name="ma_mon", nullable=false)
    private String maMon;

    @Column(name="hoc_ki", nullable=false, length=2)
    private Integer hocKi;

    @Column(name="nam", nullable=false, length=5)
    private Integer nam;

    @Column(name="trang_thai", nullable=false)
    private Boolean trangThai;

    @Column(name="dong", nullable=false)
    private Boolean dong;

    @Column(name="so_sv_toi_thieu", nullable=false, length=3)
    private Integer soSVToiThieu;

    @Column(name="so_sv_toi_da", nullable=false,length=4)
    private Integer soSVToiDa;

    @Column(name="sl_dang_ki", nullable=false, length=4)
    private Integer slDangKi;


    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    public String getMaLopTinChi() {
        return maLopTinChi;
    }

    public void setMaLopTinChi(String maLopTinChi) {
        this.maLopTinChi = maLopTinChi;
    }

    public Integer getHocKi() {
        return hocKi;
    }

    public void setHocKi(Integer hocKi) {
        this.hocKi = hocKi;
    }

    public Integer getNam() {
        return nam;
    }

    public void setNam(Integer nam) {
        this.nam = nam;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    public Integer getSoSVToiThieu() {
        return soSVToiThieu;
    }

    public void setSoSVToiThieu(Integer soSVToiThieu) {
        this.soSVToiThieu = soSVToiThieu;
    }

    public Integer getSoSVToiDa() {
        return soSVToiDa;
    }

    public void setSoSVToiDa(Integer soSVToiDa) {
        this.soSVToiDa = soSVToiDa;
    }

    public Integer getSlDangKi() {
        return slDangKi;
    }

    public void setSlDangKi(Integer slDangKi) {
        this.slDangKi = slDangKi;
    }

    public MonEntity getMon() {
        return mon;
    }

    public void setMon(MonEntity mon) {
        mon = mon;
    }

    public Boolean getDong() {
        return dong;
    }

    public void setDong(Boolean dong) {
        this.dong = dong;
    }

    public List<BangDiemChiTietEntity> getListBangDiemChiTiet() {
        return listBangDiemChiTiet;
    }

    public void setListBangDiemChiTiet(List<BangDiemChiTietEntity> listBangDiemChiTiet) {
        this.listBangDiemChiTiet = listBangDiemChiTiet;
    }

    public Collection<GiangVienLTCEntity> getListGiangVienLTC() {
        return listGiangVienLTC;
    }

    public void setListGiangVienLTC(Collection<GiangVienLTCEntity> listGiangVienLTC) {
        this.listGiangVienLTC = listGiangVienLTC;
    }
}
