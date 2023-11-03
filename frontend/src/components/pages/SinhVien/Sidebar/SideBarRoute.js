import Dashboard from "../../Dashboard";
import { Route, Routes } from "react-router-dom";
import Sidebar from "../../SinhVien/Sidebar/Sidebar";
import BangDiem from "../../../../components/BangDiem/BangDiem";

const SideBarRoute=()=>{
  return (
    <div>
      <Sidebar>
      <Routes>     
          <Route  path='/dashboard' element={<Dashboard/>}/>  
          <Route  path='/bangdiem' element={<BangDiem/>}/>
        </Routes>  
      </Sidebar>
    </div>
  );
}

export default SideBarRoute;