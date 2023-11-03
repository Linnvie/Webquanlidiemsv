import styles from "../Login/Login.module.scss";
import classNames from "classnames/bind";
import Button from "react-bootstrap/Button";
import Col from "react-bootstrap/Col";
import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
import Container from "react-bootstrap/Container";
import images from "../../../assets/images";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import { BrowserRouter as Router, Link, Route, Routes, useNavigate } from "react-router-dom";
import Header from "../../Header";
import { useState } from "react";
import { HandleLoginAPI } from "../../../service/UserService";
import SideBarRoute from "../SinhVien/Sidebar/SideBarRoute";
import Dashboard from "../Dashboard";
import { useDispatch } from "react-redux";
import { login } from "../../../redux/useSlices";
import { GetAllUsers } from "../../../service/UserService";
import { useEffect } from "react";
import { connect } from 'react-redux';
import axios from "axios";
import { values } from "lodash";
import {ModalForgetPassword} from "./ForgetPassword";
import { AlertCustom} from "../../Alert/Alert";




const cx = classNames.bind(styles);
function Login(props) {
  const [tenDangNhap, setTenDangNhap] = useState('')
  const [password, setPassWord] = useState('')
  const [errMessage, setErrMessage] = useState('')

  const [isOpenMadalPass, setIsOpenMadalPass] = useState(false)

  const [item, setItem] = useState({});
  const [alert, setAlert] = useState(false);

  
  const dispatch = useDispatch()
  
  // const handleSubmit = async(eve) => {
   
  //   setErrMessage('')
  //   eve.preventDefault()
  //   try{
  //     let data = await HandleLoginAPI(tenDangNhap, password)
  //     console.log(data);
  //     if(data && data.data.errCode !== 0) {
  //       setErrMessage(data.data.message)
  //     }
  //     if(data && data.data.errCode === 0) {
        
  //       dispatch(login({
  //         name:tenDangNhap,
  //         password:password,
  //         loggedIn: true
  //       }))
  //       props.userLoginSuccess(data.data.user)
  //       console.log('login success', data.data.user);
  //     }
  //   }catch(e) {
  //     if(e.response) {
  //       if(e.response.data) {
  //         setErrMessage(e.response.data.message) 
  //         // console.log(e.response.data.message);
  //       }
  //     }
  //     console.log(e);
  //   }
  // }

  const handleSubmit = async(eve) => {
   
    setErrMessage('')
    eve.preventDefault()
    try{
      let data = await HandleLoginAPI(tenDangNhap, password)
      console.log(data.data.data.role);
      
      if(data && data.data.data!=null) {
        console.log('login success');
        dispatch(login({
          name:tenDangNhap,
         // password:password,
          role: data.data.data.role,
          loggedIn: true
        }
        ))
        localStorage.setItem("accessToken", data.data.data.accessToken);
      }else{
        setErrMessage(data.data.message) 
      }
    }catch(e) {
      if(e.response) {
        if(e.response.data) {
          setErrMessage(e.response.data.message) 
          // console.log(e.response.data.message);
        }
      }
      console.log(e);
    }
  }

  let handleForgetPass  = async() => {
    setIsOpenMadalPass(true) 
    };

    let toglePassModal = () => {
        setIsOpenMadalPass(!isOpenMadalPass)
      }

      const handleChildValueChange = (newValue) => {
        setAlert(true); 
        setItem({"message": newValue.message,
                "type":newValue.type,
                "title":newValue.title,
               "onClose":{handleCloseAlert}})
      };
    
      const handleCloseAlert = () => {
        setAlert(false); 
      };
 
  
  return (
    <>

    {alert && (<AlertCustom className={cx("alert-container")} 
                      item={item}
                      onClose={handleCloseAlert}/>)}


    <ModalForgetPassword
          isOpenn = {isOpenMadalPass}
          togleFromParent = {toglePassModal}
          item={item}
          onValueChange={handleChildValueChange}
      />

      <Header/>
      <div className={cx("wrapper")}>
        <Container>
          <Row>
            <Col sm={5} className={cx("image-login")}>
              <img src={images.background} alt="" />
            </Col>
            <Col sm={7} className={cx("login")}>
              <div className={cx("login-wrapper")}>
                <Form className={cx("login-form")} onSubmit ={(e) => handleSubmit(e)}>
                  <Form.Group
                    as={Row}
                    className="mb-3"
                    controlId="formHorizontalEmail"
                  >
                    <Form.Label column sm={2}>
                      Tên đăng nhập
                    </Form.Label>
                    <Col sm={10}>
                      <Form.Control
                        value={tenDangNhap}
                        onChange = {e => setTenDangNhap(e.target.value)}
                        placeholder="Tên đăng nhập"
                        className={cx("placehoder-name")
                      }
                      />
                    </Col>
                  </Form.Group>
                      
                  <Form.Group
                    as={Row}
                    className="mb-3"
                    controlId="formHorizontalPassword"
                  >
                    <Form.Label column sm={2}>
                      Mật khẩu
                    </Form.Label>
                    <Col sm={10}>
                      <Form.Control
                        value={password}
                        onChange = {e => setPassWord(e.target.value)}
                        type="password"
                        placeholder="Mật khẩu"
                        className={cx("placehoder-name")}
                      />
                    </Col>
                  </Form.Group>
  
                 
  
                  <Form.Group as={Row} className="mb-3">
                    <Col sm={{ span: 10, offset: 2 }}>
                      <Nav className="me-auto x" >
                        {/* <Nav.Link as={Link} to="/bangdiem" className={cx('login-btn')} >
                          Đăng nhập
                        </Nav.Link> */}



                        {/* <Nav.Link as={Link} to="/sidebar" className={cx('login-btn')} 
                          onClick = {handleSubmit}
                        >
                          Đăng nhập
                        </Nav.Link> */}
                        <div style={{color:'red'}}>
                          {errMessage}
                        </div>
                        <Nav.Link className={cx('login-btn')} 
                          onClick = {handleSubmit}
                        >
                          Đăng nhập
                        </Nav.Link>


                      </Nav>
                    </Col>
                  </Form.Group>
                  <Form.Group
                    as={Row}
                    className="mb-3"
                    controlId="formHorizontalCheck"
                  >
                    
                    <Col sm={{  offset: 2 }}>
                      <Form.Check
                        label="Nhớ mật khẩu"
                        className={cx("form-remember-pass")}
                      />
                    </Col>
                    <Col sm={{ offset: 2 }}  onClick={handleForgetPass}>
                      Quên mật khẩu
                    </Col>
                  </Form.Group>
                </Form>
              </div>
            </Col>
          </Row>
        </Container>
      </div>
    </>
  );
}





export default Login;
