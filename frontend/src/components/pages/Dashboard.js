import React from "react";
import { FeaturedNews } from "./section/FeaturedNews";
import { Notification } from "./section/Notification";
import styles from "../pages/Dashboard.scss";
import classNames from "classnames/bind";
import { About } from "./section/About";
const cx = classNames.bind(styles);
function Dashboard() {
    return ( 
    
        <div>
            <FeaturedNews/>
            <Notification/>
            <About/>
        </div>

    );
}

export default Dashboard;