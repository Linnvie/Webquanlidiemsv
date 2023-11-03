package com.ptit.apiquanlidiem.dto;

import java.util.List;

public class PageSinhVienResDto extends PageableDto{

    private List<SinhVienDto> listSV;

    public PageSinhVienResDto(int totalPages, long totalElements, int perPage, int numberOfElements, int currentPage, boolean first, boolean last, List<SinhVienDto> listSV) {
        super(totalPages, totalElements, perPage, numberOfElements, currentPage, first, last);
        this.listSV = listSV;
    }

    public List<SinhVienDto> getListSV() {
        return listSV;
    }

    public void setListSV(List<SinhVienDto> listSV) {
        this.listSV = listSV;
    }
}
