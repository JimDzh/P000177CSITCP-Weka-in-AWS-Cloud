import React from 'react';
import './Pages/style.css';
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";

// Navbar component
const Navbar = () => {
  return (
    <div>
        <div className="left-nav">
            <img className="rmit-logo"
                src="https://keypath.uk.com/sites/uk/files/styles/half_width_600w_/public/image/2020-07/Rmit-White.png?itok=hUXxaWnz"
                alt="RMIT's logo" />
            <img className="weka-logo"
                src="https://dashboard.snapcraft.io/site_media/appmedia/2021/10/weka.png"
                alt="WEKA's logo" />
        </div>
        <div className="right-nav">
            <nav>
                <ul>
                    <li><a href="/">Home</a></li>
                    <li><a href="/preprocess">Preprocess</a></li>
                    <li><a href="/classify">Classify</a></li>
                    <li><a href="/cluster">Cluster</a></li>
                    <li><a href="/associate">Associate</a></li>
                    <li><a href="/select-attributes">Select attributes</a></li>
                    <li><a href="/visualise">Visualise</a></li>
                    <li><a href="/help">Help</a></li>
                </ul>
            </nav>
        </div>
    </div>
  );
}

export default Navbar;