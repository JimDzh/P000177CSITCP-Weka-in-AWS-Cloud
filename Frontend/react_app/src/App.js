import React from 'react';
import ReactDOM from "react-dom/client";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import './Pages/style.css';

import About from "./Pages/About";
import Home from "./Pages/Home";
import Error from "./Pages/Error"
import Navbar from "./Navbar";
import Footer from "./Footer";
import Classify from "./Pages/Classify";
import Preprocess from "./Pages/Preprocess";

// Main component
const App = () => {
  return (
    <div className='routes'>
        <Router>
          <Navbar />
          <Routes>
            <Route path='/' element={<Home />} />
            <Route path='/preprocess' element={<Preprocess/>} />
            <Route path='/classify' element={<Classify />} />
            <Route path='/cluster' element={<Cluster />} />
            <Route path='/about' element={<About />} />
            <Route path='/help' element={<Help />} />
            <Route path='*' element={<Error />} />
          </Routes>
          <Footer />
        </Router>
    </div>
  );
}

export default App;
