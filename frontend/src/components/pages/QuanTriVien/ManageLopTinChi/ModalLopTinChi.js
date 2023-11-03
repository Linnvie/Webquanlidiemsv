import React, { useEffect, useState } from "react";
import { Button, Modal, ModalHeader, ModalBody, ModalFooter } from "reactstrap";
import styles from "../../../../container/UserManage.module.scss";
import classNames from "classnames/bind";
import {LopTinChiDto} from "../../../../dto/LopTinChiDto"
import { createNewLTCService,GetAllMon ,GetAllLTCByMon,GetAllGVActive, SearchByKeyWordLTC} from "../../../../service/UserService";
import {AiOutlineClose} from "react-icons/ai";
const ModalLopTinChi = (props) => {
  const cx = classNames.bind(styles);

  const [modal, setModal] = useState(false);
  const [valueForm, setValueForm] = useState(LopTinChiDto);
  const [listMon, setListMon] = useState([]);
  const [listGV, setListGV] = useState([]);
  const [tempListGiangVien, setTempListGiangVien] = useState([]); 

  useEffect(() => {
    async function get() {
      getAllMonFromReact(); 
      getAllGVFromReact(); 
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
  const toggle = () => setModal(props.togleFromParent);

  let handleOnChangInput = (e) => {
    setValueForm({
      ...valueForm,
      [e.target.name]: e.target.value.trim(),
    });

  };

  let handleOnChangInputList = (e) => {
    setValueForm({
      ...valueForm,
      [e.target.name]: [...valueForm.listGiangVien, e.target.value.trim()],
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
      "trangThai",
      "soSVToiThieu",
      "soSVToiDa"
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

  let handleAddNewLTC =async () => {
    let isValid = checkValidateInput()
   try {
     if(isValid === true) {
       await createNewLTCService(valueForm);
       setModal(props.togleFromParent);

       let response;
        if(props.maMon!==""){
          response = await GetAllLTCByMon(props.maMon, props.paginate);   
        }else{
          response = await SearchByKeyWordLTC(props.searchTerm,props.paginate);  
        }
        props.onValueChange({"message":response.data.message,
        "type":"success",
        "title":"Thành công"});

        props.listLtc(response.data.data.listLTC)
        props.paginate({"totalPages": response.data.data.totalPages,
            "currentPage": response.data.data.currentPage,
            "perPage": response.data.data.perPage})
       
     }   
     
   } catch (e) {
    props.onValueChange({"message":e.response.data.message,
              "type":"error",
              "title":"Lỗi"});
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
      <ModalHeader toggle={toggle}>Thêm lớp tín chỉ</ModalHeader>
      <ModalBody>
        <div className={cx("modal-user-body")}>
          <div className={cx("input-container")}>
            <label>Mã lớp tín chỉ *</label>
            <input
              type="text"
              name="maLopTinChi"
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            />
          </div>
          <div className={cx("input-container")}>
            <label>Môn học *</label>
             <select
              name="maMon"
              className={cx("select-form")}
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
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            />
          </div>
         
          <div className={cx("input-container")}>
            <label>Trạng thái *</label>
            <select
              name="trangThai"
              className={cx("select-form")}
              onChange={(e) => {
                handleOnChangInput(e);
              }}
            >
              <option>--Chọn trạng thái</option>
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
           
                {valueForm.listGiangVien.map((giangVien, index) => (
                  <div key={index} style={{marginLeft:"40px"}}>{giangVien} 
                    <span key={index} style={{marginLeft:"20px"}}
                      onClick={(e, index) => {
                        handleX(e);
                      }}><AiOutlineClose/>
                    </span>
                  </div>
        ))}
          </div>
       
        </div>
        <br />
      </ModalBody>
      <ModalFooter>
        <Button color="primary" className="px-3" onClick={handleAddNewLTC}>
          Thêm mới
        </Button>{" "}
        <Button color="secondary" className="px-3" onClick={toggle}>
          Close
        </Button>
      </ModalFooter>
    </Modal>
  );
};

export { ModalLopTinChi };
