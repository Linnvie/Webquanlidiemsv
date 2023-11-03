import { useEffect, useState } from "react";
import { BrowserRouter as Router, Link } from 'react-router-dom';
import { SearchByKeyWordGiangVien, FilterGiangVien} from "../../../../service/UserService";
import Table from "react-bootstrap/Table";
import {AiFillEdit,AiOutlinePlus} from 'react-icons/ai'
import classNames from "classnames/bind";
import { ModalGiangVien} from "./ModalGiangVien";
import { ModalEditGiangVien} from "./ModalEditGiangVien";
import '../../../Search/SearchComponent.scss';
import styles from '../../../../container/UserManage.module.scss'
import { AlertCustom} from "../../../Alert/Alert";
import { Pagination } from 'antd';

const cx = classNames.bind(styles);

const ManageGiangVien= () => {
const [paginate, setPaginate] = useState({});
const [isOpenMadalGV, setIsOpenMadalGV] = useState(false)
const [isOpenEditModalGV, setIsOpenEditModalGV] = useState(false)
const [isGVEdit, setIsGVEdit] = useState({})
const [listGV, setListGV] = useState([]);
const [searchTerm, setSearchTerm] = useState('');
const [selectedOption, setSelectedOption] = useState('');

const [item, setItem] = useState({});
const [alert, setAlert] = useState(false);

useEffect(() => {
  async function get() {
    getAllGVFromReact();
  }

}, []);

  useEffect(() => {
    async function get() {
      // getAllGVFromReact();
      console.log("selectedOption: "+selectedOption)
      let response;
      if(selectedOption === ""){     
        response = await SearchByKeyWordGiangVien("",{"perPage": 10,"currentPage": 1});   
      }else{      
        response = await FilterGiangVien(selectedOption,{"perPage": 10,"currentPage": 1});
      }
      console.log(response)
      if(response.data.data===null){       
        setListGV([])
        return
      }
        setListGV(response.data.data.listGV);
        setPaginate({"totalPages": response.data.data.totalPages,
                    "currentPage": response.data.data.currentPage,
                    "perPage": response.data.data.perPage})

    }
    get()
  }, [selectedOption]);

 
  let getAllGVFromReact = async() => {
    const response = await SearchByKeyWordGiangVien("",{"perPage": 10,"currentPage": 1});
    if(response.data.data===null){       
      setListGV([])
      return
    }
      setListGV(response.data.data.listGV);
      setPaginate({"totalPages": response.data.data.totalPages,
                  "currentPage": response.data.data.currentPage,
                  "perPage": response.data.data.perPage})
  }


  
  let handleAddNewGV = () => {
    setIsOpenMadalGV(true)
  }

  let togleGVModal = () => {
    setIsOpenMadalGV(!isOpenMadalGV)
  }

  let togleGVEditModal = () => {
    setIsOpenEditModalGV(!isOpenEditModalGV)
  }

  const handleChange = (e) => {
    setSearchTerm(e.target.value);
  };


  const handleGVChange = (newlist) => {
    setListGV(newlist);
  };


  const handleEditGV = (gv) => {
    setIsOpenEditModalGV(gv)
    setIsGVEdit(gv)
  }

  const handlePageSizeChange = async (current, pageSize) => {
    const response = await SearchByKeyWordGiangVien(searchTerm,{"perPage": pageSize,
                                                        "currentPage": current});
        if(response.data.data===null){       
            setListGV([])
            return
        }
        setListGV(response.data.data.listGV);
        setPaginate({"totalPages": response.data.data.totalPages,
                    "currentPage": response.data.data.currentPage,
                    "perPage": response.data.data.perPage})
 
  };


  const handleSearch = async () => {
    try {

      const response = await SearchByKeyWordGiangVien(searchTerm,{"perPage": 10,
                                                        "currentPage": 1});
        if(response.data.data===null){       
            setListGV([])
            return
        }
            setListGV(response.data.data.listGV);
            setPaginate({"totalPages": response.data.data.totalPages,
                        "currentPage": response.data.data.currentPage,
                        "perPage": response.data.data.perPage})
  
    } catch (error) {
      console.error('Lỗi khi gọi API tìm kiếm:', error);
    }
  };
  
    let handleFilter = async (event) => {

      event.preventDefault();
      console.log(event.target)

      const selectedValue = event.target.value;
      console.log("selectedValue: "+selectedValue);
      setSelectedOption(selectedValue);

    }

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
     <ModalGiangVien
          isOpenn = {isOpenMadalGV}
          togleFromParent = {togleGVModal}
          listGV={handleGVChange}
          searchTerm={searchTerm}
          paginate={paginate}
          onValueChange={handleChildValueChange}
      />
      {isOpenEditModalGV && 
      
      <ModalEditGiangVien
          isOpenn = {isOpenEditModalGV}
          togleFromParent = {togleGVEditModal}
          currentGV= {isGVEdit}
          listGV={handleGVChange}
          searchTerm={searchTerm}
          paginate={paginate}
          onValueChange={handleChildValueChange}
      /> 
      }

      <h3 className="title text-center mt-3 bold">Quản lí Giảng viên</h3>

      <div className="search-container">
      <input 
        type="text"
        value={searchTerm}
        onChange={handleChange}
        placeholder="Nhập từ khóa tìm kiếm"
      />
      <button onClick={handleSearch}>Tìm kiếm Giảng viên</button>
      <button 
        className="btn btn-primary px-3 "
        onClick={handleAddNewGV}
     >
       <AiOutlinePlus/>
        Thêm mới Giảng viên
     </button>

     <select value={selectedOption} onChange={(e) => {handleFilter(e)}}>
        <option value="">Tất cả</option>
        <option value="true">Đang dạy</option>
        <option value="false">Đã nghỉ</option>
      </select>
    </div>
    
   
      <div className="users-table mt-3">
       {listGV.length>0 && (
        <Table striped bordered hover >
          <thead>
            <tr>
              <th>STT</th>
              <th>Mã giảng viên</th>
              <th>Họ lót</th>
              <th>Tên</th>
              <th>Giới Tính</th>
              <th>Ngày Sinh</th>
              <th>Mã khoa</th>
              <th>Học hàm</th>
              <th>Học vị</th>
              
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
           
              {listGV.map((item, index) => {
                return(
                  <tr key={index}>
                 <>
                
                 <td>{index+1}</td>
                  <td>{item.maGiangVien}</td>
                  <td>{item.hoLot}</td>
                  <td>{item.ten}</td>
                  <td>{item.gioiTinh === false ? "Nữ" : "Nam"}</td>
                  <td>{item.ngaySinh}</td>
                  <td>{item.maKhoa}</td>
                  <td>{item.hocHam}</td>
                  <td>{item.hocVi}</td>
                  
                  <td>
                    <button className={cx("btn-edit")} onClick={() => handleEditGV(item)}><AiFillEdit/></button>
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
          onChange={(current, size) => handlePageSizeChange(current, size)}/>
        </Table>)}
       
      </div>
   
    </div>
  );
};

export {ManageGiangVien};
