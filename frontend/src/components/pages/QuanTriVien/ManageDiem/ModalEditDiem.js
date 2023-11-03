import React, { useEffect, useState } from "react";
import { Button, Modal, ModalHeader, ModalBody, ModalFooter } from "reactstrap";
import styles from "../../../../container/UserManage.module.scss";
import {DiemDto} from "../../../../dto/DiemDto"
import classNames from "classnames/bind";
import 'lodash'
import { isEmpty } from "lodash";
import { editDiemService,GetAllDiemByLTC} from "../../../../service/UserService";


const ModalEditDiem = (props) => {
  const cx = classNames.bind(styles);
  const [modal, setModal] = useState(false);
  const [valueForm, setValueForm] = useState(DiemDto || props.value);
  let diem = props.currentDiem

  useEffect(() => {
    async function get() {
      if(diem && !isEmpty(diem)) {        
        setValueForm({id:{
                      mssv:diem.mssv,
                      maLTC: diem.maLTC},
                    diemHe1:diem.diemHe1,
                    diemHe2:diem.diemHe2,
                    diemHe3:diem.diemHe3,
                    diemHe4:diem.diemHe4,
                    diemHe5:diem.diemHe5,
                      lan:diem.lan})
    }
    }
    get()
  }, []);

  const toggle = 
  () => setModal(props.togleFromParent);
  let handleOnChangInput = (e, id) => {
    setValueForm({
      ...valueForm,
      [e.target.name] : e.target.value,
    });
  };

  let checkValidateInput = () => {
    let arrayInput = [
      // "id.mssv",
      "id",
      // "diemHe1",
      // "diemHe2",
      // "diemHe3",
      // "lan",
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

  let handleSaveDiem = async() => {
    let isValid = checkValidateInput()
    if(isValid === true) {
      try {
        await editDiemService(valueForm);
        const response = await GetAllDiemByLTC(valueForm.id.maLTC,props.paginate);
        setModal(props.togleFromParent);
        props.onValueChange({"message":response.data.message,
        "type":"success",
        "title":"Thành công"});
        props.listDiem(response.data.data.listBangDiem);
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
      <ModalHeader toggle={toggle}>Nhập điểm</ModalHeader>
      <ModalBody>
        <div className={cx("modal-user-body")}>
          
          <div className={cx("input-container")}>
          <input
                type="text"
                name="lan"
                value={valueForm.lan}
               hidden
                disabled
              />
              <label>Mã số sinh viên</label>
              <input
                type="text"
                name="id.mssv"
                value={valueForm.id.mssv}
                onChange={(e) => {
                  handleOnChangInput(e);
                }}
                disabled
              />
            </div>
              <div className={cx("input-container")}>
              <label>Mã lớp tín chỉ</label>
              <input
                type="text"
                name="id.maLTC"
                value={valueForm.id.maLTC}
                onChange={(e) => {
                  handleOnChangInput(e);
                }}
                disabled
              />
            </div>
          <div className={cx("input-container")}>
            <label>Điểm hệ 1</label>
            <input
              type="number" step="0.01"
              name="diemHe1"
              value={valueForm.diemHe1}
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            />
          </div>
          <div className={cx("input-container")}>
            <label>Điểm hệ 2</label>
            <input
              type="number" step="0.01"
              name="diemHe2"
              value={valueForm.diemHe2}
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            />
          </div>
          <div className={cx("input-container")}>
            <label>Điểm hệ 3</label>
            <input
              type="number" step="0.01"
              name="diemHe3"
              value={valueForm.diemHe3}
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            />
          </div>
          <div className={cx("input-container")}>
            <label>Điểm hệ 4</label>
            <input
              type="number" step="0.01"
              name="diemHe4"
              value={valueForm.diemHe4}
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            />
          </div>
          <div className={cx("input-container")}>
            <label>Điểm hệ 5</label>
            <input
              type="number" step="0.01"
              name="diemHe5"
              value={valueForm.diemHe5}
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            />
          </div>
        </div>
        <br />
      </ModalBody>
      <ModalFooter>
        <Button color="primary" className="px-3" onClick={handleSaveDiem}>
          Save changes
        </Button>{" "}
        <Button color="secondary" className="px-3" onClick={toggle}>
          Close
        </Button>
      </ModalFooter>
    </Modal>
  );
};

export { ModalEditDiem };
