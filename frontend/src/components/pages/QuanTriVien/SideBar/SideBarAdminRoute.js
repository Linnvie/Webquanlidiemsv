import Dashboard from "../../Dashboard";
import { Route, Routes } from "react-router-dom";
import SidebarAdmin from "../SideBar/SideBarAdmin"
import { SinhVienManage } from "../../../../container/SinhVienManage";
import {TimKiemSVTheoLopTinChi} from "../ManageLopTinChi/TimKiemSVTheoLopTinChi"
import {TimKiemSVTheoLop} from "../ManageLop/TimKiemSVTheoLop"
import {SinhVienByLop} from "../ManageStudent/SinhVienByLop"
import {Mon} from "../ManageMon/Mon"
import {ManageGiangVien} from "../ManageGiangVien/ManageGiangVien"
import {DiemByLTC} from "../ManageDiem/Diem"
import InforAdmin from "./InforAdmin"

const SideBarAdminRoute=()=>{
  return (
    <div>
      <SidebarAdmin>
      <Routes>     
          <Route  path='/dashboard' element={<Dashboard/>}/> 
          <Route  path='/ltc' element={<TimKiemSVTheoLopTinChi/>}/>
          <Route  path='/lop' element={<TimKiemSVTheoLop/>}/>
          <Route  path='/student/:maLTC' element={<SinhVienManage/>}/>
          <Route  path='/nhap-diem/:maLTC' element={< DiemByLTC/>}/>
          <Route  path='/student/lop/:maLop' element={<SinhVienByLop/>}/>
          <Route  path='/mon' element={<Mon/>}/>
          <Route  path='/giangvien' element={<ManageGiangVien/>}/>
          <Route  path='/admin' element={<InforAdmin/>}/>
        </Routes>  
      </SidebarAdmin>
    </div>
  );
}

export default SideBarAdminRoute;