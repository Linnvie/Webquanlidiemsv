import Table from "react-bootstrap/Table";
import { GetAllStudentLTC  } from "../../../../service/UserService";
import { useEffect, useState } from "react";
import {AiFillEdit, AiFillDelete,AiOutlinePlus} from 'react-icons/ai'
import styles from '../../../../container/UserManage.module.scss'
import classNames from "classnames/bind";
import { useParams } from 'react-router-dom';
import { Pagination } from 'antd'; 

const cx = classNames.bind(styles);

const ListStudent = () => {
  const [paginate, setPaginate] = useState({});
  const [listSV, setListSV] = useState([]);

  useEffect(() => {
    async function get() {   
      getAllSvFromReact();    
    }
    get()
  }, []);

 
  const { maLTC } = useParams();

  let getAllSvFromReact = async() => {
    const response = await GetAllStudentLTC(maLTC,{"perPage": 10,
                                                "currentPage": 1});
    if(response.data.data===null){       
      setListSV([])
      return
  }
      setListSV(response.data.data.listSV);
      setPaginate({"totalPages": response.data.data.totalPages,
                  "currentPage": response.data.data.currentPage})
  console.log(paginate)
  }


  const handlePageSizeChange = async (current, pageSize) => {
 
    const response = await GetAllStudentLTC(maLTC,{"perPage": pageSize,
                                                    "currentPage": current});
        if(response.data.data===null){       
          setListSV([])
            return
        }
        setListSV(response.data.data.listSV);
        setPaginate({"totalPages": response.data.data.totalPages,
                    "currentPage": response.data.data.currentPage})
  };

  return (
    <div className="users-container">
     
      <h3 className="title text-center mt-3 bold">Danh sách sinh viên lớp tín chỉ {maLTC}</h3>
      <div className="search-container">
    </div>
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

export { ListStudent };
