import { useEffect, useState } from "react";
import { BrowserRouter as Router, Link } from 'react-router-dom';
import { SearchByKeyWordLop} from "../../../../service/UserService";
import Table from "react-bootstrap/Table";
import {AiFillEdit,AiOutlinePlus,AiOutlineTeam} from 'react-icons/ai'
import classNames from "classnames/bind";
import { ModalLop } from "./ModalLop";
import { ModalEditLop } from "./ModelEditLop";
import '../../../Search/SearchComponent.scss';
import styles from '../../../../container/UserManage.module.scss'
import { Pagination } from 'antd';
import { AlertCustom} from "../../../Alert/Alert";

const cx = classNames.bind(styles);

const TimKiemSVTheoLop= () => {
const [paginate, setPaginate] = useState({});
const [isOpenMadalLop, setIsOpenMadalLop] = useState(false)
const [isOpenEditModalLop, setIsOpenEditModalLop] = useState(false)
const [isLopEdit, setIsLopEdit] = useState({})
const [listLop, setListLop] = useState([]);
const [searchTerm, setSearchTerm] = useState('');

const [item, setItem] = useState({});
const [alert, setAlert] = useState(false);

  useEffect(() => {
    async function get() {
      getAllLopFromReact();
    }
    get()
  }, []);

  let getAllLopFromReact = async() => {
    const response = await SearchByKeyWordLop("",{"perPage": 10,
                                                "currentPage": 1});
    if(response.data.data===null){       
      setListLop([])
      return
  }
      setListLop(response.data.data.listLop);
      setPaginate({"totalPages": response.data.data.totalPages,
                  "currentPage": response.data.data.currentPage,
                  "perPage": response.data.data.perPage})
  }


  
  let handleAddNewLop = () => {
    setIsOpenMadalLop(true)
  }

  let togleLopModal = () => {
    setIsOpenMadalLop(!isOpenMadalLop)
  }

  let togleLopEditModal = () => {
    setIsOpenEditModalLop(!isOpenEditModalLop)
  }

  const handleChange = (e) => {
    setSearchTerm(e.target.value);
  };


  const handleLopChange = (newlist) => {
    setListLop(newlist);
  };


  const handleEditLop = (lop) => {
    setIsOpenEditModalLop(lop)
    setIsLopEdit(lop)
  }


  const handlePageSizeChange = async (current, pageSize) => {
    const response = await SearchByKeyWordLop(searchTerm,{"perPage": pageSize,
                                                        "currentPage": current});
        if(response.data.data===null){       
            setListLop([])
            return
        }
        setListLop(response.data.data.listLop);
        setPaginate({"totalPages": response.data.data.totalPages,
                    "currentPage": response.data.data.currentPage,
                    "perPage": response.data.data.perPage})
 
  };


  const handleSearch = async () => {
    try {

      const response = await SearchByKeyWordLop(searchTerm,{"perPage": 10,
                                                        "currentPage": 1});
        if(response.data.data===null){       
            setListLop([])
            return
        }
            setListLop(response.data.data.listLop);
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
     <ModalLop
          isOpenn = {isOpenMadalLop}
          togleFromParent = {togleLopModal}
          listLop={handleLopChange}
          searchTerm={searchTerm}
          paginate={paginate}
          onValueChange={handleChildValueChange}
      />
      {isOpenEditModalLop && 
      
      <ModalEditLop
          isOpenn = {isOpenEditModalLop}
          togleFromParent = {togleLopEditModal}
          currentLop= {isLopEdit}
          listLop={handleLopChange}
          searchTerm={searchTerm}
          paginate={paginate}
          onValueChange={handleChildValueChange}
      /> 
      }

      <h3 className="title text-center mt-3 bold">Quản lí lớp</h3>

      <div className="search-container">
      <input 
        type="text"
        value={searchTerm}
        onChange={handleChange}
        placeholder="Nhập từ khóa tìm kiếm"
      />
      <button onClick={handleSearch}>Tìm kiếm lớp</button>
      <button 
        className="btn btn-primary px-3 "
        onClick={handleAddNewLop}
     >
       <AiOutlinePlus/>
        Thêm mới lớp 
     </button>
    </div>
   
      <div className="users-table mt-3">
       {listLop.length>0 && (
        <Table striped bordered hover >
          <thead>
            <tr>
                <th>STT</th>
              <th>Mã lớp </th>
              <th>Tên lớp </th>
              <th>Mã khoa</th>
              <th>Mã ngành</th>
              <th>Niên khóa</th>
              
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
           
              {listLop.map((item, index) => {
                return(
                  <tr key={index}>
                 <>
                
                  <td>{index+1}</td>
                  <td>{item.maLop}</td>
                  <td>{item.tenLop}</td>
                  <td>{item.maKhoa}</td>
                  <td>{item.maNganh}</td>
                  <td>{item.nienKhoa}</td>
                  
                  <td>
                    <button className={cx("btn-edit")} onClick={() => handleEditLop(item)}><AiFillEdit/></button>
                    <Link to={`/student/lop/${item.maLop}`}>
                    <button className={cx("btn-edit")}><AiOutlineTeam/></button>
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
   
    </div>
  );
};

export { TimKiemSVTheoLop };
