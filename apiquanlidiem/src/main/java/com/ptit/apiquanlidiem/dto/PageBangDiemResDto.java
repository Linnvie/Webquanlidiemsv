package com.ptit.apiquanlidiem.dto;

import java.util.List;

public class PageBangDiemResDto extends PageableDto{

    private List<TTBangDiemDto> listBangDiem;

    public PageBangDiemResDto() {
    }

    public PageBangDiemResDto(int totalPages, long totalElements, int perPage, int numberOfElements, int currentPage, boolean first, boolean last, List<TTBangDiemDto> listBangDiem) {
        super(totalPages, totalElements, perPage, numberOfElements, currentPage, first, last);
        this.listBangDiem = listBangDiem;
    }

    public List<TTBangDiemDto> getListBangDiem() {
        return listBangDiem;
    }

    public void setListBangDiem(List<TTBangDiemDto> listBangDiem) {
        this.listBangDiem = listBangDiem;
    }
}
