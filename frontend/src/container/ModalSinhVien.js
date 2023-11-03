import React, { useEffect, useState } from "react";
import { Button, Modal, ModalHeader, ModalBody, ModalFooter } from "reactstrap";
import styles from "../../../fontend/src/container/UserManage.module.scss";
import classNames from "classnames/bind";
import {SinhVienDto} from "../dto/SinhVienDto"
import {createNewSVService,GetAllStudentLop, GetAllLop, GetAllNganh} from "../service/UserService";
const ModalSinhVien = (props) => {
  const cx = classNames.bind(styles);

  const [modal, setModal] = useState(false);
  const [startYear, setStartYear] = useState('');
  const [endYear, setEndYear] = useState('');

  const [listNganh, setListNganh] = useState([]);
  const [listLop, setListLop] = useState([]);

  const [valueForm, setValueForm] = useState(SinhVienDto);

  useEffect(() => {
    async function get() {
      getAllNganhFromReact(); 
      getAllLopFromReact(); 
    }
    get()
  }, []);

  let getAllNganhFromReact = async() => {
    const response = await GetAllNganh();
    if(response.data.data===null){       
      setListNganh([])
      return
    }
      setListNganh(response.data.data);

  }

  let getAllLopFromReact = async() => {
    const response = await GetAllLop();
    if(response.data.data===null){       
      setListLop([])
      return
    }
      setListLop(response.data.data);

  }

  const toggle = () => setModal(props.togleFromParent);

  const handleOnChangInputNienKhoa = (e) => {
    const { name, value } = e.target;

    // Lưu giá trị của ô input vào state tương ứng
    if (name === 'startYear') {
      setStartYear(value);
    } else if (name === 'endYear') {
      setEndYear(value);
    }
    setValueForm({
      ... valueForm,
       "nienKhoa": `${startYear}-${endYear}`
   });
    
  };

  // Kết hợp hai giá trị thành trường nienKhoa
 

  let handleOnChangInput = (e) => {
    setValueForm({
      ...valueForm,
      [e.target.name]: e.target.value.trim(),
    });
  };

  let checkValidateInput = () => {
    let arrayInput = [
   //   "mssv",
   "hoLot",
   "ngaySinh",
    "ten",
  //  "gioiTinh",
   "maNganh",
   "maKhoa",
   "maLop",
  "heHoc"
    ];
    let isValid = true
    for(let i = 0; i< arrayInput.length; i++) {
   
      if(!valueForm[arrayInput[i]]){
        isValid = false
        console.log(i)
        props.onValueChange({"message":"  Vui lòng điền đầy đủ thông tin các mục bắt buộc (*)",
        "type":"error",
      "title":"Lỗi"});
        break
      }
    }
    return isValid
  };

  let handleAddNewSV =async () => {
    let isValid = checkValidateInput()
    
    console.log(valueForm)
    if(isValid === true) {
    
     try {
       await createNewSVService(valueForm);
        
       const response = await GetAllStudentLop(props.maLop,props.paginate);
       setModal(props.togleFromParent);
       props.onValueChange({"message":response.data.message,
                          "type":"success",
                          "title":"Thành công"});

         props.listSV(response.data.data.listSV);
         props.paginate({"totalPages": response.data.data.totalPages,
         "currentPage": response.data.data.currentPage,
         "perPage": response.data.data.perPage})
        }catch (e) {
        props.onValueChange({"message":e.response.data.message,
                            "type":"error",
                          "title":"Lỗi"});
     }

  }};

  return (
    <Modal
      isOpen={props.isOpenn}
      toggle={toggle}
      size="lg"
      centered
      className={cx("modal-user-container")}
    >
      <ModalHeader toggle={toggle}>Thêm sinh viên</ModalHeader>
      <ModalBody>
        <div className={cx("modal-user-body")}>
          <div className={cx("input-container")}>
            <label>MSSV</label>
            <input
              type="text"
              name="mssv"
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            />
          </div>
          <div className={cx("input-container")}>
            <label>Họ lót</label>
            <input
              type="hoLot"
              name="hoLot"
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            />
          </div>
          <div className={cx("input-container")}>
            <label>Tên</label>
            <input
              type="ten"
              name="ten"
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            />
          </div>
       
          <div className={cx("input-container")}>
            <label>Giới Tính</label>
            <select
              name="gioiTinh"
              className={cx("select-form")}
              value="true"
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            >
              <option  value="true">Nam</option>
              <option value="false">Nữ</option>
            </select>
          </div>
          <div className={cx("input-container")}>
            <label>Ngày Sinh</label>
            <input
              type="date"
              name="ngaySinh"
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            />
          </div>
          <div className={cx("input-container")}>
            <label>Địa chỉ</label>
            <input
              type="diaChi"
              name="diaChi"
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            />
          </div>
          <div className={cx("input-container")}>
            <label>Số tài khoản</label>
            <input
              type="soTaiKhoan"
              name="soTaiKhoan"
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            />
          </div>
          <div className={cx("input-container")}>
            <label>Khoa</label>
            <select
              name="maKhoa"
              className={cx("select-form")}
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            >
              <option  value="">--Chọn khoa</option>
              <option  value="CNTT2">Công nghệ thông tin 2</option>
              <option value="VT2">Viễn thông 2</option>
              <option value="KT2">Kinh tế 2</option>
            </select>
          </div>
          <div className={cx("input-container")}>
            <label>Ngành</label>
            <select
              name="maNganh"
              className={cx("select-form")}
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            >
               <option  >--Chọn ngành học-</option>
              {listNganh.map(item => (
                <option key={item.id} value={item.maNganh}>
                  {item.tenNganh} {item.maNganh}
                </option>))}
            </select>
          </div>
          <div className={cx("input-container")}>
            <label>Lớp</label>
            <select
              name="maLop"
              className={cx("select-form")}
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            >
               <option  >--Chọn lớp học-</option>
              {listLop.map(item => (
                <option key={item.id} value={item.maLop}>
                  {item.tenLop} {item.maLop}
                </option>))}
            </select>
          </div>
          <div className={cx("input-container")}>
            <label>Năm bắt đầu</label>
            <input
              type="number"
              name="startYear"
              onChange={(e) => {
                handleOnChangInputNienKhoa(e);
              }}
            />
            <div className={cx("input-container")}>
            <label>Năm kết thúc</label>
            <input
              type="number"
              name="endYear"
              onChange={(e) => {
                handleOnChangInputNienKhoa(e);
              }}
            />
          </div>
    
          <div className={cx("input-container")}>
            <label>Hệ học</label>
            <select
              name="heHoc"
              className={cx("select-form")}
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            >
              <option value="Đại học chính quy">--Chọn hệ học</option>
              <option  value="Đại học chính quy">Đại học chính quy</option>
              <option value="Vừa học vừa làm">Vừa học vừa làm</option>
              <option value="Từ xa">Từ xa</option>
            </select>
          </div>
        </div>
        </div>
        <br />
      </ModalBody>
      <ModalFooter>
        <Button color="primary" className="px-3" onClick={handleAddNewSV}>
          Add new
        </Button>{" "}
        <Button color="secondary" className="px-3" onClick={toggle}>
          Close
        </Button>
      </ModalFooter>
    </Modal>
  );
};

export { ModalSinhVien };
