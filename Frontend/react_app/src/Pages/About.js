import React from 'react';
import './style.css';

// About component
const About = () => {
  return (
    <div>
        <div className="about-weka">
                <img className="background" src="https://images.pexels.com/photos/7311920/pexels-photo-7311920.jpeg?cs=srgb&dl=pexels-alberlan-barros-7311920.jpg&fm=jpg" alt="" />
                <span className="weka-intro ">Waikato Environment for Knowledge Analysis (Weka), developed at the University of Waikato, New Zealand, is free software licensed under the GNU General Public License, and the companion software to the book "Data Mining: Practical Machine Learning Tools and Techniques".
                </span>
        </div>

        <div className="more">
            <div className="box">
                <div className="content">
                    <h1>History</h1>
                    <p>In 1993, the University of Waikato in New Zealand began development of the original version of Weka.
                        In 1997, the decision was made to redevelop Weka from scratch in Java, including implementations of modeling algorithms.
                        In 2005, Weka received the SIGKDD Data Mining and Knowledge Discovery Service Award.
                        In 2006, Pentaho Corporation acquired an exclusive licence to use Weka for business intelligence.</p>
                </div>
            </div>

            <div className="box2">
                <div className="content2">
                    <h1>Advantages</h1>
                    <p>  Free availability under the GNU General Public License. Portability, since it is fully implemented in the Java programming language and thus runs on almost any modern computing platform. A comprehensive collection of data preprocessing and modeling techniques. Ease of use due to its graphical user interfaces.</p>
                </div>
            </div>

            <div className="box3">
                <div className="content">
                    <h1>Weka in Cloud</h1>
                    <p> RMIT RACE hosted the WEKA in AWS but needs to have an interface that can be accessed via different kinds of browsers to manipulate WEKA. Therefore, The Weka in Cloud which mean in such a way that users from different study backgrounds, not only technology can access WEKA for their courses through their preferred browser. Users should find it easy to manipulate and use WEKA for their research and study. </p>
                </div>
            </div>
        </div>
    </div>
  );
}

export default About;