import React, { useEffect, useState } from "react";
import { Button, Modal, ModalHeader, ModalBody, ModalFooter } from "reactstrap";
import styles from "../../../../container/UserManage.module.scss";
import {MonDto} from "../../../../dto/MonDto"
import classNames from "classnames/bind";
import 'lodash'
import { isEmpty } from "lodash";
import { editMonService,SearchByKeyWordMon} from "../../../../service/UserService";

const ModalEditMon = (props) => {
  const cx = classNames.bind(styles);


  const [modal, setModal] = useState(false);
  const [nestedModal, setNestedModal] = useState(false);
  const [closeAll, setCloseAll] = useState(false);


  const [valueForm, setValueForm] = useState(MonDto || props.value);


  useEffect(() => {
    async function get() {
      
    }
    get()
  }, []);



  let mon = props.currentMon

  useEffect(() => {
    async function get() {

        if(mon && !isEmpty(mon)) {
           
            setValueForm(mon)
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

  let handleSaveMon = async() => {
   
    let isValid = checkValidateInput()
    if(isValid === true) {
      let response;
     
     try{
      await editMonService(valueForm);  
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
      <ModalHeader toggle={toggle}>Sửa thông tin Môn hoc {mon.tenMon}-{mon.maMon}</ModalHeader>
      <ModalBody>
        <div className={cx("modal-user-body")}>
          <div className={cx("input-container")}>
            <label>Mã môn</label>
            <input
              type="text"
              name="maMon"
              value={valueForm.maMon}
              onChange={(e) => {
                handleOnChangInput(e);
              }}
              disabled
            />
          </div>
          <div className={cx("input-container")}>
            <label>Tên môn *</label>
            <input
              type="text"
              name="tenMon"
              value={valueForm.tenMon}
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
              value={valueForm.soTinChi}
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
              value={valueForm.soTietLiThuyet}
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
              value={valueForm.soTietThucHanh}
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
              value={valueForm.heSo1}
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
              value={valueForm.heSo2}
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
              value={valueForm.heSo3}
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
              value={valueForm.heSo4}
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
              value={valueForm.heSo5}
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            />
          </div>
        </div>
        <br />
      </ModalBody>
      <ModalFooter>
        <Button color="primary" className="px-3" onClick={handleSaveMon}>
          Save changes
        </Button>{" "}
        <Button color="secondary" className="px-3" onClick={toggle}>
          Close
        </Button>
      </ModalFooter>
    </Modal>
  );
};

export { ModalEditMon};
