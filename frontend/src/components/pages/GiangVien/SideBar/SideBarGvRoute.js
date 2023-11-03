import Dashboard from "../../Dashboard";
import Demo from "../../demo";
import { Route, Routes } from "react-router-dom";
import BangDiem from "../../../BangDiem/BangDiem";
import Login from '../../Login/Login'
import { SinhVienManage } from "../../../../container/SinhVienManage";
import SidebarGv from "./SideBarGv";
import Infor from "../Manage/Infor";
import {ListLopTinChi} from "../Manage/ListLopTinChi";
import {ListStudent} from "../Manage/ListStudent";

const SideBarGvRoute=()=>{
  return (
    <div>
      <SidebarGv>
      <Routes>     
          <Route  path='/dashboard' element={<Dashboard/>}/> 
          <Route  path='/infor' element={<Infor/>}/>
          <Route  path='/dsLTC' element={<ListLopTinChi/>}/>      
          <Route  path='/dssv/:maLTC' element={< ListStudent/>}/>
        </Routes>  
      </SidebarGv>
    </div>
  );
}

export default SideBarGvRoute;