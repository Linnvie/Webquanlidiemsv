import styles from "../BangDiem/BangDiem.module.scss";
import classNames from "classnames/bind";
import Table from "react-bootstrap/Table";

const cx = classNames.bind(styles);
function BangDiemItem({ item }) {
  
    return (
        <div>


<div className={cx("head-bd")}>Học kì:{item.hocKi} - Năm học:{item.nam}</div>
       
             <Table striped bordered hover className={cx('table-bangdiem')}>           
             <thead>
               <tr>
                 <th>STT</th>
                 <th>Mã môn</th>
                 <th>Tên môn</th>
                 <th>Mã lớp tín chỉ</th>
                 <th>Số tín chỉ</th>
                 <th>%CC</th>
                 <th>%KT</th>
                 <th>%SE</th>
                 <th>%Thi</th>
                 <th>Điểm CC</th>
                 <th>Điểm KT</th>
                 <th>Điểm TH</th>
                 <th>Điểm SE</th>
                 <th>Thi lần 1</th>
                 <th>TK(10)</th>
                 <th>TK(CK)</th>
                 <th>KQ1</th>
                 <th>KQ</th>
               </tr>
             </thead>
             {item.listDiem.map((diem, index) => (
             <tbody>
               <tr>
                 <td>{index+1}</td>
                 <td>{diem.maMon}</td>
                 <td>{diem.tenMon}</td>
                 <td>{diem.maLTC}</td>
                 <td>{diem.soTinChi}</td>
                 <td>{diem.heSo1*100}</td>
                 <td>{diem.heSo2*100}</td>
                 <td>{diem.heSo4*100}</td>
                 <td>{diem.heSo3*100}</td>
                 <td>{diem.diemHe1}</td>
                 <td>{diem.diemHe2}</td>
                 <td></td>
                 <td>{diem.diemHe4}</td>
                 <td>{diem.diemHe3}</td>
                 <td>{diem.diemTrungBinhHe10}</td>
                 <td>{diem.diemTrungBinhHe4}</td>
                 <td>{diem.diemChu}</td>
                 <td>{diem.kq}</td>
                 
               </tr>                          
             </tbody>
             ))}
           </Table>
         

         
          <div style={{ display: 'flex', justifyContent: "space-around" }}>
          <ul >
        <li>Điểm trung bình học kì hệ 4: {item.tbHocKi4}</li>
        <li>Điểm trung bình học kì hệ 10: {item.tbHocKi10}</li>
        <li>Số tín chỉ đạt học kì: {item.tinChiDatHk}</li>
      </ul>

   
      <ul>
        <li>Điểm trung bình tích lũy hệ 4: {item.tbTichLuy4}</li>
        <li>Điểm trung bình tích lũy hệ 10: {item.tbTichLuy10}</li>
        <li>Số tín chỉ tích lũy: {item.tinChiTichLuy}</li>
      </ul>
        </div>  
     


        </div>

    )};

export {BangDiemItem}