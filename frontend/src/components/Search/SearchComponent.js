// import React, { useState } from 'react';

// const SearchComponent = ({ onSearch }) => {
//   const [searchTerm, setSearchTerm] = useState('');

//   const handleChange = (e) => {
//     setSearchTerm(e.target.value);
//     // Gọi hàm tìm kiếm mỗi khi giá trị nhập vào thay đổi
//     onSearch(e.target.value);
//   };

//   return (
//     <input
//       type="text"
//       value={searchTerm}
//       onChange={handleChange}
//       placeholder="Nhập từ khóa tìm kiếm"
//     />
//   );
// };

// export default SearchComponent;



import React, { useState } from 'react';
import './SearchComponent.scss';



const SearchComponent = ({ onSearch }) => {
  const [searchTerm, setSearchTerm] = useState('');

  const handleChange = (e) => {
    setSearchTerm(e.target.value);
  };

  const handleSearchClick = () => {
    onSearch(searchTerm);
  };

  return (
    <div className="search-container">
      <input 
        type="text"
        value={searchTerm}
        onChange={handleChange}
        placeholder="Nhập từ khóa tìm kiếm"
      />
      <button onClick={handleSearchClick}>Tìm kiếm</button>
    </div>
  );
};

export default SearchComponent;


