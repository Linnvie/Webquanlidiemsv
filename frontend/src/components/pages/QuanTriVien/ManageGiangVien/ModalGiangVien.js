import React, { useEffect, useState } from "react";
import { Button, Modal, ModalHeader, ModalBody, ModalFooter } from "reactstrap";
import styles from "../../../../container/UserManage.module.scss";
import classNames from "classnames/bind";
import {GiangVienDto} from "../../../../dto/GiangVienDto"
import {SearchByKeyWordGiangVien,createNewGVService} from "../../../../service/UserService";
const ModalGiangVien = (props) => {
  const cx = classNames.bind(styles);

  const [modal, setModal] = useState(false);
  const [valueForm, setValueForm] = useState(GiangVienDto);

  useEffect(() => {
    async function get() {
   
    }
    get()
  }, []);
 
  const toggle = () => setModal(props.togleFromParent);

  let handleOnChangInput = (e) => {
    setValueForm({
      ...valueForm,
      [e.target.name]: e.target.value.trim(),
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

  let handleAddNewGV =async () => {
    let isValid = checkValidateInput()
 
    if(isValid === true) {
      let response;
      try {
        await createNewGVService(valueForm);
        response = await SearchByKeyWordGiangVien(props.searchTerm, props.paginate);
        console.log(1)

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
      <ModalHeader toggle={toggle}>Thêm mới giảng viên</ModalHeader>
      <ModalBody>
        <div className={cx("modal-user-body")}>
          <div className={cx("input-container")}>
            <label>Mã giảng viên *</label>
            <input
              type="text"
              name="maGiangVien"
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            />
          </div>
          <div className={cx("input-container")}>
            <label>Họ lót *</label>
            <input
              type="hoLot"
              name="hoLot"
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
            <label>Ngày Sinh *</label>
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
            <label>Khoa *</label>
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
            <label>Học hàm *</label>
            <input
              type="text"
              name="hocHam"
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
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            />
          </div>
        </div>
        <br />
      </ModalBody>
      <ModalFooter>
        <Button color="primary" className="px-3" onClick={handleAddNewGV}>
          Thêm mới
        </Button>{" "}
        <Button color="secondary" className="px-3" onClick={toggle}>
          Close
        </Button>
      </ModalFooter>
    </Modal>
  );
};

export {ModalGiangVien};
