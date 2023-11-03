import React, { useEffect, useState } from "react";
import { Button, Modal, ModalHeader, ModalBody, ModalFooter } from "reactstrap";
import styles from "../../../../container/UserManage.module.scss";
import {LopTinChiDto} from "../../../../dto/LopTinChiDto"
import classNames from "classnames/bind";
import 'lodash'
import { isEmpty } from "lodash";
import {AiOutlineClose} from "react-icons/ai";
import { editLTCService,SearchByKeyWordLTC,GetAllMon,GetAllLTCByMon,GetAllGVActive} from "../../../../service/UserService";

const ModalEditLopTinChi = (props) => {
  const cx = classNames.bind(styles);
  console.log(props)

  const [modal, setModal] = useState(false);
  
  const [valueForm, setValueForm] = useState( props.currentLTC);
  console.log(valueForm)
  const [listMon, setListMon] = useState([]);
  const [listGV, setListGV] = useState([]);
  let LTC = props.currentLTC

  useEffect(() => {
    async function get() {
      getAllMonFromReact(); 
      getAllGVFromReact();

      if(LTC && !isEmpty(LTC)) {
           
        setValueForm(LTC)
    }
    }
    get()
  }, []);

  let getAllMonFromReact = async() => {
    const response = await GetAllMon();
    if(response.data.data===null){       
      setListMon([])
      return
    }
      setListMon(response.data.data);

  }

  let getAllGVFromReact = async() => {
    const response = await GetAllGVActive();
    if(response.data.data===null){       
      setListGV([])
      return
    }
      setListGV(response.data.data);

  }
  const toggle = 
  () => setModal(props.togleFromParent);

  let handleOnChangInput = (e, id) => {
    setValueForm({
      ...valueForm,
      [e.target.name] : e.target.value,
    });
  };

  let handleX = (e, index) => {
    setValueForm({
      ...valueForm,
      [e.target.name]: valueForm.listGiangVien.splice(index, 1),
    });

  };

  

  let checkValidateInput = () => {
    let arrayInput = [
      "maLopTinChi",
      "maMon",
      "hocKi",
      "nam",
    //  "trangThai",
      "soSVToiThieu",
      "soSVToiDa"
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

  let handleSaveLTC = async() => {
    let isValid = checkValidateInput()
    if(isValid === true) {
    
      try {
        await editLTCService(valueForm);
        let response;
        if(props.maMon!==""){
          response = await GetAllLTCByMon(props.maMon, props.paginate);   
        }else{
          response = await SearchByKeyWordLTC(props.searchTerm,props.paginate);
        }
        setModal(props.togleFromParent);
        props.onValueChange({"message":response.data.message,
        "type":"success",
        "title":"Thành công"});
        props.listLtc(response.data.data.listLTC)
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
  let handleOnChangInputList = (e) => {
    setValueForm({
      ...valueForm,
      [e.target.name]: [...valueForm.listGiangVien, e.target.value.trim()],
    });

  };

  return (
    <Modal
      isOpen={props.isOpenn}
      toggle={toggle}
      size="lg"
      centered
      className={cx("modal-user-container")}
    >
      <ModalHeader toggle={toggle}>Sửa thông tin Lớp tín chỉ</ModalHeader>
      <ModalBody>
        <div className={cx("modal-user-body")}>
          <div className={cx("input-container")}>
            <label>Mã lớp tín chỉ *</label>
            <input
              type="text"
              name="maLopTinChi"
              value={valueForm.maLopTinChi}
              onChange={(e) => {
                handleOnChangInput(e);
              }}
              disabled
            />
          </div>
          <div className={cx("input-container")}>
            <label>Mã môn *</label>
            <select
              name="maMon"
              value={valueForm.maMon}
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            >
              <option  >--Chọn môn học-</option>
              {listMon.map(item => (
                <option key={item.id} value={item.maMon}>
                  {item.tenMon} {item.maMon}
                </option>))}
            </select>
          </div>
          <div className={cx("input-container")}>
            <label>Năm *</label>
            <input
              type="number"
              name="nam"
              value={valueForm.nam}
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            />
          </div>

          <div className={cx("input-container")}>
            <label>Học kì *</label>
            <select
              name="hocKi"
              className={cx("select-form")}
              value={valueForm.hocKi}
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            >
              <option  >--Chọn học kì-</option>
              <option  value="1">1</option>
              <option value="2">2</option>
              <option  value="3">3</option>
              <option value="4">4</option>
            </select>
          </div>

          <div className={cx("input-container")}>
            <label>Số sinh viên tối thiểu *</label>
            <input
              type="number"
              name="soSVToiThieu"
              value={valueForm.soSVToiThieu}
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            />
          </div>

          <div className={cx("input-container")}>
            <label>Số sinh viên tối đa *</label>
            <input
              type="number"
              name="soSVToiDa"
              value={valueForm.soSVToiDa}
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            />
          </div>

          <div className={cx("input-container")}>
            <label>Số lượng đăng kí</label>
            <input
              type="text"
              name="slDangKi"
              value={valueForm.slDangKi}
              onChange={(e) => {
                handleOnChangInput(e);
              }}
              disabled
            />
          </div>
          <div className={cx("input-container")}>
            <label>Trạng thái *</label>
            <select
            name="trangThai"
            value={valueForm.trangThai}
              className={cx("select-form")}
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            >
              <option value="true">Mở</option>
              <option value="false">Đóng</option>
            </select>
          </div>
          <div className={cx("input-container")}>
            <label>Giảng viên</label>
             <select
              name="listGiangVien"
              className={cx("select-form")}
              onChange={(e) => {
                handleOnChangInputList(e);
              }}
            >
              <option  >--Chọn giảng viên-</option>
              {listGV.map(item => (
                <option key={item.id} value={item.maGiangVien}>
                  {item.hoLot} {item.ten} - {item.maGiangVien} 
                </option>))}
                
            </select>
            {valueForm.listGiangVien && valueForm.listGiangVien.map((giangVien, index) => (
                  <div key={index} style={{marginLeft:"40px"}}>{giangVien} 
                    <span key={index} style={{marginLeft:"20px"}}
                      onClick={(e, index) => {
                        handleX(e);
                      }}><AiOutlineClose/>
                    </span>
                  </div>))}

          </div>
        </div>
        <br />
      </ModalBody>
      <ModalFooter>
        <Button color="primary" className="px-3" onClick={handleSaveLTC}>
          Save changes
        </Button>{" "}
        <Button color="secondary" className="px-3" onClick={toggle}>
          Close
        </Button>
      </ModalFooter>
    </Modal>
  );
};

export { ModalEditLopTinChi };
