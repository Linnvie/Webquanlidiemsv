package com.ptit.apiquanlidiem.dto;

import java.util.List;

public class PageLopResDto extends PageableDto{

    private List<LopDto> listLop;

    public PageLopResDto(int totalPages, long totalElements, int perPage, int numberOfElements, int currentPage, boolean first, boolean last, List<LopDto> listLop) {
        super(totalPages, totalElements, perPage, numberOfElements, currentPage, first, last);
        this.listLop = listLop;
    }

    public PageLopResDto() {
    }

    public List<LopDto> getListLop() {
        return listLop;
    }

    public void setListLop(List<LopDto> listLop) {
        this.listLop = listLop;
    }
}
