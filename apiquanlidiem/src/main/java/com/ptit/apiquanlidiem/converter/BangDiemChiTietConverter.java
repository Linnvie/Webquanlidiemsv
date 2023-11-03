package com.ptit.apiquanlidiem.converter;

import com.ptit.apiquanlidiem.dto.TTBangDiemDto;
import com.ptit.apiquanlidiem.entity.BangDiemChiTietEntity;
import org.springframework.stereotype.Component;

@Component
public class BangDiemChiTietConverter {

    public TTBangDiemDto toDto(BangDiemChiTietEntity entity){

        String kq= "Đạt";
        TTBangDiemDto dto = new TTBangDiemDto();
        dto.setMssv(entity.getSinhVien().getMssv());
        dto.setMaMon(entity.getLopTinChi().getMon().getMaMon());
        dto.setTenMon(entity.getLopTinChi().getMon().getTenMon());
        dto.setMaLTC(entity.getLopTinChi().getMaLopTinChi());
        dto.setHocKi(entity.getLopTinChi().getHocKi());
        dto.setNam(entity.getLopTinChi().getNam());
        dto.setLan(entity.getLan());
        dto.setSoTinChi(entity.getLopTinChi().getMon().getSoTinChi());
        dto.setHeSo1(entity.getLopTinChi().getMon().getHeSo1());
        dto.setHeSo2(entity.getLopTinChi().getMon().getHeSo2());
        dto.setHeSo3(entity.getLopTinChi().getMon().getHeSo3());
        dto.setHeSo4(entity.getLopTinChi().getMon().getHeSo4());
        dto.setHeSo5(entity.getLopTinChi().getMon().getHeSo5());

        if(entity.getDiemHe1()!=null||entity.getDiemHe2()!=null||entity.getDiemHe3()!=null||entity.getDiemHe4()!=null||entity.getDiemHe5()!=null){
            dto.setDiemHe1(entity.getDiemHe1()==null?0: entity.getDiemHe1());
            dto.setDiemHe2(entity.getDiemHe2()==null?0: entity.getDiemHe2());
            dto.setDiemHe3(entity.getDiemHe3()==null?0: entity.getDiemHe3());
            dto.setDiemHe4(entity.getDiemHe4()==null?0: entity.getDiemHe4());
            dto.setDiemHe5(entity.getDiemHe5()==null?0: entity.getDiemHe5());
            dto.setDiemTrungBinhHe10(((float) Math.round((float)
                    (dto.getDiemHe1() * dto.getHeSo1()
                    + dto.getDiemHe2() * dto.getHeSo2()
                    + dto.getDiemHe3() * dto.getHeSo3()
                    + dto.getDiemHe4() * dto.getHeSo4()
                    + dto.getDiemHe5() * dto.getHeSo5())* 10)) / 10);
        }else {
            dto.setDiemTrungBinhHe10(0.0F);
        }
        if(dto.getDiemTrungBinhHe10()<4.0){
            dto.setDiemTrungBinhHe4((float) 0);
            dto.setDiemChu("F");
            kq = "Không đạt";
        } else if(dto.getDiemTrungBinhHe10()<4.5){
            dto.setDiemTrungBinhHe4(1.0F);
            dto.setDiemChu("D");
        } else if(dto.getDiemTrungBinhHe10()<5.0){
            dto.setDiemTrungBinhHe4(1.5F);
            dto.setDiemChu("D+");
        } else if(dto.getDiemTrungBinhHe10()<6.4){
            dto.setDiemTrungBinhHe4(2.0F);
            dto.setDiemChu("C");
        } else if(dto.getDiemTrungBinhHe10()<7.0){
            dto.setDiemTrungBinhHe4(2.5F);
            dto.setDiemChu("C+");
        } else if(dto.getDiemTrungBinhHe10()<8.0){
            dto.setDiemTrungBinhHe4(3.0F);
            dto.setDiemChu("B");
        } else if(dto.getDiemTrungBinhHe10()<8.4){
            dto.setDiemTrungBinhHe4(3.5F);
            dto.setDiemChu("B+");
        } else if(dto.getDiemTrungBinhHe10()<9.0){
            dto.setDiemTrungBinhHe4(3.7F);
            dto.setDiemChu("A");
        } else {
            dto.setDiemTrungBinhHe4(4.0F);
            dto.setDiemChu("A+");
        }
        dto.setKq(kq);
        return dto;
    }


}
