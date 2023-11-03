import { useEffect, useState } from "react";
import { BrowserRouter as Router, Link } from 'react-router-dom';
import { SearchByKeyWordMon} from "../../../../service/UserService";
import Table from "react-bootstrap/Table";
import {AiFillEdit,AiOutlinePlus} from 'react-icons/ai'
import classNames from "classnames/bind";
import { ModalMon } from "./ModalMon";
import { ModalEditMon } from "./ModelEditMon";
import '../../../Search/SearchComponent.scss';
import styles from '../../../../container/UserManage.module.scss'
import { Pagination } from 'antd';
import { AlertCustom} from "../../../Alert/Alert";

const cx = classNames.bind(styles);

const Mon= () => {
const [paginate, setPaginate] = useState({});
const [isOpenMadalMon, setIsOpenMadalMon] = useState(false)
const [isOpenEditModalMon, setIsOpenEditModalMon] = useState(false)
const [isMonEdit, setIsMonEdit] = useState({})
const [listMon, setListMon] = useState([]);
const [searchTerm, setSearchTerm] = useState('');
const [item, setItem] = useState({});
const [alert, setAlert] = useState(false);

  useEffect(() => {
    async function get() {
      getAllMonFromReact();
    }
    get()
  }, []);

  let getAllMonFromReact = async() => {
    const response = await SearchByKeyWordMon("",{"perPage": 2,
                                                "currentPage": 1});
    if(response.data.data===null){       
      setListMon([])
      return
  }
      setListMon(response.data.data.listMon);
      setPaginate({"totalPages": response.data.data.totalPages,
                  "currentPage": response.data.data.currentPage,
                  "perPage": response.data.data.perPage})
  }


  
  let handleAddNewMon = () => {
    setIsOpenMadalMon(true)
  }

  let togleMonModal = () => {
    setIsOpenMadalMon(!isOpenMadalMon)
  }

  let togleMonEditModal = () => {
    setIsOpenEditModalMon(!isOpenEditModalMon)
  }

  const handleChange = (e) => {
    setSearchTerm(e.target.value);
  };


  const handleMonChange = (newlist) => {
    setListMon(newlist);
  };


  const handleEditMon = (mon) => {
    setIsOpenEditModalMon(mon)
    setIsMonEdit(mon)
  }

  const handlePageSizeChange = async (current, pageSize) => {
    console.log(current)
    const response = await SearchByKeyWordMon(searchTerm,{"perPage": pageSize,
                                                        "currentPage": current});
        if(response.data.data===null){       
            setListMon([])
            return
        }
        setListMon(response.data.data.listMon);
        setPaginate({"totalPages": response.data.data.totalPages,
                    "currentPage": response.data.data.currentPage,
                    "perPage": response.data.data.perPage})
 
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


  const handleSearch = async () => {
    try {

      const response = await SearchByKeyWordMon(searchTerm,{"perPage": 2,
                                                        "currentPage": 1});
        if(response.data.data===null){       
            setListMon([])
            return
        }
            setListMon(response.data.data.listMon);
            setPaginate({"totalPages": response.data.data.totalPages,
                        "currentPage": response.data.data.currentPage,
                        "perPage": response.data.data.perPage})
        console.log(paginate)
  
    } catch (error) {
      console.error('Lỗi khi gọi API tìm kiếm:', error);
    }
  };

  return (
    
    <div className="users-container"> 
    {alert && (<AlertCustom className={cx("alert-container")} 
                      item={item}
                      onClose={handleCloseAlert}/>)}

     <ModalMon
          isOpenn = {isOpenMadalMon}
          togleFromParent = {togleMonModal}
          listMon={handleMonChange}
          searchTerm={searchTerm}
          paginate={paginate}
          onValueChange={handleChildValueChange}
      />
      {isOpenEditModalMon && 
      
      <ModalEditMon
          isOpenn = {isOpenEditModalMon}
          togleFromParent = {togleMonEditModal}
          currentMon= {isMonEdit}
          listMon={handleMonChange}
          searchTerm={searchTerm}
          paginate={paginate}
          onValueChange={handleChildValueChange}
      /> 
      }

      <h3 className="title text-center mt-3 bold">Quản lí Môn</h3>

      <div className="search-container">
      <input 
        type="text"
        value={searchTerm}
        onChange={handleChange}
        placeholder="Nhập từ khóa tìm kiếm"
      />
      <button onClick={handleSearch}>Tìm kiếm Môn</button>
      <button 
        className="btn btn-primary px-3 "
        onClick={handleAddNewMon}
     >
       <AiOutlinePlus/>
        Thêm mới Môn
     </button>
    </div>
   
      <div className="users-table mt-3">
       {listMon.length>0 && (
        <Table striped bordered hover >
          <thead>
            <tr>
                <th>STT</th>
              <th>Mã lớp </th>
              <th>Tên lớp </th>
              <th>Số tín chỉ</th>
              <th>Số tiết lí thuyết</th>
              <th>Số tiết thực hành</th>
              <th>Hệ số 1</th>
              <th>Hệ số 2</th>
              <th>Hệ số 3</th>
              <th>Hệ số 4</th>
              <th>Hệ số 5</th>
              
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
           
              {listMon.map((item, index) => {
                return(
                  <tr key={index}>
                 <>
                
                  <td>{index+1}</td>
                  <td>{item.maMon}</td>
                  <td>{item.tenMon}</td>
                  <td>{item.soTinChi}</td>
                  <td>{item.soTietLiThuyet}</td>
                  <td>{item.soTietThucHanh}</td>
                  <td>{item.heSo1}</td>
                  <td>{item.heSo2}</td>
                  <td>{item.heSo3}</td>
                  <td>{item.heSo4}</td>
                  <td>{item.heSo5}</td>
                  
                  <td>
                    <button className={cx("btn-edit")} onClick={() => handleEditMon(item)}><AiFillEdit/></button>
                    {/* <Link to={`/student/mon/${item.maMon}`}>
                    <button className={cx("btn-edit")}><AiFillBook/></button>
                    </Link> */}
                    {/* <button className={cx("btn-delete")} onClick={() =>handleDeleteUser(item)}><AiFillDelete /></button> */}
                    
                  </td>
                 </>
            </tr>
                )
              })}
              
          </tbody>
        
          <Pagination current={paginate.currentPage}  total={paginate.totalPages*10}
          onChange={(current,size) => handlePageSizeChange(current,2)}/>
        </Table>)}
       
      </div>
   
    </div>
  );
};

export { Mon };
