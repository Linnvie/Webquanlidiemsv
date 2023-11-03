import { useEffect, useState } from "react";
import { BrowserRouter as Router, Link } from 'react-router-dom';
import {GetLTCByMaGVHocKiNam} from "../../../../service/UserService";
import Table from "react-bootstrap/Table";
import {AiFillEdit, AiFillDelete,AiOutlinePlus, AiFillRedditCircle, AiFillBook, AiFillAccountBook} from 'react-icons/ai'
import classNames from "classnames/bind";
import '../../../Search/SearchComponent.scss';
import styles from '../../../../container/UserManage.module.scss'
import { selectUser } from "../../../../redux/useSlices";
import { useSelector } from "react-redux";

const cx = classNames.bind(styles);

const ListLopTinChi= () => {
const [paginate, setPaginate] = useState({});
const [searchTerm, setSearchTerm] = useState('');
const [listLTC, setListLTC] = useState([]);

const user = useSelector(selectUser);

  useEffect(() => {
    async function get() {
      getAllLTCFromReact(); 
    }
    get()
  }, []);


  let getAllLTCFromReact = async() => {
    const today = new Date();
    const currentYear = today.getFullYear();
    const response = await GetLTCByMaGVHocKiNam(user.name,1,currentYear);
    if(response.data.data===null){       
      setListLTC([])
      return
    }
      setListLTC(response.data.data);

  }



  return (
    
    <div className="users-container"> 
  
      <h3 className="title text-center mt-3 bold">Quản lí lớp tín chỉ</h3>
   
      <div className="users-table mt-3">
       {listLTC.length>0 && (
        <Table striped bordered hover >
          <thead>
            <tr>
            <th>STT</th>
              <th>Mã lớp tín chỉ</th>
              <th>Mã môn</th>
              <th>Học kì</th>
              <th>Năm</th>
              <th>Trạng thái</th>
              <th>Đóng</th>
              <th>Hệ số 1</th>
              <th>Hệ số 2</th>
              <th>Hệ số 3</th>
              <th>Hệ số 4</th>
              <th>Hệ số 5</th>
              
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
           
              {listLTC.map((item, index) => {
                return(
                  <tr key={index}>
                 <>
                
                 <td>{index+1}</td>
                  <td>{item.maLTC}</td>
                  <td>{item.maMon}</td>
                  <td>{item.hocKi}</td>
                  <td>{item.nam}</td>
                  <td>{item.trangThai ? "Mở" : "Đóng"}</td>
                  <td>{item.dong}</td>
                  <td>{item.heSo1}</td>
                  <td>{item.heSo2}</td>
                  <td>{item.heSo3}</td>
                  <td>{item.heSo4}</td>
                  <td>{item.heSo5}</td>
                  <td>
                    <Link to={`/dssv/${item.maLTC}`}>
                    <button className={cx("btn-edit")}><AiFillAccountBook/></button>
                    </Link>
                   
                    
                  </td>
                 </>
            </tr>
                )
              })}
              
          </tbody>
        
        </Table>)}
       
      </div>
   
    </div>
  );
};

export {ListLopTinChi};
