import React, { useEffect, useState } from "react";
import { Button, Modal, ModalHeader, ModalBody, ModalFooter } from "reactstrap";
import styles from "../../../fontend/src/container/UserManage.module.scss";
import classNames from "classnames/bind";
import 'lodash'
import { isEmpty } from "lodash";
import {SinhVienDto} from "../dto/SinhVienDto"
import {editSVService, GetAllStudentLop, GetAllLop, GetAllNganh} from "../service/UserService"

const ModalEditSinhVien = (props) => {
  const cx = classNames.bind(styles);

  const [modal, setModal] = useState(false);
  const [startYear, setStartYear] = useState('');
  const [endYear, setEndYear] = useState('');

  const [listNganh, setListNganh] = useState([]);
  const [listLop, setListLop] = useState([]);

  const [valueForm, setValueForm] = useState(SinhVienDto || props.value);
  let sinhVien = props.currentSV

  useEffect(() => {
    async function get() {
        if(sinhVien && !isEmpty(sinhVien)) {       
            setValueForm(sinhVien)
            const parts=sinhVien.nienKhoa.split("-")
            setStartYear(parts[0])
            setEndYear(parts[1])
        }
        getAllNganhFromReact(); 
        getAllLopFromReact(); 
    }
    get()
  }, [])

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
 
  
  let handleOnChangInput = (e, id) => {
    setValueForm({
      ...valueForm,
      [e.target.name] : e.target.value,
    });
  };

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

  let checkValidateInput = () => {
    let arrayInput = [
     // "mssv",
      "hoLot",
      "ngaySinh",
      "ten",
    //  "gioiTinh",
    //  "maNganh",
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

  let handleSaveSV = async() => {
    let isValid = checkValidateInput()
    if(isValid === true) {
      try {
        await editSVService(valueForm);
        setModal(props.togleFromParent);
        const response = await GetAllStudentLop(props.maLop,props.paginate);
        props.onValueChange({"message":response.data.message,
                            "type":"success",
                            "title":"Thành công"});
        
        props.listSV(response.data.data.listSV);
        props.paginate({"totalPages": response.data.data.totalPages,
                          "currentPage": response.data.data.currentPage,
                          "perPage": response.data.data.perPage})
      } catch (e) {
        props.onValueChange({"message":e.response.data.message,
                            "type":"error",
                          "title":"Lỗi"});
      }
    }  
  };

  return (
    <Modal
      isOpen={props.isOpenn}
      toggle={toggle}
      size="lg"
      centered
      className={cx("modal-user-container")}
    >
      <ModalHeader toggle={toggle}>Sửa thông tin Sinh viên {sinhVien.hoLot} {sinhVien.ten}-{sinhVien.mssv}</ModalHeader>
      <ModalBody>
        
        <div className={cx("modal-user-body")}>
        <div className={cx("input-container")}>
            <label>Mã số sinh viên</label>
            <input
              type="text"
              name="mssv"
              value={valueForm.mssv}
              onChange={(e) => {
                handleOnChangInput(e);
              }}
              disabled
            />
          </div>
          
          <div className={cx("input-container")}>
            <label>Họ lót</label>
            <input
              type="hoLot"
              name="hoLot"
              value={valueForm.hoLot}
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
              value={valueForm.ten}
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
              value={valueForm.gioiTinh}
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            >
               <option  value="">--Chọn giới tính--</option>
              <option  value="true">Nam</option>
              <option value="false">Nữ</option>
            </select>
          </div>
          <div className={cx("input-container")}>
            <label>Ngày Sinh</label>
            <input
              type="date"
              name="ngaySinh"
              value={valueForm.ngaySinh}
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
              value={valueForm.diaChi}
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
              value={valueForm.soTaiKhoan}
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            />
          </div>
          <div className={cx("input-container")}>
            <label>Khoa</label>
            <select
              name="maKhoa"
              value={valueForm.maKhoa}
              className={cx("select-form")}
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            >
              <option  value="">--Chọn khoa--</option>
              <option  value="CNTT2">Công nghệ thông tin 2</option>
              <option value="VT2">Viễn thông 2</option>
              <option value="KT2">Kinh tế 2</option>
            </select>
          </div>
          <div className={cx("input-container")}>
            <label>Ngành</label>
            <select
              name="maNganh"
              value={valueForm.maNganh}
              className={cx("select-form")}
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            >
               <option value="" >--Chọn ngành học-</option>
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
              value={valueForm.maLop}
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
            <label>Trạng thái</label>
            <select
              name="status"
              value={valueForm.status}
              className={cx("select-form")}
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            >
              <option  value="">--Chọn trạng thái</option>
              <option  value="STUDYING">Đang học</option>
              <option value="RESERVE">Bảo lưu</option>
              <option value="DROPOUT">Nghỉ học</option>
              <option  value="COMPLETE">Hoàn thanh</option>
            </select>
          </div>
          <div className={cx("input-container")}>
            <label>Năm bắt đầu</label>
            <input
              type="number"
              name="startYear"
              value={startYear}
              onChange={(e) => {
                handleOnChangInputNienKhoa(e);
              }}
            />
            <div className={cx("input-container")}>
            <label>Năm kết thúc</label>
            <input
              type="number"
              name="endYear"
              value={endYear}
              onChange={(e) => {
                handleOnChangInputNienKhoa(e);
              }}
            />
          </div>
    
          <div className={cx("input-container")}>
            <label>Hệ học</label>
            <select
              name="heHoc"
              value={valueForm.heHoc}
              className={cx("select-form")}
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            >
              <option value="">--Chọn hệ học</option>
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
        <Button color="primary" className="px-3" onClick={handleSaveSV}>
          Save changes
        </Button>{" "}
        <Button color="secondary" className="px-3" onClick={toggle}>
          Close
        </Button>
      </ModalFooter>
    </Modal>
  );
};

export { ModalEditSinhVien };
