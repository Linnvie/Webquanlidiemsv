import Table from "react-bootstrap/Table";
import { GetAllDiemByLTC,editDiemService,GetLTCByMaLTC} from "../../../../service/UserService";
import Card from "react-bootstrap/Card";
import { useEffect, useState } from "react";
import {AiFillEdit, AiFillDelete,AiOutlinePlus} from 'react-icons/ai'
import styles from '../../../../container/UserManage.module.scss'
import classNames from "classnames/bind";
import { ModalEditDiem } from "../ManageDiem/ModalEditDiem";
import { useParams } from 'react-router-dom';
import { Pagination } from 'antd'; 
import { AlertCustom} from "../../../Alert/Alert";

const cx = classNames.bind(styles);

const DiemByLTC = () => {
  const [paginate, setPaginate] = useState({});
  const [isOpenEditModalDiem, setIsOpenEditModalDiem] = useState(false)
  const [isDiemEdit, setIsDiemEdit] = useState({})
  const [listDiem, setListDiem] = useState([]);
  const [alert, setAlert] = useState(false);
  const [item, setItem] = useState(false);
  const [searchTerm, setSearchTerm] = useState('');
  const [lopTinChi, setLopTinChi] = useState({});

  useEffect(() => {
    async function get() {   
      getAllDiemFromReact();    
      await getLopTinChi();  
      
    }
    get()
  }, []);

  const { maLTC } = useParams();

  let getLopTinChi = async() => {
    let response = await GetLTCByMaLTC(maLTC);
    console.log(response)
      if (response.data.code==200  && response.data.data) {   
        setLopTinChi(response.data.data)
        
        };
      }
 
  let togleDiemEditModal = () => {
    setIsOpenEditModalDiem(!isOpenEditModalDiem)
  }

  let getAllDiemFromReact = async() => {
    const response = await GetAllDiemByLTC(maLTC,{"perPage": 10,
                                                "currentPage": 1});
    if(response.data.data===null){       
      setListDiem([])
      return
    }
      setListDiem(response.data.data.listBangDiem);
      setPaginate({"totalPages": response.data.data.totalPages,
                  "currentPage": response.data.data.currentPage,
                  "perPage": response.data.data.perPage})
  console.log(paginate)
  }


  const handlePageSizeChange = async (current, pageSize) => {
 
    const response = await GetAllDiemByLTC(maLTC,{"perPage": pageSize,
                                                    "currentPage": current});
        if(response.data.data===null){       
          setListDiem([])
            return
        }
        setListDiem(response.data.data.listBangDiem);
        setPaginate({"totalPages": response.data.data.totalPages,
                    "currentPage": response.data.data.currentPage,
                    "perPage": response.data.data.perPage})
 
  };

  const handleEditDiem = (diem) => {
    setIsOpenEditModalDiem(true)
    setIsDiemEdit(diem)
  }

  const handleDiemChange = (newlist) => {
    setListDiem(newlist);
  };

  const handleChildValueChange = (newValue) => {
    setAlert(true); 
    setItem({"message": newValue.message,
            "type":newValue.type,
            "title":newValue.title,
           "onClose":{handleCloseAlert}})
  };

  const handleCloseAlert = () => {
    setAlert(false); 
  };


  return (
    <div className="users-container">
      {alert && (<AlertCustom className={cx("alert-container")} 
                      item={item}
                      onClose={handleCloseAlert}/>)}
     
      {isOpenEditModalDiem && 
      
      <ModalEditDiem
          isOpenn = {isOpenEditModalDiem}
          togleFromParent = {togleDiemEditModal}
          currentDiem = {isDiemEdit}
          paginate = {paginate}
          listDiem={handleDiemChange}
          onValueChange={handleChildValueChange}
      /> 
      }
      <h3 className="title text-center mt-3 bold">Bảng điểm sinh viên lớp tín chỉ {maLTC}</h3>

      <div style={{display:"flex", justifyContent:"center", marginTop:"40px"}}>
      <div className={cx("wrapper-bangdiem")}>
        <Card style={{ width: "30rem", marginLeft:"40px"}}>
          <Card.Body>
            {/* <Card.Title>Card Title</Card.Title> */}
            <div className={cx("desc-bangdiem")}>
              <Card.Text>Tên môn - Mã môn: {lopTinChi.tenMon} - {lopTinChi.maMon} </Card.Text>
              <p></p>
            </div>
            <div className={cx("desc-bangdiem")}>
              <Card.Text>Giảng viên : {lopTinChi.giangVien}</Card.Text>
              <p></p>
            </div>
            <div className={cx("desc-bangdiem")}>
              <Card.Text>Học kì- Năm: {lopTinChi.hocKi} - {lopTinChi.nam}</Card.Text>
              <p></p>
            </div>
            <div className={cx("desc-bangdiem")}>
              <Card.Text>Trạng thái : {lopTinChi.trangThai ? "Mở" : "Đóng"}</Card.Text>
              <p></p>
            </div>
            <div className={cx("desc-bangdiem")}>
              <Card.Text>Khóa sổ: {lopTinChi.dong ? "Chưa khóa" : "Đã khóa"}</Card.Text>
              <p></p>
            </div>
          
          </Card.Body>
        </Card>
      </div>

      <div className={cx("wrapper-bangdiem")}>
        <Card style={{ width: "25rem" , marginLeft:"40px"}}>
          
          <Card.Body>
            {/* <Card.Title>Card Title</Card.Title> */}
            <div className={cx("desc-bangdiem")}>
              <Card.Text>Hệ số 1 : {lopTinChi.heSo1}</Card.Text>
              <p></p>
            </div>
            <div className={cx("desc-bangdiem")}>
              <Card.Text>Hệ số 2 : {lopTinChi.heSo2}</Card.Text>
              <p></p>
            </div>
            <div className={cx("desc-bangdiem")}>
              <Card.Text>Hệ số 3 : {lopTinChi.heSo3}</Card.Text>
              <p></p>
            </div>
            <div className={cx("desc-bangdiem")}>
              <Card.Text>Hệ số 4 : {lopTinChi.heSo4} </Card.Text>
              <p></p>
            </div>
            <div className={cx("desc-bangdiem")}>
              <Card.Text>Hệ số 5 : {lopTinChi.heSo5}</Card.Text>
              <p></p>
            </div>
          
          </Card.Body>
        </Card>
      </div>
      </div>
    
      <div className="users-table mt-3">
      {listDiem.length>0 && (
        <Table striped bordered hover>
          <thead>
            <tr>
              <th>STT</th>
              <th>MSSV</th>
              <th>Điểm hệ 1</th>
              <th>Điểm hệ 2</th>
              <th>Điểm hệ 3</th>
              <th>Điểm hệ 4</th>
              <th>Điểm hệ 5</th>
              <th>Lần</th>
              
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
           
          {listDiem.map((item, index) => {
                return(
                  <tr key={index}>
                 <>      
                  <td>{index+1}</td>
                  <td>{item.mssv}</td>
                  <td>{item.diemHe1}</td>
                  <td>{item.diemHe2}</td>
                  <td>{item.diemHe3}</td>
                  <td>{item.diemHe4}</td>
                  <td>{item.diemHe5}</td>
                  <td>{item.lan}</td>
                  <td>
                    <button className={cx("btn-edit")} onClick={() => handleEditDiem(item)}><AiFillEdit/></button>
                    {/* <button className={cx("btn-delete")} onClick={() =>handleDeleteSV(item)}><AiFillDelete /></button> */}
                    

                  </td>
                 </>
            </tr>
                )
              })}
              
          </tbody>
          <Pagination current={paginate.currentPage}  total={paginate.totalPages*10}
          onChange={(current, size) => handlePageSizeChange(current, size)}/>
        </Table>)}
      </div>
    </div>
  );
};

export { DiemByLTC};
