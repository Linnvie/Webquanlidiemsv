import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import styles from "../section/Notification.scss";
import classNames from "classnames/bind";

const cx = classNames.bind(styles);



const Notification = () => {
    let settings = {
        dots: false,
        infinite: true,
        speed: 500,
        slidesToShow: 4,
        slidesToScroll: 1,
      };
    return(
        <div className= {cx("section-share section-notification")}>
      <div className= {cx("section-container")}>
        <div className= {cx("section-header")}>
          <span className={cx("title-section")}>Thông báo</span>
          <button className={cx("btn-section")}>Xem thêm</button>
        </div>
        <div className= {cx("section-body")}>

        <Slider {...settings}>
          <div className= {cx("featurednews-customize")}>
            <div className="bg-image section-notification" />
            <div>Hình thức thi, kiểm tra, đánh giá kết quả học tập theo phương thức đào tạo tín chỉ</div>
          </div>
          <div className= {cx("featurednews-customize")}>
            <div className="bg-image section-notification" />
            <div>Thông báo cấp bằng tốt nghiệp</div>
          </div>
          <div className= {cx("featurednews-customize")}>
            <div className="bg-image section-notification" />
            <div>10 sinh viên xuất sắc của Học viện Công nghệ Bưu chính Viễn thông đã được chọn để tham gia chương trình gPBL</div>
          </div>
          <div className= {cx("featurednews-customize")}>
            <div className="bg-image section-notification" />
            <div>Hội trại thanh niên Hội báo toàn quốc là sự kiện thường niên bắt đầu từ năm 2018</div>
          </div>
          <div className= {cx("featurednews-customize")}>
            <div className="bg-image section-notification" />
            <div>GeoComply tuyển thực tập sinh CNTT</div>
          </div>
          <div className= {cx("featurednews-customize")}>
            <div className="bg-image section-notification" />
            <div>Các đề tài NCKH sinh viên tiêu biểu trong năm 2022</div>
          </div>
          
        </Slider>
        </div>
      </div>
    </div>
    )
}

export {Notification}