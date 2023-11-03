import styles from "../BangDiem/BangDiem.module.scss";
import classNames from "classnames/bind";
import { Alert } from 'antd';

const cx = classNames.bind(styles);
function AlertCustom(props) {
  
    return (
        <div className={cx("alert-container")}>
       <Alert 
            message={props.item.title}
            description={props.item.message}
            type={props.item.type}
            showIcon
            closeIcon
            afterClose={setTimeout(() => {        
                    props.onClose()
                  }, 3000)}
            />
            </div>

    )};

export {AlertCustom}