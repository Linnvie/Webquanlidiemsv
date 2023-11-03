import axios from "axios";
import authHeader from "./auth-header.service";

const path= "http://localhost:8089/api/v1"

const HandleLoginAPI = async (userName, userPassword) => {
  return await axios.post(path + "/auth/login", {
    username: userName,
    password: userPassword,
  });
};

const GetDiemService = (mssv) => {
  return axios.get(path + `/diem/${mssv}`,
  {
    headers: {
      Authorization: `Bearer ${localStorage.getItem('accessToken')}`
    }

    });
};

const GetSinhVienByMSSV = (mssv) => {
  return axios.get(`${path}/sinhvien?mssv=${mssv}`, {
    headers: {
      Authorization: `Bearer ${localStorage.getItem('accessToken')}`
    }

    });
};

const GetQTVByMaQTV = (maQTV) => {
  return axios.get(`${path}/quantrivien?maQTV=${maQTV}`,
  {
    headers: {
      Authorization: `Bearer ${localStorage.getItem('accessToken')}`
    }

    });
};

const GetGiangVienByMaGV = (maGV) => {
  return axios.get(`${path}/giangvien?maGV=${maGV}`,
  {
    headers: {
      Authorization: `Bearer ${localStorage.getItem('accessToken')}`
    },

    })
};


const GetLTCByMaLTC = (maLTC) => {

  return axios.get(`${path}/lop-tin-chi/one?maLTC=${maLTC}`,
  {
    headers: {
      Authorization: `Bearer ${localStorage.getItem('accessToken')}`
    }

    });
};

const GetLTCByMaGVHocKiNam = (maGV, hocKi, nam) => {
  return axios.get(`${path}/lop-tin-chi/giangvien?maGV=${maGV}&hocKi=${hocKi}&nam=2021`,
  {
    headers: {
      Authorization: `Bearer ${localStorage.getItem('accessToken')}`
    }

    });
};

const SearchByKeyWordLTC = (keyWord, paginate) => {
  return axios.get(`${path}/lop-tin-chi/search?keyWord=${keyWord}&currentPage=${paginate.currentPage}&perPage=${paginate.perPage}`,
  {
    headers: {
      Authorization: `Bearer ${localStorage.getItem('accessToken')}`
    }

    });
};

const SearchByKeyWordLop = (keyWord, paginate) => {
  return axios.get(`${path}/lop/search?keyWord=${keyWord}&currentPage=${paginate.currentPage}&perPage=${paginate.perPage}`,
  {
    headers: {
      Authorization: `Bearer ${localStorage.getItem('accessToken')}`
    }

    });
};

const SearchByKeyWordMon = (keyWord, paginate) => {
  console.log(paginate)
  return axios.get(`${path}/mon/search?keyWord=${keyWord}&currentPage=${paginate.currentPage}&perPage=${paginate.perPage}`,
  {
    headers: {
      Authorization: `Bearer ${localStorage.getItem('accessToken')}`
    }

    });
};

const SearchByKeyWordGiangVien = (keyWord, paginate) => {
  return axios.get(`${path}/giangvien/search?keyWord=${keyWord}&currentPage=${paginate.currentPage}&perPage=${paginate.perPage}`,
  {
    headers: {
      Authorization: `Bearer ${localStorage.getItem('accessToken')}`
    }

    });
};

const FilterGiangVien = (active, paginate) => {
  return axios.get(`${path}/giangvien/filter?active=${active}&currentPage=${paginate.currentPage}&perPage=${paginate.perPage}`,
  {
    headers: {
      Authorization: `Bearer ${localStorage.getItem('accessToken')}`
    }

    });
};

const GetAllStudentLTC = (maLTC,paginate) => {
  return axios.get(`${path}/sinhvien/loptinchi?maLTC=${maLTC}&currentPage=${paginate.currentPage}&perPage=${paginate.perPage}`,
  {
    headers: {
      Authorization: `Bearer ${localStorage.getItem('accessToken')}`
    }

    });
};

const GetAllLTCByMon = (maMon, paginate) => {
  return axios.get(`${path}/lop-tin-chi-all?maMon=${maMon}&currentPage=${paginate.currentPage}&perPage=${paginate.perPage}`,
  {
    headers: {
      Authorization: `Bearer ${localStorage.getItem('accessToken')}`
    }

    });
};

const GetAllDiemByLTC = (maLTC, paginate) => {
  return axios.get(`${path}/diem/loptinchi?maLTC=${maLTC}&currentPage=${paginate.currentPage}&perPage=${paginate.perPage}`,
  {
    headers: {
      Authorization: `Bearer ${localStorage.getItem('accessToken')}`
    }

    });
};

const GetAllStudentLop = (maLop,paginate) => {
  return axios.get(`${path}/sinhvien/lop?maLop=${maLop}&currentPage=${paginate.currentPage}&perPage=${paginate.perPage}`,
  {
    headers: {
      Authorization: `Bearer ${localStorage.getItem('accessToken')}`
    }

    });
};

const GetAllMon = () => {
  return axios.get(`${path}/allmon`,
  {
    headers: {
      Authorization: `Bearer ${localStorage.getItem('accessToken')}`
    }

    });
};

const GetAllNganh = () => {
  return axios.get(`${path}/allnganh`,
  {
    headers: {
      Authorization: `Bearer ${localStorage.getItem('accessToken')}`
    }

    });
};

const GetAllLop = () => {
  return axios.get(`${path}/alllop`,
  {
    headers: {
      Authorization: `Bearer ${localStorage.getItem('accessToken')}`
    }

    });
};

const GetAllGVActive = () => {
  return axios.get(`${path}/allgiangvien`,
  {
    headers: {
      Authorization: `Bearer ${localStorage.getItem('accessToken')}`
    }

    });
};

const createNewLTCService = async (valueForm) => {
  console.log(valueForm)
  return await axios.post(`${path}/lop-tin-chi`, 
  valueForm,
  {
    headers: {
      Authorization: `Bearer ${localStorage.getItem('accessToken')}`
    }

    }
);
};

const editLTCService = (inputData) => {
  return axios.put(`${path}/lop-tin-chi`, inputData,
  {
    headers: {
      Authorization: `Bearer ${localStorage.getItem('accessToken')}`
    }

    });
}

const editDiemService = (inputData) => {
  return axios.put(`${path}/lop-tin-chi/nhap-diem`, inputData,
  {
    headers: {
      Authorization: `Bearer ${localStorage.getItem('accessToken')}`
    }

    });
}

const ChangePasswordService = (inputData) => {
  return axios.put(`${path}/accountchangePw`, inputData,
  {
    headers: {
      Authorization: `Bearer ${localStorage.getItem('accessToken')}`
    }

    });
}

const ForgetPasswordService = (inputData) => {
  return axios.put(`${path}/accountforgetPw`, inputData,
  {
    headers: {
      Authorization: `Bearer ${localStorage.getItem('accessToken')}`
    }

    });
}

const createNewLopService = async (valueForm) => {
  console.log(valueForm)
  return await axios.post(`${path}/lop`, 
  valueForm,
  {
    headers: {
      Authorization: `Bearer ${localStorage.getItem('accessToken')}`
    }

    }
);
};

const editLopService = (inputData) => {
  return axios.put(`${path}/lop`, inputData,
  {
    headers: {
      Authorization: `Bearer ${localStorage.getItem('accessToken')}`
    }

    });
}

const createNewSVService = async (valueForm) => {
    console.log(valueForm)
  return await axios.post(`${path}/accountSV`, 
    valueForm,
    {
      headers: {
        Authorization: `Bearer ${localStorage.getItem('accessToken')}`
      }
  
      }
  );
};

const createNewGVService = async (valueForm) => {
  console.log(valueForm)
return await axios.post(`${path}/accountGV`, 
  valueForm,
  {
    headers: {
      Authorization: `Bearer ${localStorage.getItem('accessToken')}`
    }

    }
);
};

const editMonService = (inputData) => {
  return axios.put(`${path}/mon`, inputData,
  {
    headers: {
      Authorization: `Bearer ${localStorage.getItem('accessToken')}`
    }

    });
}

const editGVService = (inputData) => {
  return axios.put(`${path}/giangvien`, inputData,
  {
    headers: {
      Authorization: `Bearer ${localStorage.getItem('accessToken')}`
    }

    });
}

const createNewMonService = async (valueForm) => {
    console.log(valueForm)
  return await axios.post(`${path}/mon`, 
    valueForm,
    {
      headers: {
        Authorization: `Bearer ${localStorage.getItem('accessToken')}`
      }
  
      }
  );
};

const deleteSVService = (userName) => {
  return axios.delete("http://localhost:4000/api/delete-user", {
    data: {
      username: userName,
    },
  },{
    headers: {
      Authorization: `Bearer ${localStorage.getItem('accessToken')}`
    }

    });
};

const editSVService = (inputData) => {
  return axios.put(`${path}/sinhvien`, inputData,
  {
    headers: {
      Authorization: `Bearer ${localStorage.getItem('accessToken')}`
    }

    });
}



export { HandleLoginAPI, GetAllStudentLTC,
  createNewSVService, deleteSVService,
  editSVService,GetSinhVienByMSSV,GetDiemService, SearchByKeyWordLTC, createNewLTCService, editLTCService,GetAllMon,
  createNewLopService, editLopService,  SearchByKeyWordLop, GetAllStudentLop,
  GetAllLTCByMon,editDiemService, GetAllDiemByLTC,GetAllGVActive,GetLTCByMaLTC,
  createNewMonService, editMonService, SearchByKeyWordMon, SearchByKeyWordGiangVien, FilterGiangVien,
  createNewGVService, editGVService, GetGiangVienByMaGV, GetLTCByMaGVHocKiNam, ChangePasswordService,
  GetQTVByMaQTV, GetAllNganh, GetAllLop,ForgetPasswordService
};
