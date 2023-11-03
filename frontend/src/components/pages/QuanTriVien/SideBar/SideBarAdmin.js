import React, { useState } from "react";
import {
  BsCalendarDate,
  BsFillHddNetworkFill,
  BsGraphDown,
  BsMenuButton,
  BsReceipt,
} from "react-icons/bs";

import { AiOutlineReconciliation, AiOutlineInsertRowBelow, AiOutlineUserSwitch, AiOutlineUser,AiFillRead } from "react-icons/ai";
import { BiLogOutCircle } from "react-icons/bi";


import { NavLink } from "react-router-dom";
import classNames from "classnames/bind";
import styles from "../../SinhVien/Sidebar/Sidebar.module.scss";
import { useDispatch } from "react-redux";
import { login, logout } from "../../../../redux/useSlices";
const cx = classNames.bind(styles);

function SidebarAdmin({ children }) {
  const [isOpen, setIsOpen] = useState(false);
  const toggle = () => setIsOpen(!isOpen);

  const dispatch = useDispatch();
  const handleLogout = (e) => {
    e.preventDefault()
    

    console.log("xx", sidebarlist[sidebarlist.length - 1]);
    
    localStorage.removeItem('accessToken');
    dispatch(logout());
  };

  const sidebarlist = [
    {
      path: "/dashboard",
      name: "Trang chủ",
      icon: <BsCalendarDate />,
    },
    {
      path: "/admin",
      name: "Thông tin cá nhân",
      icon: <AiOutlineUser />,
    },
    {
      path: "/mon",
      name: "Môn học",
      icon: <AiFillRead />,
    },
    
    {
      path: "/ltc",
      name: "Lớp tín chỉ",
      icon: <AiOutlineReconciliation/>,
    },
    {
      path: "/lop",
      name: "Lớp",
      icon: <AiOutlineInsertRowBelow/>,
    },
    {
      path: "/giangvien",
      name: "Giảng viên",
      icon: <AiOutlineUserSwitch/>,
    },
    {
      path: "/logout",
      name: "Đăng xuất",
      icon: <BiLogOutCircle />,
    },
  ];

  return (
    <div className={cx("container-sidebar")}>
      <div
        style={{ width: isOpen ? "200px" : "50px" }}
        className={cx("sidebar")}
      >
        <div className={cx("top-section")}>
          <div
            style={{ marginLeft: isOpen ? "50px" : "0" }}
            className={cx("bars")}
          >
            <BsMenuButton onClick={toggle} />
          </div>
        </div>
        {sidebarlist.map((item, index) => {
          if (sidebarlist.length - 1 === index) {
            return (
              <NavLink
                to={item.path}
                key={item.index}
                className={cx("link")}
                activeClassName="active"
                onClick={(e) => handleLogout(e)}
              >
                <div className={cx("icon")}>{item.icon}</div>
                <div
                  style={{ display: isOpen ? "block" : "none" }}
                  className={cx("link-text")}
                >
                  {item.name}
                </div>
              </NavLink>
            );
          }

          return (
            <NavLink
              to={item.path}
              key={item.index}
              className={cx("link")}
              activeClassName="active"
            >
              <div className={cx("icon")}>{item.icon}</div>
              <div
                style={{ display: isOpen ? "block" : "none" }}
                className={cx("link-text")}
              >
                {item.name}
              </div>
            </NavLink>
          );
        })}
      </div>
      <main className={cx("children-sidebar")}>
        <div className={cx("children-sidebar-inner")}>{children}</div>
      </main>
    </div>
  );
}

export default SidebarAdmin;
