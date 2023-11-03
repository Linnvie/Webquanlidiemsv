import Table from "react-bootstrap/Table";
import { GetAllStudentLop, createNewSVService} from "../../../../service/UserService";

import { useEffect, useState } from "react";
import {AiFillEdit, AiFillDelete,AiOutlinePlus} from 'react-icons/ai'
import styles from '../../../../container/UserManage.module.scss'
import classNames from "classnames/bind";
import { ModalSinhVien } from "../../../../container/ModalSinhVien";
import { ModalEditSinhVien } from "../../../../container/ModalEditSinhVien";
import { useParams } from 'react-router-dom';
import { Pagination } from 'antd'; 
import { AlertCustom} from "../../../Alert/Alert";

const cx = classNames.bind(styles);

const SinhVienByLop = () => {
  const [paginate, setPaginate] = useState({});
  const [isOpenMadalSV, setIsOpenMadalSV] = useState(false)
  const [isOpenEditModalSV, setIsOpenEditModalSV] = useState(false)
  const [isSVEdit, setIsSVEdit] = useState({})
  const [listSV, setListSV] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');

  const [alert, setAlert] = useState('');
  const [item, setItem] = useState('');



  useEffect(() => {
    async function get() {   
      getAllSvFromReact();    
    }
    get()
  }, []);

  const {maLop} = useParams();
 

  let handleAddNewSV= () => {
    setIsOpenMadalSV(true)
  }

  let togleSVModal = () => {
    setIsOpenMadalSV(!isOpenMadalSV)
  }

  let togleSVEditModal = () => {
    setIsOpenEditModalSV(!isOpenEditModalSV)
  }

  let getAllSvFromReact = async() => {
    const response = await GetAllStudentLop(maLop,{"perPage": 10,
                                                "currentPage": 1});
    if(response.data.data===null){       
      setListSV([])
      return
  }
      setListSV(response.data.data.listSV);
      setPaginate({"totalPages": response.data.data.totalPages,
                  "currentPage": response.data.data.currentPage,
                  "perPage": response.data.data.perPage})
  }


  const handlePageSizeChange = async (current, pageSize) => {
 
    const response = await GetAllStudentLop(maLop,{"perPage": pageSize,
                                                    "currentPage": current});
        if(response.data.data===null){       
          setListSV([])
            return
        }
        setListSV(response.data.data.listSV);
        setPaginate({"totalPages": response.data.data.totalPages,
                    "currentPage": response.data.data.currentPage,
                    "perPage": response.data.data.perPage})
 
  };


  const handleEditSV = (sv) => {
    setIsOpenEditModalSV(true)
    setIsSVEdit(sv)
  }

  const handleSVChange = (newlist) => {
    setListSV(newlist);
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

  function renderContent(type) {
    if (type === 'STUDYING') {
      return "Đang học"
    } else if (type === 'RESERVE') {
      return "Bảo lưu";
    } else if (type === 'DROPOUT') {
      return "Nghỉ học";
    } else if (type === 'COMPLETE') {
      return "Đã tốt nghiệp";
    }
  }


  return (
    <div className="users-container">

{alert && (<AlertCustom className={cx("alert-container")} 
                      item={item}
                      onClose={handleCloseAlert}/>)}
      <ModalSinhVien
          isOpenn = {isOpenMadalSV}
          togleFromParent = {togleSVModal}
          listSV = {handleSVChange}
          paginate={paginate}
          maLop={maLop}
          onValueChange={handleChildValueChange}
      />
      {isOpenEditModalSV && 
      
      <ModalEditSinhVien
          isOpenn = {isOpenEditModalSV}
          togleFromParent = {togleSVEditModal}
          currentSV = {isSVEdit}
          listSV = {handleSVChange}
          paginate={paginate}
          maLop={maLop}
          onValueChange={handleChildValueChange}
      /> 
      }
      <h3 className="title text-center mt-3 bold">Danh sách sinh viên lớp {maLop}</h3>
    
      
      <button 
        className="btn btn-primary px-3 "
        onClick={handleAddNewSV}
     >
       <AiOutlinePlus/>
        Thêm mới sinh viên
     </button>
    
      <div className="users-table mt-3">
      {listSV.length>0 && (
        <Table striped bordered hover>
          <thead>
            <tr>
              <th>STT</th>
              <th>MSSV</th>
              <th>Họ lót</th>
              <th>Tên</th>
              <th>Giới Tính</th>
              <th>Ngày Sinh</th>
              <th>Mã khoa</th>
              <th>Mã lớp</th>
              <th>Niên khóa</th>
              <th>Hệ học</th>
              <th>Trạng thái</th>
              
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
           
          {listSV.map((item, index) => {
                return(
                  <tr key={index}>
                 <>      
                  <td>{index+1}</td>
                  <td>{item.mssv}</td>
                  <td>{item.hoLot}</td>
                  <td>{item.ten}</td>
                  <td>{item.gioiTinh === false ? "Nữ" : "Nam"}</td>
                  <td>{item.ngaySinh}</td>
                  <td>{item.maKhoa}</td>
                  <td>{item.maLop}</td>
                  <td>{item.nienKhoa}</td>
                  <td>{item.heHoc}</td>
                  <td> {renderContent(item.status)}</td>
                  <td>
                    <button className={cx("btn-edit")} onClick={() => handleEditSV(item)}><AiFillEdit/></button>
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

export {SinhVienByLop};
