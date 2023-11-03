package com.ptit.apiquanlidiem.dto;

import java.util.List;

public class PageLTCResDto extends PageableDto{

    private List<LopTinChiDto> listLTC;

    public PageLTCResDto() {
    }

    public PageLTCResDto(int totalPages, long totalElements, int perPage, int numberOfElements, int currentPage, boolean first, boolean last, List<LopTinChiDto> listLTC) {
        super(totalPages, totalElements, perPage, numberOfElements, currentPage, first, last);
        this.listLTC = listLTC;
    }

    public List<LopTinChiDto> getListLTC() {
        return listLTC;
    }

    public void setListLTC(List<LopTinChiDto> listLTC) {
        this.listLTC = listLTC;
    }
}
