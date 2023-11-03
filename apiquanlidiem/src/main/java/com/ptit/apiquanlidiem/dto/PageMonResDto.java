package com.ptit.apiquanlidiem.dto;

import java.util.List;

public class PageMonResDto extends  PageableDto{

    private List<MonDto> listMon;

    public PageMonResDto(List<MonDto> listMon) {
        this.listMon = listMon;
    }

    public PageMonResDto() {
    }

    public PageMonResDto(int totalPages, long totalElements, int perPage, int numberOfElements, int currentPage, boolean first, boolean last, List<MonDto> listMon) {
        super(totalPages, totalElements, perPage, numberOfElements, currentPage, first, last);
        this.listMon = listMon;
    }

    public List<MonDto> getListMon() {
        return listMon;
    }

    public void setListMon(List<MonDto> listMon) {
        this.listMon = listMon;
    }
}


