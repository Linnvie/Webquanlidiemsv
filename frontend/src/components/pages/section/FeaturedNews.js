import styles from "../section/FeaturedNews.scss";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import featurednews from '../../../assets/FeaturedNewImg/bonhiem.jpg'
import classNames from "classnames/bind";
const cx = classNames.bind(styles);



const FeaturedNews = () => {
  let settings = {
    dots: false,
    infinite: true,
    speed: 500,
    slidesToShow: 4,
    slidesToScroll: 1,
  };

  return (
    <div className= {cx("section-share section-featurednews")}>
      <div className= {cx("section-container")}>
        <div className= {cx("section-header")}>
          <span className={cx("title-section")}>Tin tức nổi bật</span>
          <button className={cx("btn-section")}>Xem thêm</button>
        </div>
        <div className= {cx("section-body")}>

        <Slider {...settings}>
          <div className= {cx("featurednews-customize")}>
            <div className="bg-image section-featurednews" />
            <div>Giải thưởng 1</div>
          </div>
          <div className= {cx("featurednews-customize")}>
            <div className="bg-image section-featurednews" />
            <div>Giải thưởng 2</div>
          </div>
          <div className= {cx("featurednews-customize")}>
            <div className="bg-image section-featurednews" />
            <div>Giải thưởng 3</div>
          </div>
          <div className= {cx("featurednews-customize")}>
            <div className="bg-image section-featurednews" />
            <div>Giải thưởng 4</div>
          </div>
          <div className= {cx("featurednews-customize")}>
            <div className="bg-image section-featurednews" />
            <div>Giải thưởng 5</div>
          </div>
          <div className= {cx("featurednews-customize")}>
            <div className="bg-image section-featurednews" />
            <div>Giải thưởng 6</div>
          </div>
          
        </Slider>
        </div>
      </div>
    </div>
  );
};

export { FeaturedNews };
