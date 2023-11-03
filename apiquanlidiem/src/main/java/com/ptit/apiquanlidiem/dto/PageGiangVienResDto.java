package com.ptit.apiquanlidiem.dto;

import java.util.List;

public class PageGiangVienResDto extends PageableDto{

    private List<GiangVienDto> listGV;

    public PageGiangVienResDto() {
    }

    public PageGiangVienResDto(int totalPages, long totalElements, int perPage, int numberOfElements, int currentPage, boolean first, boolean last, List<GiangVienDto> listGV) {
        super(totalPages, totalElements, perPage, numberOfElements, currentPage, first, last);
        this.listGV = listGV;
    }

    public List<GiangVienDto> getListGV() {
        return listGV;
    }

    public void setListGV(List<GiangVienDto> listGV) {
        this.listGV = listGV;
    }
}
