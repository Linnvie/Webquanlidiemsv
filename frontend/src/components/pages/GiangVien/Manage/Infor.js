import Card from "react-bootstrap/Card";
import styles from "../../../BangDiem/BangDiem.module.scss";
import classNames from "classnames/bind";
import { GetGiangVienByMaGV} from "../../../../service/UserService";
import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { selectUser } from "../../../../redux/useSlices";
import {ModalChangePassword} from "../../Login/ChangePassword";
import { AlertCustom} from "../../../Alert/Alert";
import {AiOutlineEdit} from 'react-icons/ai'
const cx = classNames.bind(styles);



function Infor() { 
  const user = useSelector(selectUser);
  const [giangVien, setGiangVien] = useState({});
  const [isOpenMadalPass, setIsOpenMadalPass] = useState(false)

  const [item, setItem] = useState({});
  const [alert, setAlert] = useState(false);
  

  useEffect(() => {
    async function get() { 
      await getGiangVien();
    }
    get();
  }, []);

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
     
  let getGiangVien = async() => {
    const response = await GetGiangVienByMaGV(user.name);
      if (response.data.code==200  && response.data.data) {  
        setGiangVien(response.data.data)
   
        };
      }
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
 <div  style={{display:"flex", justifyItems:"center" , alignItems:"center"}}>
    
      <div className={cx("wrapper-bangdiem") }   style={{ flex:"1"}} >
        <Card style={{ width: "30rem" }}>
        <Card.Header><div className={cx("desc-bangdiem")}>
            <p></p>
              <Card.Text>Thông tin giảng viên</Card.Text>
              <p></p>
            </div></Card.Header>
          <Card.Body>
            {/* <Card.Title>Card Title</Card.Title> */}
            <div className={cx("desc-bangdiem")}>
              <Card.Text>Mã giảng viên: {giangVien.maGiangVien}</Card.Text>
              <p></p>
            </div>
            <div className={cx("desc-bangdiem")}>
              <Card.Text>Họ và tên: {giangVien.hoLot} {giangVien.ten}</Card.Text>
              <p></p>
            </div>
            <div className={cx("desc-bangdiem")}>
              <Card.Text>Ngày sinh: {giangVien.ngaySinh}</Card.Text>
              <p></p>
            </div>
            <div className={cx("desc-bangdiem")}>
              <Card.Text>Giới tính : {giangVien.gioiTinh ? "Nữ" : "Nam"}</Card.Text>
              <p></p>
            </div>
            
            <div className={cx("desc-bangdiem")}>
              <Card.Text>Email: {giangVien.maGiangVien}@teacher.ptithcm.edu.vn</Card.Text>
              <p></p>
            </div>
            <div className={cx("desc-bangdiem")}>
              <Card.Text>Địa chỉ: {giangVien.diaChi}</Card.Text>
              <p></p>
            </div>
            <div className={cx("desc-bangdiem")}>
              <Card.Text>Số tài khoản: {giangVien.soTaiKhoan}</Card.Text>
              <p></p>
            </div>
            <div className={cx("desc-bangdiem")}>
              <Card.Text>Khoa: {giangVien.khoa}</Card.Text>
              <p></p>
            </div>
            <div className={cx("desc-bangdiem")}>
              <Card.Text>Học hàm: {giangVien.hocHam}</Card.Text>
              <p></p>
            </div>
            <div className={cx("desc-bangdiem")}>
              <Card.Text>Học vị: {giangVien.hocVi}</Card.Text>
              <p></p>
            </div>
          </Card.Body>
        </Card>
      </div>

 
        
     </div>
    </>
  );
}

export default Infor;
