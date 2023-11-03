import React, { useEffect, useState } from "react";
import { Button, Modal, ModalHeader, ModalBody, ModalFooter } from "reactstrap";
import styles from "../../../container/UserManage.module.scss";
import classNames from "classnames/bind";
import { ChangePasswordService} from "../../../service/UserService";
const ModalChangePassword = (props) => {
  const cx = classNames.bind(styles);

  const [modal, setModal] = useState(false);
  const [valueForm, setValueForm] = useState({"username":"",
                                                "currentPassword":"",
                                            "newPassword":""});

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
          "currentPassword",
          "newPassword",
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

  let handleChangePass =async () => {
    let isValid = checkValidateInput()
 
    if(isValid === true) {
    
    let response;
     try{    
      response = await ChangePasswordService({...valueForm,
            "username":props.currentUser.name});   

      props.onValueChange({"message":response.data.message,
                          "type":"success",
                          "title":"Thành công"});
      setModal(props.togleFromParent);
     }catch(e){
      props.onValueChange({"message":e.response.data.message,
                            "type":"error",
                           "title":"Lỗi"});
     }
    }

  };

  return (
   <>

    <Modal
      isOpen={props.isOpenn}
      toggle={toggle}
      size="lg"
      centered
      className={cx("modal-user-container")}
    >
      <ModalHeader toggle={toggle}>Đổi mật khẩu</ModalHeader>
      <ModalBody>
        <div className={cx("modal-user-body")} >
          <div className={cx("input-container")} style={{margin:"auto"}}>
            <label>Mật khẩu hiện tại *</label>
            <input
              type="text"
              name="currentPassword"
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            />
          </div>
          </div>
          <div className={cx("modal-user-body")}>
          
          <div className={cx("input-container")} style={{margin:"auto"}}>
            <label>Mật khẩu mới *</label>
            <input
              type="text"
              name="newPassword"
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            />   
</div>
       
        </div>
        <br />
      </ModalBody>
      <ModalFooter>
        <Button color="primary" className="px-3" onClick={handleChangePass}>
          Đổi mật khẩu
        </Button>{" "}
        <Button color="secondary" className="px-3" onClick={toggle}>
          Close
        </Button>
      </ModalFooter>
    </Modal>
    </>
  );
};

export { ModalChangePassword };
