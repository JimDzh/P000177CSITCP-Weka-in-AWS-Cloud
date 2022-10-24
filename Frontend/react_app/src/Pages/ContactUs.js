import React from 'react';
import './contact-style.css';

// ContactUS page component
const ContactUs = () => {
    return(
        <div className="contact">
            <div className="container">
                <form action="">
                    <label>
                        Name
                        <input type="text" id="name" name="userName" placeholder="Your name.."  />
                    </label>
                    <br/>
                    <label>
                        Email
                        <input type="text" id="email" name="userEmail" placeholder="Your email.." />
                    </label>
                    <br/>
                    <label>
                        Subject
                        <textarea id="message" name="userMessage" placeholder="Write something.." style={{height: "200px"}}></textarea>
                    </label>
                    <br/>
                    <input type="submit" value="Submit" />
                </form>
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
                <br/><br/>
            </div>
        </div>
    );
}

export default ContactUs;