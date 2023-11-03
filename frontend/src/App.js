import "bootstrap/dist/css/bootstrap.min.css";
import Header from "./components/Header";
import Home from "./components/Home/Home";

import BangDiem from "./components/BangDiem/BangDiem";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Dashboard from "./components/pages/Dashboard";
import ProtectedRouter from "./components/pages/router/ProtectedRouter";
import SideBarRoute from "./components/pages/SinhVien/Sidebar/SideBarRoute";
import SideBarAdminRoute from "./components/pages/QuanTriVien/SideBar/SideBarAdminRoute";
import SideBarGvRoute from "./components/pages/GiangVien/SideBar/SideBarGvRoute";
import { useSelector } from "react-redux";
import { selectUser } from "./redux/useSlices";
import Login from "./components/pages/Login/Login";
import { useEffect } from "react";
import {
  userIsAuthenticated,
  userIsNotAuthenticated,
} from "./components/Auth/authentication";
function App() {
  const user = useSelector(selectUser)
  console.log("log")
  console.log(user)
  let renderComponent;
  if (user) {
    if (user.role === "ADMIN") {
          renderComponent = <SideBarAdminRoute />;
        } else if (user.role === "STUDENT") {
          renderComponent =<SideBarRoute />;
    } else if (user.role === "TEACHER") {
        renderComponent =<SideBarGvRoute />;
    }
    }else {
        renderComponent = <Login />;}
      


  return (
    <div className="App">
      {/* <Router >
      {user ? <SideBarAdminRoute/> : <Login/> }  */}
         {/* <div>
            
            <Routes> 
            
             <Route path="/login" element={<Login />} /> 
       <Route path="/*" element={<SideBarRoute />} /> 

      </Routes> 

         </div> */}
       {/* </Router> */}

       <Router>{renderComponent}</Router>

      
    </div>
  );
}

export default App;



{/* <Router>
<Routes>
  {user ? (
    // Nếu user đã đăng nhập
    <>
      {user.role === 'STUDENT' && (
        <SideBarRoute/>
      )}
      {user.role === 'ADMIN' && (
        <SideBarAdminRoute/>
      )}
      {user.role === 'TEACHER' && (
       <SideBarGvRoute/>
      )}
    </>
  ) : (
    // Nếu user chưa đăng nhập
    <Login/>
  )}
</Routes>
</Router> */}