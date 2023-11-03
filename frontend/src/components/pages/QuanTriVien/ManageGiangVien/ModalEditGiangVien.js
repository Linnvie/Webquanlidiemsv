import React, { useEffect, useState } from "react";
import { Button, Modal, ModalHeader, ModalBody, ModalFooter } from "reactstrap";
import styles from "../../../../container/UserManage.module.scss";
import {GiangVienDto} from "../../../../dto/GiangVienDto"
import classNames from "classnames/bind";
import 'lodash'
import { isEmpty } from "lodash";
import { editGVService,SearchByKeyWordGiangVien} from "../../../../service/UserService";

const  ModalEditGiangVien = (props) => {
  const cx = classNames.bind(styles);


  const [modal, setModal] = useState(false);
  const [nestedModal, setNestedModal] = useState(false);
  const [closeAll, setCloseAll] = useState(false);


  const [valueForm, setValueForm] = useState(GiangVienDto || props.value);


  useEffect(() => {
    async function get() {
      
    }
    get()
  }, []);



  let gv = props.currentGV

  useEffect(() => {
    async function get() {

        if(gv && !isEmpty(gv)) {
           
            setValueForm(gv)
        }
    }
    get()
  }, [])


  const toggle = 
  () => setModal(props.togleFromParent);

  const toggleNested = () => {
    setNestedModal(!nestedModal);
    setCloseAll(false);
  };

  const toggleAll = () => {
    setNestedModal(!nestedModal);
    setCloseAll(true);
  };
  

  let handleOnChangInput = (e, id) => {
    setValueForm({
      ...valueForm,
      [e.target.name] : e.target.value,
    });
  };

  let checkValidateInput = () => {
    let arrayInput = [
      "maGiangVien",
      "hoLot",
      "ngaySinh",
      "ten",
      "gioiTinh",
      "hocHam",
     "maKhoa",
      "hocVi"
    ];
    let isValid = true
    for(let i = 0; i< arrayInput.length; i++) {
      if(!valueForm[arrayInput[i]]){
        isValid = false
        props.onValueChange({"message":"  Vui lòng điền đầy đủ thông tin các mục bắt buộc (*)",
                            "type":"error",
                          "title":"Lỗi"});
        break
      }
    }
    return isValid
  };

  let handleSaveGV = async() => {
   
    let isValid = checkValidateInput()
    if(isValid === true) {
      
     try {
       await editGVService(valueForm);     
       
       const response = await SearchByKeyWordGiangVien(props.searchTerm, props.paginate);
       setModal(props.togleFromParent);
       props.onValueChange({"message":response.data.message,
         "type":"success",
         "title":"Thành công"});

       props.listGV(response.data.data.listGV)
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
      <ModalHeader toggle={toggle}>Sửa thông tin Giảng viên {gv.hoLot} {gv.ten}-{gv.maGiangVien}</ModalHeader>
      <ModalBody>
        
        <div className={cx("modal-user-body")}>
        <div className={cx("input-container")}>
            <label>Mã giảng viên *</label>
            <input
              type="text"
              name="mssv"
              value={valueForm.maGiangVien}
              onChange={(e) => {
                handleOnChangInput(e);
              }}
              disabled
            />
          </div>
          
          <div className={cx("input-container")}>
            <label>Họ lót *</label>
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
            <label>Tên *</label>
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
            <label>Giới Tính *</label>
            <select
              name="gioiTinh"
              className={cx("select-form")}
              value={valueForm.gioiTinh}
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
              name="ngaySinh *"
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
            <label>Khoa *</label>
            <select
              name="maKhoa"
              value={valueForm.maKhoa}
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
            <label>Học hàm *</label>
            <input
              type="text"
              name="hocHam"
              value={valueForm.hocHam}
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            />
          </div>
          <div className={cx("input-container")}>
            <label>Học vị *</label>
            <input
              type="text"
              name="hocVi"
              value={valueForm.hocVi}
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            />
          </div>
        </div>
        <br />
      </ModalBody>
      <ModalFooter>
        <Button color="primary" className="px-3" onClick={handleSaveGV}>
          Save changes
        </Button>{" "}
        <Button color="secondary" className="px-3" onClick={toggle}>
          Close
        </Button>
      </ModalFooter>
    </Modal>
  );
};

export { ModalEditGiangVien};
