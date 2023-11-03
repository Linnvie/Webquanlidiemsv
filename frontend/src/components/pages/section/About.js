import styles from "../section/Notification.scss";
import classNames from "classnames/bind";
import {AiOutlineHome} from 'react-icons/ai'

const cx = classNames.bind(styles);
const About = () => {
    
    return(
        <div className= {cx("section-share section-about")}>
           <div className={cx("section-content")}>
                <div className={cx("content-left")}>
                    <iframe width="100%" height="400px" src="https://www.youtube.com/embed/Jf85bs9NFCU" title="Học Viện Công Nghệ Bưu Chính Viễn Thông PTIT Có Gì Hot? | Review Trường ĐH #28 | SuperTeo" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
                </div> 
                <div className={cx("content-right")}>
                    
                    <h5>Học viện Công nghệ Bưu chính Viễn thông – Cơ sở tại TP. Hồ Chí Minh</h5>
                    <div className={cx("home-about-right")}>
                        <span className={cx('home-icon')}><AiOutlineHome/></span>
                        <p>Cơ sở Quận 1: 11 Nguyễn Đình Chiểu, Phường Đa Kao, Quận 1, TP. Hồ Chí Minh</p>
                    </div>
                    <div className={cx("home-about-right")}>
                        <span className={cx('home-icon')}><AiOutlineHome/></span>
                        <p>Cơ sở Thủ Đức: 97 Man Thiện, Phường Hiệp Phú, Thủ Đức, TP. Hồ Chí Minh</p>
                    </div>
                </div>
            </div> 
        </div>
    )
}

export {About}