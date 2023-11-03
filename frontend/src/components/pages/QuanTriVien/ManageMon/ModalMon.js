import React, { useEffect, useState } from "react";
import { Button, Modal, ModalHeader, ModalBody, ModalFooter } from "reactstrap";
import styles from "../../../../container/UserManage.module.scss";
import classNames from "classnames/bind";
import {MonDto} from "../../../../dto/MonDto"
import { createNewMonService,SearchByKeyWordMon} from "../../../../service/UserService";
const ModalMon = (props) => {
  const cx = classNames.bind(styles);

  const [modal, setModal] = useState(false);
  const [valueForm, setValueForm] = useState(MonDto);

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
    "maMon",
    "tenMon",
    "soTinChi",
    "soTietLiThuyet",
    "soTietThucHanh",
    "heSo1",
    "heSo2",
    "heSo3"
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

  let handleAddNewMon =async () => {
    let isValid = checkValidateInput()
 
    if(isValid === true) {
      let response;
      try{
        await createNewMonService(valueForm);
      
        response = await SearchByKeyWordMon(props.searchTerm, props.paginate);
        setModal(props.togleFromParent);
        props.onValueChange({"message":response.data.message,
          "type":"success",
          "title":"Thành công"});

        props.listMon(response.data.data.listMon)
        props.paginate({"totalPages": response.data.data.totalPages,
            "currentPage": response.data.data.currentPage,
            "perPage": response.data.data.perPage})
        
      }catch(e){
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
      <ModalHeader toggle={toggle}>Thêm mới môn</ModalHeader>
      <ModalBody>
        <div className={cx("modal-user-body")}>
          <div className={cx("input-container")}>
            <label>Mã môn *</label>
            <input
              type="text"
              name="maMon"
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            />
          </div>
          <div className={cx("modal-user-body")}>
          <div className={cx("input-container")}>
            <label>Tên môn *</label>
            <input
              type="text"
              name="tenMon"
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            />
          </div>
        
          <div className={cx("input-container")}>
            <label>Số tín chỉ *</label>
            <input
              type="number"
              name="soTinChi"
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            />
          </div>
       
          
          <div className={cx("input-container")}>
            <label>Số tiết lí thuyết *</label>
            <input
              type="number"
              name="soTietLiThuyet"
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            />
          </div>
          <div className={cx("input-container")}>
            <label>Số tiết thực hành *</label>
            <input
              type="number"
              name="soTietThucHanh"
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            />
          </div>
          <div className={cx("input-container")}>
            <label>Hệ số 1 *</label>
            <input
              type="number" step="0.1"
              name="heSo1"
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            />
          </div>
          <div className={cx("input-container")}>
            <label>Hệ số 2 *</label>
            <input
             type="number" step="0.1"
              name="heSo2"
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            />
          </div>
          <div className={cx("input-container")}>
            <label>Hệ số 3 *</label>
            <input
              type="number" step="0.1"
              name="heSo3"
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            />
          </div>
          <div className={cx("input-container")}>
            <label>Hệ số 4</label>
            <input
             type="number" step="0.1"
              name="heSo4"
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            />
          </div>
          <div className={cx("input-container")}>
            <label>Hệ số 5</label>
            <input
              type="number" step="0.1"
              name="heSo5"
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            />
          </div>
         
         
</div>
       
        </div>
        <br />
      </ModalBody>
      <ModalFooter>
        <Button color="primary" className="px-3" onClick={handleAddNewMon}>
          Thêm mới
        </Button>{" "}
        <Button color="secondary" className="px-3" onClick={toggle}>
          Close
        </Button>
      </ModalFooter>
    </Modal>
  );
};

export { ModalMon };
