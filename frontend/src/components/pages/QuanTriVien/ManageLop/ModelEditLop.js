import React, { useEffect, useState } from "react";
import { Button, Modal, ModalHeader, ModalBody, ModalFooter } from "reactstrap";
import styles from "../../../../container/UserManage.module.scss";
import {LopDto} from "../../../../dto/LopDto"
import classNames from "classnames/bind";
import 'lodash'
import { isEmpty } from "lodash";
import { editLopService,SearchByKeyWordLop,GetAllNganh} from "../../../../service/UserService";

const ModalEditLop = (props) => {
  const cx = classNames.bind(styles);

  const [modal, setModal] = useState(false);
  const [valueForm, setValueForm] = useState(props.currentLop);
  const [listNganh, setListNganh] = useState([]);

  const [startYear, setStartYear] = useState(valueForm.nienKhoa.split("-")[0]);
  const [endYear, setEndYear] = useState(valueForm.nienKhoa.split("-")[1]);

  useEffect(() => {
    async function get() {
      getAllMonFromReact(); 
    }
    get()
  }, []);

  let getAllMonFromReact = async() => {
    const response = await GetAllNganh();
    if(response.data.data===null){       
      setListNganh([])
      return
    }
      setListNganh(response.data.data);

  }

  let lop = props.currentLop

  useEffect(() => {
    async function get() {

        if(lop && !isEmpty(lop)) {        
            setValueForm(lop)
        }
    }
    get()
  }, [])


  const toggle = 
  () => setModal(props.togleFromParent);
  
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
      "maLop",
      // "tenLop",
      // "maKhoa",
      // "maNganh",
      // "nienKhoa"
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

  let handleSaveLop = async() => {
 let isValid = checkValidateInput()
    if(isValid === true) {

      let response;
      try{
        await editLopService(valueForm);
      
        response = await SearchByKeyWordLop(props.searchTerm, props.paginate);
        setModal(props.togleFromParent);
        props.onValueChange({"message":response.data.message,
                            "type":"success",
                            "title":"Thành công"});

        props.listLop(response.data.data.listLop)
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
      <ModalHeader toggle={toggle}>Sửa thông tin Lớp</ModalHeader>
      <ModalBody>
        <div className={cx("modal-user-body")}>
          <div className={cx("input-container")}>
            <label>Mã lớp *</label>
            <input
              type="text"
              name="maLop"
              value={valueForm.maLop}
              onChange={(e) => {
                handleOnChangInput(e);
              }}
              disabled
            />
          </div>
          <div className={cx("input-container")}>
            <label>Tên lớp *</label>
            <input
              type="text"
              name="tenLop"
              value={valueForm.tenLop}
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            />
          </div>
          
          <div className={cx("input-container")}>
            <label>Khoa  *</label>
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
            <label>Ngành *</label>
             <select
              name="maNganh"
              value={valueForm.maNganh}
              className={cx("select-form")}
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            >
              <option value="CQPU" >--Chọn ngành-</option>
              <option value="CQPU">CQPU</option>
              {listNganh.map(item => (
                <option key={item.id} value={item.maNganh}>
                  {item.tenNganh} {item.maNganh}
                </option>))}
            </select>
          </div>
          <div className={cx("input-container")}>
            <label>Năm bắt đầu *</label>
            <input
              type="number"
              name="startYear"
              value={valueForm.nienKhoa.split("-")[0]}
              onChange={(e) => {
                handleOnChangInputNienKhoa(e);
              }}
            />
            <div className={cx("input-container")}>
            <label>Năm kết thúc *</label>
            <input
              type="number"
              name="endYear"
              value={valueForm.nienKhoa.split("-")[1]}
              onChange={(e) => {
                handleOnChangInputNienKhoa(e);
              }}
            />
          </div>
        </div>
        </div>
        <br />
      </ModalBody>
      <ModalFooter>
        <Button color="primary" className="px-3" onClick={handleSaveLop}>
          Save changes
        </Button>{" "}
        <Button color="secondary" className="px-3" onClick={toggle}>
          Close
        </Button>
      </ModalFooter>
    </Modal>
  );
};

export { ModalEditLop};
