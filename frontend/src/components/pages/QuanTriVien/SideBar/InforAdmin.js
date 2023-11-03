import Card from "react-bootstrap/Card";
import styles from "../../../BangDiem/BangDiem.module.scss";
import classNames from "classnames/bind";
import { GetQTVByMaQTV} from "../../../../service/UserService";
import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { selectUser } from "../../../../redux/useSlices";
import {ModalChangePassword} from "../../Login/ChangePassword";
import { AlertCustom} from "../../../Alert/Alert";
import {AiOutlineEdit} from 'react-icons/ai'
const cx = classNames.bind(styles);



function InforAdmin() { 
  const user = useSelector(selectUser);
  const [quantrivien, setQuanTriVien] = useState({});
  const [isOpenMadalPass, setIsOpenMadalPass] = useState(false)

  const [item, setItem] = useState({});
  const [alert, setAlert] = useState(false);
  

  useEffect(() => {
    async function get() { 
      await getQuanTriVien();
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
     
  let getQuanTriVien = async() => {
    let response = await GetQTVByMaQTV(user.name);
      if (response.data.code==200  && response.data.data) {   
        setQuanTriVien(response.data.data)
        
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
              <Card.Text>Thông tin quản trị viên</Card.Text>
              <p></p>
            </div></Card.Header>
          <Card.Body>
            {/* <Card.Title>Card Title</Card.Title> */}
            <div className={cx("desc-bangdiem")}>
              <Card.Text>Mã quản trị viên: {quantrivien.maQTV}</Card.Text>
              <p></p>
            </div>
            <div className={cx("desc-bangdiem")}>
              <Card.Text>Họ và tên: {quantrivien.hoLot} {quantrivien.ten}</Card.Text>
              <p></p>
            </div>
            <div className={cx("desc-bangdiem")}>
              <Card.Text>Ngày sinh: {quantrivien.ngaySinh}</Card.Text>
              <p></p>
            </div>
            <div className={cx("desc-bangdiem")}>
              <Card.Text>Giới tính : {quantrivien.gioiTinh ? "Nữ" : "Nam"}</Card.Text>
              <p></p>
            </div>
            
            <div className={cx("desc-bangdiem")}>
              <Card.Text>Email: {quantrivien.maQTV}@teacher.ptithcm.edu.vn</Card.Text>
              <p></p>
            </div>
            <div className={cx("desc-bangdiem")}>
              <Card.Text>Địa chỉ: {quantrivien.diaChi}</Card.Text>
              <p></p>
            </div>
            <div className={cx("desc-bangdiem")}>
              <Card.Text>Số tài khoản: {quantrivien.soTaiKhoan}</Card.Text>
              <p></p>
            </div>
            <div className={cx("desc-bangdiem")}>
              <Card.Text>Khoa: {quantrivien.chucVu}</Card.Text>
              <p></p>
            </div>
           
          </Card.Body>
        </Card>
      </div>

 
        
     </div>
    </>
  );
}

export default InforAdmin;
