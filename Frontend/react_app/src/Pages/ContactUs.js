import React from 'react';
import './contact-style.css';

// ContactUS page component
const ContactUs = () => {
    return(
        <div className="contact">
            <div className="container">
            <h1> Contact Us</h1>
            <p> Get in touch with us!</p>
                <div className="contact-box">
                    <div className="contact-left">
                            <h3>Send your message</h3>
                            <form>
                                <div className="input-row">
                                    <div className="input-group">
                                        <label> Name </label>
                                        <input type="text" id="name" name="userName" placeholder="Your name.."  />
                                    </div>
                                    <div className="input-group">
                                        <label> Email </label>
                                        <input type="text" id="email" name="userEmail" placeholder="Your email.." />
                                    </div>
                                </div>
                                <label> Message </label>
                                <textarea rows="5" placeholder="your message"></textarea>
                                <input type="submit" value="Submit" />
                            </form>
                    </div>
                    <div className="contact-right">
                            <h3>Our details</h3>
                            <table>
                                <tr>
                                    <td>EMAIL:</td>
                                    <td>weka.rmit@rmit.com</td>
                                </tr>
                                <tr>
                                    <td>PHONE:</td>
                                    <td>+61 123 456 789</td>
                                </tr>
                                <tr>
                                    <td>ADDRESS:</td>
                                    <td>124 La Trobe St, Melbourne VIC 3000</td>
                                </tr>
                            </table>
                    </div>
                </div>
            </div>
            <h2 style={{textAlign: "center",marginBottom: "16px"}}>Meet Our Team!</h2><br/><br/>
            <div style={{textAlign: "center"}}>
                <div style={{float: "left", width: "20%"}}>
                    <h4>Zhihong Deng</h4>
                    <p>s3860863@student.rmit.edu.au <br/> Front-End and Back-End Developer </p>
                </div>

                <div style={{float: "left", width: "20%"}}>
                    <h4>Sai Sreshtaa Turaga</h4>
                    <p>s3814571@student.rmit.edu.au <br/> Front-End and Back-End Developer </p>
                </div>

                <div style={{float: "left", width: "20%"}}>
                    <h4>Christoper Adrianus Sindarto</h4>
                    <p>s3738844@student.rmit.edu.au <br/> Front-End and Back-End Developer</p>
                </div>

                <div style={{float: "left", width: "20%"}}>
                    <h4>Yitao Ma</h4>
                    <p>s3843689@student.rmit.edu.au <br/> Front-End and Back-End Developer</p>

                </div>

                <div style={{float: "left", width: "20%"}}>
                    <h4>Zehu Liu</h4>
                    <p>s3845938@student.rmit.edu.au <br/> Front-End and Back-End Developer </p>
                </div>
            </div>
        </div>
    );
}

export default ContactUs;