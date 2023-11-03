import Card from "react-bootstrap/Card";
import styles from "../BangDiem/BangDiem.module.scss";
import classNames from "classnames/bind";
import {BangDiemItem} from "../BangDiem/BangDiemItem";
import { GetSinhVienByMSSV, GetDiemService } from "../../service/UserService";
import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { selectUser } from "../../redux/useSlices";
import { AlertCustom} from "../Alert/Alert";
import {ModalChangePassword} from "../pages/Login/ChangePassword";
import {AiOutlineEdit} from 'react-icons/ai'
const cx = classNames.bind(styles);



function BangDiem() { 
  const user = useSelector(selectUser);
  const [diem, setDiem] = useState([]);
  const [sinhVien, setSinhVien] = useState({});
  const [isOpenMadalPass, setIsOpenMadalPass] = useState(false)
  const [item, setItem] = useState({});
  const [alert, setAlert] = useState(false);
  

  useEffect(() => {
    async function get() { 
      await getSinhVien();   
      await getDiem();
    }
    get();
  }, []);
     
  let getSinhVien = async() => {
    let response = await GetSinhVienByMSSV(user.name);
      if (response.data.code==200  && response.data.data) {   
        setSinhVien(response.data.data)
        
        };
      }

  let getDiem = async() => {
    console.log("vào")
    let response = await GetDiemService(user.name);
    console.log("res"+response)
      if (response.data.code==200  && response.data.data) {     
        setDiem(response.data.data);      
      }
    }

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

    let handleChangePass  = async() => {
      setIsOpenMadalPass(true) 
      };
  
      let toglePassModal = () => {
          setIsOpenMadalPass(!isOpenMadalPass)
        }

  
  return (
    <>

{alert && (<AlertCustom className={cx("alert-container")} 
                   item={item}
                  onClose={handleCloseAlert}/>)}


    <ModalChangePassword
          isOpenn = {isOpenMadalPass}
          togleFromParent = {toglePassModal}
          currentUser={user}
          item={item}
          onValueChange={handleChildValueChange}
      />

<button 
        className="btn btn-primary px-3 "
        onClick={handleChangePass}
     >
       <AiOutlineEdit/>
        Đổi mật khẩu
     </button>

    <div style={{display:"flex"}}>
      <div className={cx("wrapper-bangdiem")}>
        <Card style={{ width: "30rem" }}>
        <Card.Header><div className={cx("desc-bangdiem")}>
            <p></p>
              <Card.Text>Thông tin sinh viên</Card.Text>
              <p></p>
            </div></Card.Header>
          <Card.Body>
            {/* <Card.Title>Card Title</Card.Title> */}
            <div className={cx("desc-bangdiem")}>
              <Card.Text>Mã số sinh viên: {sinhVien.mssv}</Card.Text>
              <p></p>
            </div>
            <div className={cx("desc-bangdiem")}>
              <Card.Text>Họ và tên: {sinhVien.hoLot} {sinhVien.ten}</Card.Text>
              <p></p>
            </div>
            <div className={cx("desc-bangdiem")}>
              <Card.Text>Ngày sinh: {sinhVien.ngaySinh}</Card.Text>
              <p></p>
            </div>
            <div className={cx("desc-bangdiem")}>
              <Card.Text>Giới tính : {sinhVien.gioiTinh ? "Nữ" : "Nam"}</Card.Text>
              <p></p>
            </div>
            
            <div className={cx("desc-bangdiem")}>
              <Card.Text>Email: {sinhVien.mssv}@student.ptithcm.edu.vn</Card.Text>
              <p></p>
            </div>
            <div className={cx("desc-bangdiem")}>
              <Card.Text>Địa chỉ: {sinhVien.diaChi}</Card.Text>
              <p></p>
            </div>
            <div className={cx("desc-bangdiem")}>
              <Card.Text>Hiện diện:  {renderContent(sinhVien.status)}</Card.Text>
              <p></p>
            </div>
          </Card.Body>
        </Card>
      </div>

      <div className={cx("wrapper-bangdiem")}>
        <Card style={{ width: "25rem" }}>
          <Card.Header ><div className={cx("desc-bangdiem")} >
            <p></p>
              <Card.Text>Thông tin khóa học</Card.Text>
              <p></p>
            </div></Card.Header>
          <Card.Body>
            {/* <Card.Title>Card Title</Card.Title> */}
            <div className={cx("desc-bangdiem")}>
              <Card.Text>Lớp : {sinhVien.maLop}</Card.Text>
              <p></p>
            </div>
            <div className={cx("desc-bangdiem")}>
              <Card.Text>Ngành: {sinhVien.maNganh}</Card.Text>
              <p></p>
            </div>
            <div className={cx("desc-bangdiem")}>
              <Card.Text>Chuyên ngành:</Card.Text>
              <p></p>
            </div>
            <div className={cx("desc-bangdiem")}>
              <Card.Text>Khoa: {sinhVien.maKhoa}</Card.Text>
              <p></p>
            </div>
            <div className={cx("desc-bangdiem")}>
              <Card.Text>Hệ học: {sinhVien.heHoc}</Card.Text>
              <p></p>
            </div>
            <div className={cx("desc-bangdiem")}>
              <Card.Text>Niên khóa: {sinhVien.nienKhoa}</Card.Text>
              <p></p>
            </div>
          </Card.Body>
        </Card>
      </div>
      </div>
    

      {diem.map((item, index) => (
            <BangDiemItem item={item} />
          ))}
        
     
    </>
  );
}

export default BangDiem;
