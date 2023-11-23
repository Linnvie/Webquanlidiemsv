#Web quan lis diem sv

Đề tài này sử dụng các công nghệ, kỹ thuật và quy trình sau để tiến hành nghiên cứu và xây dựng ứng dụng web:
Kiến trúc: Sử dụng mô hình RESTful API.
Backend: Ngôn ngữ lập trình Java, framework springboot
Frontend: Ngôn ngữ lập trình Javascript, framework react
Database: Hệ quản trị cơ sở dữ liệu PostgeSQL

3)	Áp dụng kiến thức thiết kế và xây dựng các RESTful API cho việc quản lí điểm sinh viên theo hình thức lớp tín chỉ ở các trường cao đẳng, đại học với các chức năng chính:
-	Đăng nhập, phân quyền người dùng
-	Quản trị viên:
	Quản lí sinh viên theo lớp, lớp tín chỉ.
	Quản lí tài khoản.
	Quản lí môn học.
	Quản lí lớp tín chỉ.
	Quản lí bảng điểm của từng sinh viên theo lớp tín chỉ.
-	Sinh viên:
	Xem thông tin sinh viên, thông tin về điểm thành phần, lớp tín chỉ, kết quả học tập, điểm trung bình, ... theo từng môn, từng học kì.
	Xem bảng điểm tổng qua các học kì theo năm của mình.
	Tìm kiếm bảng điểm của học kì và năm bất kì.
	Quản lí tài khoản
-	Giảng viên:
	Xem thông tin giảng viên, thông tin sinh viên theo lớp tín chỉ được phân công
	Quản lí tài khoản




1.1.	Trang sinh viên-Quyền STUDENT
	Hiển thị thông tin chi tiết sinh viên và thông tin khóa học tại trường
	Hiển thị bảng điểm theo học kì tất cả các môn đã đăng kí
	Bảng điểm gồm điểm thành phần chi tiết, hệ số từng môn, kết quả hệ 4, hệ 10, quy ra điểm chữ 
	Mỗi học kì sẽ được tính trung bình điểm theo kì hệ 4, hệ 10, số tín chỉ đạt và điểm trung bình tích lũy từ các kì đầu đến kì hiện tại theo hệ 4, hệ 10, số tín chỉ đạt
 
Hình 4.1.Màn hình trang xem điểm sinh viên
1.2.	Trang giảng viên – Quyền TEACHER

Trang thông tin cá nhân: Hiển thị thông tin chi tiết về giảng viên đang đăng nhập
 
Hình 4.2.Màn hình trang xem thông tin cá nhân giảng viên
Trang quản lí lớp tín chỉ: Hiển thị các lớp tín chỉ giảng viên đang được phân công dạy ở học kì và năm hiện tại, mỗi lớp tín chỉ ở cột thao tác có icon để mở ra danh sách sinh viên của lớp tín chỉ đó để phực vụ việc điểm danh
 
Hình 4.3.Màn hình trang xem danh sách lớp tín chỉ giảng viên giảng dạy
Trang danh sách sinh viên sẽ có phân trang phía dưới cùng (Khi bấm vào icon danh sách sinh viên ứng với mỗi lớp tín chỉ): Hiển thị thông tin cơ bản của sinh viên nhằm phục vụ việc điểm danh
 
Hình 4.4.Màn hình trang xem danh sách sinh viên lớp tín chỉ giảng viên giảng dạy
1.3.	Trang quản trị-Quyền ADMIN

Trang thông tin cá nhân: Hiển thị thông tin cá nhân của quản trị viên đang đăng nhập
 
Hình 4.5.Màn hình trang xem thông tin cá nhân của quản trị viên





Bấm vào icon môn học trên sidebar: Trang môn học hiển thị tất cả các môn học ở trường, có phân trang, mỗi dòng hiển thị thông tin môn học ở cột thao tác có icon chỉnh sửa  để  chỉnh sửa môn học 
 
Hình 4.6.Màn hình trang quản lí môn học
Nhập mã môn hoặc tên môn vào ô tìm kiếm và bấm nút sẽ hiển thị ra danh sách môn có mã hoặc tên chứa từ khóa tìm kiếm (có phân trang). Bấm thêm mới sẽ hiển thị modal thêm  mới môn, bấm icon edit sẽ hiển thị modal chỉnh sửa môn
 
Hình 4.7.Màn hình modal thêm mới môn học

 
Hình 4.8.Màn hình modal chỉnh sửa thông tin môn học

Trang quản lí lớp tín chỉ hiển thị tất cả các lớp tín chỉ ở học kì và năm hiện tại(có phân trang), mỗi dòng thông tin lớp tín chỉ ở cột thao tác có icon chỉnh sửa, icon để mở ra danh sách sinh viên của lớp tín chỉ đó(có phân trang) , icon mở ra bảng điểm tất cả sinh viên của lớp tín chỉ đó (có phân trang)
 
Hình 4.9.Màn hình trang quản  lí lớp tín chỉ

Nhập mã lớp tín chỉ vào ô tìm kiếm và bấm nút Tìm kiếm theo mã hoặc chọn môn trong list các môn học của trường và bấm nút Tìm kiếm sẽ hiển thị ra danh sách lớp tín chỉ tương ứng với điều kiện (có phân trang). Bấm thêm mới sẽ hiển thị modal thêm  mới môn, bấm icon edit sẽ hiển thị modal chỉnh sửa lớp tín chỉ, trong đó riêng phần chọn giảng viên cho lớp tín chỉ có thể chọn nhiều, nếu chọn sai bấm dấu x để hủy trước khi bấm Thêm mới


 
Hình 4.10.Màn hình modal thêm mới lớp tín chỉ
 
Hình 4.11.Màn hình chỉ tiết mục chọn giảng viên cho lớp tín chỉ
 
Hình 4.12.Màn hình modal chỉnh sửa thông tin lớp tín chỉ

Bấm vào icon thứ 2 ở Màn hình trang quản  lí lớp tín chỉ ứng với mỗi lớp tín chỉ sẽ hiển thị danh sách thông tin của tất cả sinh viên đăng kí lớp tín chỉ đó
 
Hình 4.13.Màn hình xem danh sách sinh viên theo lớp tín chỉ













Bấm vào icon thứ 3 ở Màn hình trang quản  lí lớp tín chỉ ứng với mỗi lớp tín chỉ sẽ hiển thị danh sách bảng điểm của từng sinh viên theo lớp tín chỉ đó (có phân trang), phía trên là thông tin về lớp tín chỉ bao gồm hệ số để người nhập dễ dàng đối chiếu nhập điểm theo hệ số
 
Hình 4.14.Màn hình xem danh sách bảng điểm sinh viên theo lớp tín chỉ

Ở trang bảng điểm này, bấm vào icon chỉnh sửa ở cột thao tác của mỗi bảng điểm tương ứng với mỗi sinh viên sẽ hiển thị modal để nhập điểm, quản trị viên cần nhập điểm các hệ tương ứng với bảng thông tin hệ số của môn được show ở phía trên
 
Hình 4.15.Màn hình nhập điểm cho sinh viên

Trang quản lí lớp hiển thị tất cả các lớp học ở trường, có phân trang, mỗi dòng hiển thị thông tin lớp học ở cột thao tác có icon chỉnh sửa  để  chỉnh sửa thông tin lớp học, icon danh sách để xem danh sách sinh viên của lớp tương ứng
 
Hình 4.16.Màn hình trang quản lí lớp học
 

Nhập mã lớp hoặc tên lớp vào ô tìm kiếm và bấm nút sẽ hiển thị ra danh sách lớp có mã hoặc tên chứa từ khóa tìm kiếm (có phân trang). Bấm thêm mới sẽ hiển thị modal thêm  mới môn, bấm icon edit sẽ hiển thị modal chỉnh sửa môn

 
Hình 4.17.Màn hình modal thêm mới lớp học

Bấm vào icon danh sách sinh viên của lớp sẽ hiển thị trang quản lí sinh viên của lớp đó(có phân trang), mỗi dòng tương ứng với thông tin mỗi sinh viên của lớp 
 
Hình 4.18.Màn hình trang quản lí danh sách sinh viên theo lớp
Bấm thêm mới sinh viên sẽ hiển thị modal thêm  mới sinh viên, bấm icon edit sẽ hiển thị modal chỉnh sửa thông tin sinh viên 
Hình 4.19.Màn hình modal tạo mới sinh viên
- 
Hình 4.20.Màn hình modal chỉnh sửa thông tin sinh viên

Trang quản lí giảng viên hiển thị tất cả các giảng viên đã vã đang giảng dạy ở trường, có phân trang, mỗi dòng hiển thị thông tin giảng viên ở cột thao tác có icon chỉnh sửa  để  chỉnh sửa thông tin giảng viên
 
Hình 4.21.Màn hình trang quản lí giảng viên
Nhập mã giảng viên hoặc tên giảng viên vào ô tìm kiếm và bấm nút Tìm kiếm sẽ hiển thị ra danh sách lớp có mã hoặc tên chứa từ khóa tìm kiếm. Ở ô filter chọn các option tương ứng sẽ hiện ra danh sách các giảng viên có thông tin tương ứng. Bấm thêm mới sẽ hiển thị modal thêm  mới giảng viên, bấm icon edit sẽ hiển thị modal chỉnh sửa giảng viên
 
Hình 4.22.Chi tiết filter trạng thái giảng viên
 
Hình 4.23.Màn hình modal thêm mới giảng viên
 

 
Hình 4.24.Màn hình modal chỉnh sửa thông tin giảng viên


1.4.	Trang chung
Login: Đăng nhập tài khoản (trang hiển thị đầu tiên khi truy cập vào web)

 
Hình 4.25.Màn hình trang đăng nhập

Quên mật khẩu: Bấm vào dòng chữ Quên mật khẩu ở trang login

 
Hình 4.26.Màn hình modal quên mật khẩu

Đổi mật khẩu: Button Đổi mật khẩu đều nằm ở trang thông tin cá nhân của mỗi tài khoản
 
Hình 4.27.Màn hình modal đổi mật khẩu
Xử lí thông báo chung

Hiện alert màu xanh lá đầu trang và biến mất sau 3s khi gọi api trả về thành công (200 và 201) 

 
Hình 4.28.Chi tiết alert thông báo thành công tác vụ
Hiện alert màu đỏ đầu trang và biến mất sau 3s khi gọi api trả về lỗi và khi validate form dữ liệu trước khi gửi dữ liệu vào các api CRUD
 
Hình 4.29.Chi tiết alert thông báo lỗi


Trang chủ chung hiển thị các thông báo của trường

Hình 4.30.Màn hình trang thông báo

