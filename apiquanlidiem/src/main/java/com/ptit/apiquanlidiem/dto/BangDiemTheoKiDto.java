package com.ptit.apiquanlidiem.dto;

import java.util.List;

public class BangDiemTheoKiDto {
    private int hocKi;

    private int nam;

    private int tinChiDatHk;

    private int tinChiTichLuy;

    private float tbHocKi10;

    private float tbHocKi4;

    private float tbTichLuy10;

    private float tbTichLuy4;

    private List<TTBangDiemDto> listDiem;

    public int getHocKi() {
        return hocKi;
    }

    public void setHocKi(int hocKi) {
        this.hocKi = hocKi;
    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }

    public List<TTBangDiemDto> getListDiem() {
        return listDiem;
    }

    public void setListDiem(List<TTBangDiemDto> listDiem) {
        this.listDiem = listDiem;
    }

    public int getTinChiDatHk() {
        return tinChiDatHk;
    }

    public void setTinChiDatHk(int tinChiDatHk) {
        this.tinChiDatHk = tinChiDatHk;
    }

    public int getTinChiTichLuy() {
        return tinChiTichLuy;
    }

    public void setTinChiTichLuy(int tinChiTichLuy) {
        this.tinChiTichLuy = tinChiTichLuy;
    }

    public float getTbHocKi10() {
        return tbHocKi10;
    }

    public void setTbHocKi10(float tbHocKi10) {
        this.tbHocKi10 = tbHocKi10;
    }

    public float getTbHocKi4() {
        return tbHocKi4;
    }

    public void setTbHocKi4(float tbHocKi4) {
        this.tbHocKi4 = tbHocKi4;
    }

    public float getTbTichLuy10() {
        return tbTichLuy10;
    }

    public void setTbTichLuy10(float tbTichLuy10) {
        this.tbTichLuy10 = tbTichLuy10;
    }

    public float getTbTichLuy4() {
        return tbTichLuy4;
    }

    public void setTbTichLuy4(float tbTichLuy4) {
        this.tbTichLuy4 = tbTichLuy4;
    }
}
