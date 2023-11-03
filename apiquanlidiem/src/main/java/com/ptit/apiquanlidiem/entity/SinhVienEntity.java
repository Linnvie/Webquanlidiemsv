package com.ptit.apiquanlidiem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ptit.apiquanlidiem.vo.TrangThaiHocEnum;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="sinhvien")
public class SinhVienEntity extends BaseEntity{
    @Id
    @Column(name="mssv", length=20)
    private String mssv;

    @Column(name="ho_lot", nullable=false, length=100)
    private String hoLot;

    @Column(name="ten", nullable=false, length=20)
    private String ten;

    @Column(name="gioi_tinh", nullable=false)
    private Boolean gioiTinh;

    //	@Temporal(TemporalType.DATE)
//	@DateTimeFormat(pattern="dd/MM/yyyy")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @Column(name="ngay_sinh")
    private Date ngaySinh;

    @Column(name="dia_chi", nullable=true)
    private String diaChi;

    @Column(name="so_tai_khoan", nullable=true)
    private String soTaiKhoan;

    @Column(name="ma_nganh", nullable=true, length=20)
    private String maNganh;

    @Column(name="ma_khoa", nullable=false, length=20)
    private String maKhoa;

    @Column(name="ma_lop", nullable=false, length=20)
    private String maLop;

    @Column(name="nien_khoa", nullable=false, length=20)
    private String nienKhoa;

    @Column(name="he_hoc", nullable=false)
    private String heHoc;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TrangThaiHocEnum status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="account_id")
    private AccountEntity account;

	@OneToMany(mappedBy="sinhVien")
	private List<BangDiemChiTietEntity> listBangDiemChiTiet = new ArrayList<>();

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public String getHoLot() {
        return hoLot;
    }

    public void setHoLot(String hoLot) {
        this.hoLot = hoLot;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Boolean getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getMaNganh() {
        return maNganh;
    }

    public void setMaNganh(String maNganh) {
        this.maNganh = maNganh;
    }

    public String getNienKhoa() {
        return nienKhoa;
    }

    public void setNienKhoa(String nienKhoa) {
        this.nienKhoa = nienKhoa;
    }

    public String getHeHoc() {
        return heHoc;
    }

    public void setHeHoc(String heHoc) {
        this.heHoc = heHoc;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoTaiKhoan() {
        return soTaiKhoan;
    }

    public void setSoTaiKhoan(String soTaiKhoan) {
        this.soTaiKhoan = soTaiKhoan;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public TrangThaiHocEnum getStatus() {
        return status;
    }

    public void setStatus(TrangThaiHocEnum status) {
        this.status = status;
    }

    public List<BangDiemChiTietEntity> getListBangDiemChiTiet() {
        return listBangDiemChiTiet;
    }

    public void setListBangDiemChiTiet(List<BangDiemChiTietEntity> listBangDiemChiTiet) {
        this.listBangDiemChiTiet = listBangDiemChiTiet;
    }
}