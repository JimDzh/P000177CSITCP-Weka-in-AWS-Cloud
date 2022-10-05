import React, {useEffect, useState} from 'react';
import axios from 'axios';
import video from '../Videos/weka_video.mp4';
import './style.css';
// import {useCookies} from "react-cookie";

// Home component
const Home = () => {

    const [file, setFile] = useState('');
    const [fileName, setFileName] = useState("");
    const [content, setContent] = useState(null);
    // const [name, setName] = useState(getFileName('content', null));

    //base end point url
    const FILE_UPLOAD_BASE_ENDPOINT = "http://localhost:8081/api/load-data/";

    // useEffect(()=>{
    //     if(content !== null) {
    //         setCookie('name', fileName, {path: '/'});
    //     }
    //     // console.log("name: ", cookies.name)
    //     // if(cookies.name !== "") {
    //     //     pageData();
    //     // }
    // },[content])

    // useEffect(()=>{
    //     // console.log(getFileName('name', false));
    //     // const stored = sessionStorage.getItem('content');
    //     // if(stored) {
    //     //     console.log(JSON.parse(stored).props[0]);
    //     // }
    //     let content = getFileName('content', false);
    //     if(content) {
    //         console.log(content);
    //     }
    // },[]);
    //
    // useEffect(()=>{
    //     // sessionStorage.setItem('name', JSON.stringify(name));
    //     sessionStorage.setItem('content', JSON.stringify(content));
    // },[content]);

    // function getFileName(key, defaultValue) {
    //     const stored = sessionStorage.getItem(key);
    //     if (!stored) {
    //         return defaultValue;
    //     }
    //     return JSON.parse(stored);
    // }

    const uploadFileHandler = (event) => {
        setFile(event.target.files[0]);
        setFileName(event.target.files[0].name);
        // setCookie('name', fileName, {path: '/'});
        // setName(event.target.files[0].name);
    };

    const fileSubmitHandler = (event) => {
        event.preventDefault();
        setContent(null);
        const formData = new FormData();
        formData.append(`file`, file);

        const requestOptions = {
            method: 'POST',
            body: formData
        };

        let link = "http://localhost:8082/api/classify/setFilename?fileName=" + fileName;
        axios.post(link).then(
            r => {
                console.log(r.status);
            }
        );

        link = "http://localhost:8084/api/cluster/setFilename?fileName=" + fileName;
        axios.post(link).then(
            r => {
                console.log(r.status);
            }
        );

        link = "http://localhost:8083/api/filter/setFilename?fileName=" + fileName;
        axios.post(link).then(
            r => {
                console.log(r.status);
            }
        );

        // setCookie('name', fileName, {path: '/'});

        // axios.post("http://localhost:8081/api/load-data/uploadFile", selectedFile);
        fetch(FILE_UPLOAD_BASE_ENDPOINT + 'uploadFile', requestOptions)
            .then(r  => {
                if(r.ok) {
                    pageData();
                }
            });
    };


    const pageData = () => {
        let link = "http://localhost:8081/api/load-data/getDataSummary?fileName=" + fileName;
        // console.log("Link: " + link);
        let summ = null;
        axios.get(link)
            .then(res => {
                summ = res.data;
                // console.log(summ);
                let rows = Array.prototype.slice.call(summ);
                let final = [];
                let header = ["ID", "AttributeName", "Type", "Nominal", "Integer", "Real", "MissingValues", "UniqueValues", "DistinctValues"];
                let info = [];
                for(let i=0; i < rows.length;i++) {
                    if (i > 4) {
                        let data = rows[i];
                        // console.log(data)
                        let data_array = data.split(" ");
                        for(let j=0; j < data_array.length;j++) {
                            if(data_array[j] === "" || data_array[j] === " ") {
                                delete data_array[j];
                            }
                        }
                        final.push(data_array);
                    }
                    else if (i < 3) {
                        if (rows[i] !== "") {
                            info.push(rows[i]);
                        }
                    }
                }

                if(fileName !== "") {
                    setContent(
                        <div>
                            <h3><b>The file '{fileName}' has been successfully uploaded!</b></h3>
                            <br/><br/><br/>
                            {
                                info.map((i) => (
                                    <h3 key={i}><b>{i}</b></h3>
                                ))
                            }
                            <br/><br/>
                            <div className="data-table">
                                <table>
                                    <tr>
                                        {
                                            header.map((i) => (
                                                <th key={i}> {i} </th>
                                            ))
                                        }
                                    </tr>
                                    {
                                        final.map((i) => (
                                            <tr>
                                                {
                                                    i.map((j) => (
                                                        <td key={j}>{j}</td>
                                                    ))
                                                }
                                            </tr>
                                        ))
                                    }
                                </table>
                            </div>
                        </div>
                    );
                }
            })
    }


    return (
          <div className="grid-container-zzz">
              <section className="video-sect">
                  <div className="video-content">
                      <h1 id="text" style={{fontSize: "40px"}}><b>WEKA IS NOW IN CLOUD</b></h1>
                  </div>
                  <div className="video">
                      <video autoPlay muted loop id="video">
                          <source src={video}
                                  type="video/mp4" />
                          {/*alt text*/}
                          The web browser cannot support mp4
                      </video>
                  </div>
              </section>
              <br/>
              <br/>
              <br/>
              <br/>
              <section className="file-upload">
                  <h2><b>Start by uploading a database file</b></h2>
                  <form className="file-upload-form" onSubmit={fileSubmitHandler}>
                      <input type="file" className="selectFile" accept=".arff,.arff.gz,.names,.data,.csv,.json,.json.gz,
                      .libsvm,.m,.dat,.bsi,.xrff,.xrff.gz" onChange={uploadFileHandler} />
                      <br /><br />
                      <button className="uploadFile" type='submit' >Upload File</button>
                  </form>
                  {/* If content exists, show it */}
                  {content ? (
                      <div className="data-summary">
                          {content}
                      </div>
                  ):null}
                  {/*{cookies.name ? (*/}
                  {/*    <h3><br/><br/><b>You can now check your data summary in the 'Visualise' page</b></h3>*/}
                  {/*) : null}*/}
              </section>
          </div>
    );

}

export default Home;