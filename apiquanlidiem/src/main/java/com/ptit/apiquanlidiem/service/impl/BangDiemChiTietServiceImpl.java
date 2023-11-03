package com.ptit.apiquanlidiem.service.impl;

import com.ptit.apiquanlidiem.converter.BangDiemChiTietConverter;
import com.ptit.apiquanlidiem.dto.*;
import com.ptit.apiquanlidiem.entity.*;
import com.ptit.apiquanlidiem.exception.DuplicateResourceException;
import com.ptit.apiquanlidiem.exception.ResourceNotFoundException;
import com.ptit.apiquanlidiem.repository.BangDiemChiTietRepository;
import com.ptit.apiquanlidiem.repository.LopTinChiRepository;
import com.ptit.apiquanlidiem.repository.SinhVienRepository;
import com.ptit.apiquanlidiem.service.BangDiemChiTietService;
import com.ptit.apiquanlidiem.util.ExcelUtil;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BangDiemChiTietServiceImpl implements BangDiemChiTietService {

    public static final int COLUMN_INDEX_MSSV = 0;
    public static final int COLUMN_INDEX_MALTC = 1;
    public static final int COLUMN_INDEX_DIEMHE1 = 2;
    public static final int COLUMN_INDEX_DIEMHE2 = 3;
    public static final int COLUMN_INDEX_DIEMHE3 = 4;
    public static final int COLUMN_INDEX_DIEMHE4 = 5;
    public static final int COLUMN_INDEX_DIEMHE5 = 6;
    public static final int COLUMN_INDEX_LAN = 7;

    @Autowired
    private BangDiemChiTietRepository bangDiemChiTietRepository;

    @Autowired
    private SinhVienRepository svRepository;

    @Autowired
    private BangDiemChiTietConverter bangDiemChiTietConverter;

    @Autowired
    private LopTinChiRepository lopTinChiRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ExcelUtil excelUtil;


    @Override
    public List<BangDiemTheoKiDto> findAllByMSSV(String mssv) {

        SinhVienEntity sinhVien = this.svRepository.findOneByMssv(mssv.toUpperCase())
                .orElseThrow(() -> new ResourceNotFoundException("Sinh viên không tồn tại"));

        List<BangDiemChiTietEntity> listEntity
                = this.bangDiemChiTietRepository.findBySinhVien(sinhVien);

        if(listEntity==null){
            return null;
        }

        List<BangDiemTheoKiDto> response = new ArrayList<BangDiemTheoKiDto>();

        Map<String, List<TTBangDiemDto>> map = groupByHocKiAndNam(listEntity);

        int tinChiTichLuy=0;
        float tong4TichLuy=0;
        float tong10TichLuy=0;
        int tongTinChiDaDK=0;

        for(Map.Entry<String, List<TTBangDiemDto>> entry : map.entrySet()) {
            String[] key = entry.getKey().split("-");
            List<TTBangDiemDto> list = entry.getValue();
            int tinChiDat=0;
            float tong4=0;
            float tong10=0;
            int tongTinChi=0;
            for(TTBangDiemDto item: list){
                int tinChi= item.getSoTinChi();
                if(item.getKq().equals("Đạt")){
                    tinChiDat+= tinChi;
                }
                tongTinChi+=tinChi;
                tong4+= item.getDiemTrungBinhHe4()*tinChi;
                tong10+= item.getDiemTrungBinhHe10()*tinChi;
            }

            tongTinChiDaDK+=tongTinChi;
            tinChiTichLuy+=tinChiDat;
            tong4TichLuy+=tong4;
            tong10TichLuy+=tong10;

            BangDiemTheoKiDto dto = new BangDiemTheoKiDto();
            dto.setNam(Integer.parseInt(key[0]));
            dto.setHocKi(Integer.parseInt(key[1]));

            // Tính tích lũy từng kì
            dto.setTinChiDatHk(tinChiDat);
            dto.setTbHocKi4(((float) Math.round((float)
                    (tong4/tongTinChi)* 10)) / 10);
            dto.setTbHocKi10(((float) Math.round((float)
                    (tong10/tongTinChi)* 10)) / 10);

            // Tính tích lũy tất cả các kì tới học kì hiện tại
            dto.setTinChiTichLuy(tinChiTichLuy);
            dto.setTbTichLuy4(((float) Math.round((float)
                    (tong4TichLuy/tongTinChiDaDK)* 10)) / 10);
            dto.setTbTichLuy10(((float) Math.round((float)
                    (tong10TichLuy/tongTinChiDaDK)* 10)) / 10);

            dto.setListDiem(list);

            response.add(dto);
        }

        return response;
    }

    @Override
    public List<TTBangDiemDto> findAllByMSSVAndHKAndNam(String mssv, int hocKi, int nam) {
        SinhVienEntity sinhVien=this.svRepository.findOneByMssv(mssv.toUpperCase())
                .orElseThrow(() -> new ResourceNotFoundException("Sinh viên không tồn tại"));

        List<BangDiemChiTietEntity> listEntity
                = this.bangDiemChiTietRepository.findByMssvAndHocKiAndNam(mssv.toUpperCase(), hocKi, nam);

        return listEntity.size()==0
                ?null
                : listEntity.stream().map(item -> this.bangDiemChiTietConverter.toDto(item))
                .collect(Collectors.toList());
    }

    @Override
    public PageBangDiemResDto findAllBangDiemCTByMaLTC(String maLTC, int currentPage, int perPage) {

        Pageable paging = PageRequest.of(currentPage, perPage);

        LopTinChiEntity lopTinChi=this.lopTinChiRepository.findOneByMaLopTinChi(maLTC.toUpperCase())
                .orElseThrow(() -> new ResourceNotFoundException("Lớp tín chỉ không tồn tại"));

        Page<BangDiemChiTietEntity> pageListEntity
                = this.bangDiemChiTietRepository.findByLopTinChi(lopTinChi, paging);

        return pageListEntity.getTotalElements() > 0
                ? new PageBangDiemResDto(pageListEntity.getTotalPages(),
                pageListEntity.getTotalElements(),
                pageListEntity.getSize(),
                pageListEntity.getNumberOfElements(),
                pageListEntity.getNumber() + 1,
                pageListEntity.isFirst(),
                pageListEntity.isLast(),
                pageListEntity.getContent().stream()
                        .map(item -> this.bangDiemChiTietConverter.toDto(item))
                        .collect(Collectors.toList()))

                : null;
    }

    @Override
    public TTBangDiemDto findOneByMSSVAndMaLTC(String mssv, String maLTC) {
        BangDiemChiTietEntity entity = this.bangDiemChiTietRepository.findOneById(new BangDiemChiTietKey(mssv.toUpperCase(), maLTC))
                .orElseThrow(()->new ResourceNotFoundException("Thông tin không hợp lệ"));
        return this.bangDiemChiTietConverter.toDto(entity);

    }

    //đăng kí môn học
    @Override
    @Transactional
    public int save(BangDiemChiTietDto bangDiemChiTiet) {

        LopTinChiEntity ltcEntity = this.lopTinChiRepository.findOneByMaLopTinChi(bangDiemChiTiet.getId().getMaLTC().toUpperCase())
                .orElseThrow(()-> new ResourceNotFoundException("Không tồn tại lớp tín chỉ này"));
        this.svRepository.findOneByMssv(bangDiemChiTiet.getId().getMssv().toUpperCase())
                .orElseThrow(()-> new ResourceNotFoundException("Không tồn tại sinh viên này"));

        if(ltcEntity.getTrangThai() && ltcEntity.getSlDangKi()<ltcEntity.getSoSVToiDa()) {

            BangDiemChiTietEntity diemEntity
                    = this.bangDiemChiTietRepository.findOneById(
                            new BangDiemChiTietKey(bangDiemChiTiet.getId().getMssv().toUpperCase()
                                    ,bangDiemChiTiet.getId().getMaLTC().toUpperCase())).orElse(null);
            if(diemEntity!=null){
                throw new DuplicateResourceException("Bạn đã đăng kí môn này");
            }
            this.bangDiemChiTietRepository.save(modelMapper.map(bangDiemChiTiet, BangDiemChiTietEntity.class));
            ltcEntity.setSlDangKi(ltcEntity.getSlDangKi()+1);
            this.lopTinChiRepository.save(ltcEntity);
            return 1;

        }else {
            return 0;
        }
    }

    @Override
    public void update(BangDiemChiTietDto bangDiemChiTiet) {
        bangDiemChiTiet.getId().setMssv(bangDiemChiTiet.getId().getMssv().toUpperCase());
        bangDiemChiTiet.getId().setMaLTC(bangDiemChiTiet.getId().getMaLTC().toUpperCase());

        MonEntity monEntity = lopTinChiRepository.findOneByMaLopTinChi(bangDiemChiTiet.getId().getMaLTC())
                                .orElseThrow(()->new ResourceNotFoundException("Không tồn tại lớp tín chỉ này"))
                                .getMon();
        // check nhập khác hệ số (vd môn không có hệ số 4 mà nhập)
        if(bangDiemChiTiet.getDiemHe4() !=0F ){
            if(monEntity.getHeSo4()==0F){
                throw new IllegalArgumentException("Vui lòng nhập điểm theo đúng hệ số môn ");
            }
        }
        if(bangDiemChiTiet.getDiemHe5()!=0F ){
            if(monEntity.getHeSo5()==0F){
                throw new IllegalArgumentException("Vui lòng nhập điểm theo đúng hệ số môn");
            }
        }

        bangDiemChiTietRepository.findOneById(
                        new BangDiemChiTietKey(bangDiemChiTiet.getId().getMssv(),bangDiemChiTiet.getId().getMaLTC()))
                .orElseThrow(()-> new ResourceNotFoundException("Sinh viên không học môn này"));
        this.bangDiemChiTietRepository.save(modelMapper.map(bangDiemChiTiet, BangDiemChiTietEntity.class));
    }

    @Override
    @Transactional
    public void getFromExcelFileAndSave(MultipartFile file){
        List<BangDiemChiTietEntity> listBangDiem = new ArrayList<>();
        if (!excelUtil.checkExcelFormat(file)){
            throw new IllegalArgumentException("Vui lòng sử dụng file excel");
        }

        try {
            Workbook workbook = excelUtil.getWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row nextRow = iterator.next();
                if (nextRow.getRowNum() == 0) {
                    // Ignore header
                    continue;
                }

                BangDiemChiTietEntity bangDiemChiTiet = new BangDiemChiTietEntity();
                Iterator<Cell> cellIterator = nextRow.cellIterator();
                while (cellIterator.hasNext()) {

                    //Read cell
                    Cell cell = cellIterator.next();
                    Object cellValue = excelUtil.getCellValue(cell);
                    if (cellValue == null || cellValue.toString().isEmpty()) {
                        continue;
                    }
                    int columnIndex = cell.getColumnIndex();
                    MonEntity monEntity=null;
                    switch (columnIndex) {
                        case COLUMN_INDEX_MSSV:
                            bangDiemChiTiet.getId().setMssv((String) excelUtil.getCellValue(cell));
                            break;
                        case COLUMN_INDEX_MALTC:
                            bangDiemChiTiet.getId().setMaLTC((String) excelUtil.getCellValue(cell));
                            break;
                        case COLUMN_INDEX_DIEMHE1:
                            bangDiemChiTiet.setDiemHe1((Float)excelUtil.getCellValue(cell));
                            break;
                        case COLUMN_INDEX_DIEMHE2:
                            bangDiemChiTiet.setDiemHe2((Float)excelUtil.getCellValue(cell));
                            break;
                        case COLUMN_INDEX_DIEMHE3:
                            bangDiemChiTiet.setDiemHe3((Float) excelUtil.getCellValue(cell));
                            break;
                        case COLUMN_INDEX_DIEMHE4:
                            bangDiemChiTiet.setDiemHe4((Float) excelUtil.getCellValue(cell));
                            monEntity = lopTinChiRepository.findOneByMaLopTinChi(bangDiemChiTiet.getId().getMaLTC())
                                    .orElseThrow(()->new ResourceNotFoundException("Không tồn tại lớp tín chỉ này"))
                                    .getMon();
                            if(bangDiemChiTiet.getDiemHe4() !=0F && bangDiemChiTiet.getDiemHe4() != null){
                                if(monEntity.getHeSo4()==0F){
                                    throw new IllegalArgumentException("Vui lòng nhập điểm theo đúng hệ số môn ");
                                }
                            }
                            break;
                        case COLUMN_INDEX_DIEMHE5:
                            bangDiemChiTiet.setDiemHe5((Float) excelUtil.getCellValue(cell));
                            if(bangDiemChiTiet.getDiemHe5() !=0F && bangDiemChiTiet.getDiemHe5() != null){
                                if(monEntity.getHeSo5()==0F){
                                    throw new IllegalArgumentException("Vui lòng nhập điểm theo đúng hệ số môn ");
                                }
                            }
                            break;
                        case COLUMN_INDEX_LAN:
                            bangDiemChiTiet.setLan((Integer) excelUtil.getCellValue(cell));
                            break;
                        default:
                            break;
                    }

                }
                bangDiemChiTietRepository.findOneById(
                                new BangDiemChiTietKey(bangDiemChiTiet.getId().getMssv(),bangDiemChiTiet.getId().getMaLTC()))
                        .orElseThrow(()-> new ResourceNotFoundException("Sinh viên không học môn này"));
                this.bangDiemChiTietRepository.save(bangDiemChiTiet);
            }
            workbook.close();
            file.getInputStream().close();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] generateExcel(String maLTC) {
        LopTinChiEntity lopTinChi=this.lopTinChiRepository.findOneByMaLopTinChi(maLTC.toUpperCase())
                .orElseThrow(() -> new ResourceNotFoundException("Lớp tín chỉ không tồn tại"));

        List<BangDiemChiTietEntity> bangDiemChiTietEntityList
                = this.bangDiemChiTietRepository.findByLopTinChi(lopTinChi);

        List<TTBangDiemDto> list = bangDiemChiTietEntityList.stream()
                .map(item -> this.bangDiemChiTietConverter.toDto(item))
                .collect(Collectors.toList());

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(lopTinChi.getMaLopTinChi()+"-"+lopTinChi.getMon().getMaMon());
        XSSFRow row0 = sheet.createRow(0);
        row0.createCell(0).setCellValue("Mã lớp tín chỉ: "+ lopTinChi.getMaLopTinChi());
        row0.createCell(1).setCellValue("Môn: "+lopTinChi.getMaMon()+"-"+lopTinChi.getMon().getTenMon());
        row0.createCell(2).setCellValue("Học kì: "+lopTinChi.getHocKi()+"-"+"Năm: "+lopTinChi.getNam());
        row0.createCell(3).setCellValue("Hệ số 1: "+lopTinChi.getMon().getHeSo1());
        row0.createCell(4).setCellValue("Hệ số 1: "+lopTinChi.getMon().getHeSo2());
        row0.createCell(5).setCellValue("Hệ số 1: "+lopTinChi.getMon().getHeSo3());
        row0.createCell(6).setCellValue("Hệ số 1: "+lopTinChi.getMon().getHeSo4());
        row0.createCell(7).setCellValue("Hệ số 1: "+lopTinChi.getMon().getHeSo5());
        XSSFRow row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("MSSV");
        row1.createCell(1).setCellValue("Điểm hệ 1");
        row1.createCell(2).setCellValue("Điểm hệ 2");
        row1.createCell(3).setCellValue("Điểm hệ 3");
        row1.createCell(4).setCellValue("Điểm hệ 4");
        row1.createCell(5).setCellValue("Điểm hệ 5");
        row1.createCell(6).setCellValue("Điểm trung bình hệ 10");
        row1.createCell(7).setCellValue("Điểm trung bình hệ 4");
        row1.createCell(8).setCellValue("Điểm chữ");
        row1.createCell(9).setCellValue("Kết quả");


        int dataRowIndex = 2;

        for (TTBangDiemDto item : list) {
            XSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(item.getMssv());
            dataRow.createCell(1).setCellValue(item.getDiemHe1());
            dataRow.createCell(2).setCellValue(item.getDiemHe2());
            dataRow.createCell(3).setCellValue(item.getDiemHe3());
            dataRow.createCell(4).setCellValue(item.getDiemHe4());
            dataRow.createCell(5).setCellValue(item.getDiemHe5());
            dataRow.createCell(6).setCellValue(item.getDiemTrungBinhHe10());
            dataRow.createCell(7).setCellValue(item.getDiemTrungBinhHe4());
            dataRow.createCell(8).setCellValue(item.getDiemChu());
            dataRow.createCell(9).setCellValue(item.getKq());
            dataRowIndex++;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            workbook.write(byteArrayOutputStream);
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return byteArrayOutputStream.toByteArray();
//        ServletOutputStream ops = null;
//        try {
//            ops = response.getOutputStream();
//            workbook.write(ops);
//            workbook.close();
//            ops.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }


    private Map<String, List<TTBangDiemDto>> groupByHocKiAndNam(List<BangDiemChiTietEntity> list) {
            Map<String, List<TTBangDiemDto>> grouped = new HashMap<>();

            for (BangDiemChiTietEntity item : list) {
                String key = item.getLopTinChi().getNam() + "-" + item.getLopTinChi().getHocKi();

                if (!grouped.containsKey(key)) {
                    grouped.put(key, new ArrayList<>());
                }

                grouped.get(key).add(this.bangDiemChiTietConverter.toDto(item));
            }

            return grouped;
        }

}
