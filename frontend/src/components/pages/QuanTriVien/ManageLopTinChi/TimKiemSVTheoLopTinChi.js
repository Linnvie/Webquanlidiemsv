import { useEffect, useState } from "react";
import { BrowserRouter as Router, Link } from 'react-router-dom';
import { SearchByKeyWordLTC,GetAllMon,GetAllLTCByMon } from "../../../../service/UserService";
import Table from "react-bootstrap/Table";
import {AiFillEdit,AiOutlinePlus,AiOutlineTeam,AiOutlineSolution} from 'react-icons/ai'
import classNames from "classnames/bind";
import { ModalLopTinChi } from "./ModalLopTinChi";
import { ModalEditLopTinChi } from "./ModelEditLopTinChi";
import '../../../Search/SearchComponent.scss';
import styles from '../../../../container/UserManage.module.scss'
import { Pagination } from 'antd';
import { AlertCustom} from "../../../Alert/Alert";

const cx = classNames.bind(styles);

const TimKiemSVTheoLopTinChi= () => {
const [paginate, setPaginate] = useState({});
const [isOpenMadalLTC, setIsOpenMadalLTC] = useState(false)
const [isOpenEditModalLTC, setIsOpenEditModalLTC] = useState(false)
const [isLTCEdit, setIsLTCEdit] = useState({})
const [listLopTC, setListLopTC] = useState([]);
const [searchTerm, setSearchTerm] = useState('');
const [listMon, setListMon] = useState([]);
const [maMon, setMaMon] = useState("");

const [item, setItem] = useState({});
const [alert, setAlert] = useState(false);


  useEffect(() => {
    async function get() {
      getAllMonFromReact(); 
      getAllLopTinChiFromReact();
    }
    get()
  }, [maMon]);


  
  let handleAddNewLTC = () => {
    setIsOpenMadalLTC(true)
  }

  let togleLTCModal = () => {
    setIsOpenMadalLTC(!isOpenMadalLTC)
  }

  let togleLTCEditModal = () => {
    setIsOpenEditModalLTC(!isOpenEditModalLTC)
  }

  const handleChange = (e) => {
    setSearchTerm(e.target.value);
  };

  const handleLTCChange = (newlist) => {
    setListLopTC(newlist);
  };

  const handleEditLTC = (lopTinChi) => {
    console.log("check edit user", lopTinChi);
    setIsOpenEditModalLTC(lopTinChi)
    setIsLTCEdit(lopTinChi)
  }

  const handlePageSizeChange = async (current, pageSize) => {
    const response = await SearchByKeyWordLTC(searchTerm,{"perPage": pageSize,
                                                        "currentPage": current});
        if(response.data.data===null){       
            setListLopTC([])
            return
        }
        setListLopTC(response.data.data.listLTC);
        setPaginate({"totalPages": response.data.data.totalPages,
                    "currentPage": response.data.data.currentPage,
                    "perPage": response.data.data.perPage})
 
  };

  let getAllMonFromReact = async() => {
    const response = await GetAllMon();
    if(response.data.data===null){       
      setListMon([])
      return
    }
      setListMon(response.data.data);

  }

  let getAllLopTinChiFromReact = async() => {
    const response = await SearchByKeyWordLTC("",{"perPage": 10,
                                                "currentPage": 1});
    if(response.data.data===null){       
      setListLopTC([])
      return
   }
        setListLopTC(response.data.data.listLTC);
        setPaginate({"totalPages": response.data.data.totalPages,
                    "currentPage": response.data.data.currentPage,
                    "perPage": response.data.data.perPage})
  }


  const handleSearch = async () => {
    try {

      const response = await SearchByKeyWordLTC(searchTerm,{"perPage": 10,
                                                        "currentPage": 1});
        if(response.data.data===null){       
            setListLopTC([])
            return
        }
              setListLopTC(response.data.data.listLTC);
              setPaginate({"totalPages": response.data.data.totalPages,
                          "currentPage": response.data.data.currentPage,
                          "perPage": response.data.data.perPage})
    
  
    } catch (error) {
      console.error('Lỗi khi gọi API tìm kiếm:', error);
    }
  };

  const handleSearchByMon = async (maMon) => {
    try {
      const response = await GetAllLTCByMon(maMon,{"perPage": 10,
                                                    "currentPage": 1});
        if(response.data.data===null){       
            setListLopTC([])
            return
        }
        setListLopTC(response.data.data.listLTC);
        setPaginate({"totalPages": response.data.data.totalPages,
                      "currentPage": response.data.data.currentPage,
                      "perPage": response.data.data.perPage})
  
    } catch (error) {
      console.error('Lỗi khi gọi API tìm kiếm:', error);
    }
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
     <ModalLopTinChi
          isOpenn = {isOpenMadalLTC}
          togleFromParent = {togleLTCModal}
          listLtc={handleLTCChange}
          searchTerm={searchTerm}
          paginate={paginate}
          maMon={maMon}
          onValueChange={handleChildValueChange}
      />
      {isOpenEditModalLTC && 
      
      <ModalEditLopTinChi
          isOpenn = {isOpenEditModalLTC}
          togleFromParent = {togleLTCEditModal}
          currentLTC= {isLTCEdit}
          listLtc={handleLTCChange}
          searchTerm={searchTerm}
          paginate={paginate}
          maMon={maMon}
          onValueChange={handleChildValueChange}
      /> 
      }

      <h3 className="title text-center mt-3 bold">Quản lí lớp tín chỉ</h3>

      <div className="search-container" style={{"marginTop":"40px"}}>
      <input 
        type="text"
        value={searchTerm}
        onChange={handleChange}
        placeholder="Nhập từ khóa tìm kiếm"
      />
      <button onClick={handleSearch}>Tìm kiếm theo mã lớp tín chỉ</button>

      <button 
        className="btn btn-primary px-3 "
        onClick={handleAddNewLTC}
     >
       <AiOutlinePlus/>
        Thêm mới lớp tín chỉ
     </button>
    </div>

    <div className="search-container" style={{"marginTop":"40px"}}>
    <label>Chọn Mã môn</label>
            <select
              name="maMon"             
              onChange={(e) => {setMaMon(e.target.value)}}
            >
              <option  >--Chọn môn học-</option>
              {listMon.map(item => (
                <option key={item.id} value={item.maMon}>
                  {item.tenMon} {item.maMon}
                </option>))}
            </select>

            <button onClick={() => handleSearchByMon(maMon)}>Tìm kiếm</button>
    </div>
   
      <div className="users-table mt-3">
       {listLopTC.length>0 && (
        <Table striped bordered hover >
          <thead>
            <tr>
                <th>STT</th>
              <th>Mã lớp tín chỉ</th>
              <th>Mã môn</th>
              <th>Học kì</th>
              <th>Năm</th>
              <th>Trạng thái</th>
              <th>Số sinh viên tối thiểu</th>
              <th>Số sinh viên tối đa</th>
              <th>Số lượng</th>
              
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
           
              {listLopTC.map((item, index) => {
                return(
                  <tr key={index}>
                 <>
                
                  <td>{index+1}</td>
                  <td>{item.maLopTinChi}</td>
                  <td>{item.maMon}</td>
                  <td>{item.hocKi}</td>
                  <td>{item.nam}</td>
                  <td>{item.trangThai ? "Mở" : "Đóng"}</td>
                  <td>{item.soSVToiThieu}</td>
                  <td>{item.soSVToiDa}</td>
                  <td>{item.slDangKi}</td>
                  <td>
                    <button className={cx("btn-edit")} onClick={() => handleEditLTC(item)}><AiFillEdit/></button>
                    <Link to={`/student/${item.maLopTinChi}`}>
                    <button className={cx("btn-edit")}><AiOutlineTeam/></button>
                    </Link>
                    <Link to={`/nhap-diem/${item.maLopTinChi}`}>
                    <button className={cx("btn-edit")}><AiOutlineSolution/></button>
                    </Link>
                    {/* <button className={cx("btn-delete")} onClick={() =>handleDeleteUser(item)}><AiFillDelete /></button> */}
                    
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

      {listLopTC.map((item) => (
        <div key={item.id}>{item.name}</div>
      ))}
   
    </div>
  );
};

export { TimKiemSVTheoLopTinChi };
