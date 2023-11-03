package com.ptit.apiquanlidiem.converter;

import com.ptit.apiquanlidiem.dto.ChiTietLTCResDto;
import com.ptit.apiquanlidiem.dto.LopTinChiDto;
import com.ptit.apiquanlidiem.entity.GiangVienLTCEntity;
import com.ptit.apiquanlidiem.entity.LopTinChiEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LopTinChiConverter {

    public ChiTietLTCResDto toDto(LopTinChiEntity entity){
        ChiTietLTCResDto chiTietLTCResDto = new ChiTietLTCResDto();
        chiTietLTCResDto.setMaLTC(entity.getMaLopTinChi());
        chiTietLTCResDto.setMaMon(entity.getMaMon());
        chiTietLTCResDto.setTenMon(entity.getMon().getTenMon());
        chiTietLTCResDto.setHeSo1(entity.getMon().getHeSo1());
        chiTietLTCResDto.setHeSo2(entity.getMon().getHeSo2());
        chiTietLTCResDto.setHeSo3(entity.getMon().getHeSo3());
        chiTietLTCResDto.setHeSo4(entity.getMon().getHeSo4());
        chiTietLTCResDto.setHeSo5(entity.getMon().getHeSo5());
        chiTietLTCResDto.setNam(entity.getNam());
        chiTietLTCResDto.setHocKi(entity.getHocKi());
        chiTietLTCResDto.setTrangThai(entity.getTrangThai());
        chiTietLTCResDto.setDong(entity.getDong());
        List<String> giangVien = new ArrayList<>();
        for(GiangVienLTCEntity giangVienLTC: entity.getListGiangVienLTC()){
            giangVien.add(giangVienLTC.getGiangVien().getHoLot()+" "+giangVienLTC.getGiangVien().getTen());
        }
        return chiTietLTCResDto;
    }

    public LopTinChiDto toDtoSimp(LopTinChiEntity entity){
        LopTinChiDto lopTinChiDto = new LopTinChiDto();
        lopTinChiDto.setMaLopTinChi(entity.getMaLopTinChi());
        lopTinChiDto.setMaMon(entity.getMaMon());
        lopTinChiDto.setNam(entity.getNam());
        lopTinChiDto.setHocKi(entity.getHocKi());
        lopTinChiDto.setTrangThai(entity.getTrangThai());
        lopTinChiDto.setDong(entity.getDong());
        lopTinChiDto.setSlDangKi(entity.getSlDangKi());
        lopTinChiDto.setSoSVToiThieu(entity.getSoSVToiThieu());
        lopTinChiDto.setSoSVToiDa(entity.getSoSVToiDa());
        List<String> giangVien = new ArrayList<>();
        for(GiangVienLTCEntity giangVienLTC: entity.getListGiangVienLTC()){
            giangVien.add(giangVienLTC.getGiangVien().getMaGiangVien());
        }
        lopTinChiDto.setListGiangVien(giangVien);
        return lopTinChiDto;
    }
}
