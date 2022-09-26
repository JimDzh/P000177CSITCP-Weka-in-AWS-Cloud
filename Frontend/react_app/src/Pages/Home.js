import React, {useEffect, useState} from 'react';
import axios from 'axios';
import video from '../Videos/weka_video.mp4';
import './style.css';

// Home component
const Home = () => {

    const [file, setFile] = useState('');
    const [fileName, setFileName] = useState("");
    const [content, setContent] = useState(null);

    //base end point url
    const FILE_UPLOAD_BASE_ENDPOINT = "http://localhost:8081/api/load-data/";

    const uploadFileHandler = (event) => {
        setFile(event.target.files[0]);
        setFileName(event.target.files[0].name);
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

        const link = "http://localhost:8082/api/classify/setFilename?fileName=" + fileName;
        axios.post(link).then(
            r => {
                console.log(r.status);
            }
        );

        // axios.post("http://localhost:8081/api/load-data/uploadFile", selectedFile);
        fetch(FILE_UPLOAD_BASE_ENDPOINT + 'uploadFile', requestOptions)
            .then(r  => {
                if(r.ok) {
                    pageData();
                }
            });
    };


    // TESTING FOR CLASSIFIER
    // const pageData = () => {
    //
    //     const link = "http://localhost:8081/api/load-data/getDataSummary?fileName=" + fileName;
    //     let summ = null;
    //     axios.get(link)
    //         .then(res => {
    //             summ = res.data;
    //             let rows = Array.prototype.slice.call(summ);
    //             rows = rows.join("<br/>");
    //             rows = "<h3>" + rows + "</h3>";
    //             setContent(rows);
    //
    //             // summ = "<p>" + res.data + "</p>";
    //             // let reg = /\\n/g;
    //             // summ = summ.replaceAll(reg, "<br/>");
    //             // setContent(summ);
    //         })
    // }

    const pageData = () => {

        const link = "http://localhost:8081/api/load-data/getDataSummary?fileName=" + fileName;
        let summ = null;
        axios.get(link)
            .then(res => {
                summ = res.data;
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
                            <br/><br/>
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
                  {/*<br/>*/}
                  <form className="file-upload-form" onSubmit={fileSubmitHandler}>
                      <input type="file" className="selectFile" accept=".arff,.arff.gz,.names,.data,.csv,.json,.json.gz,
                      .libsvm,.m,.dat,.bsi,.xrff,.xrff.gz" onChange={uploadFileHandler} />
                      <br /><br />
                      <button className="uploadFile" type='submit' >Upload File</button>
                  </form>
                  <br />

                  {/* If content exists, show it */}
                  {content ? (
                      <div className="data-summary">
                          {content}
                          {/*<div dangerouslySetInnerHTML={{ __html: content }}/>*/}
                      </div>
                  ):(
                      <div></div>
                  )}

                  {/*<div className="data-summary">*/}
                  {/*    /!*{content}*!/*/}
                  {/*    <div dangerouslySetInnerHTML={{ __html: content }}/>*/}
                  {/*</div>*/}
              </section>
          </div>
    );

}

export default Home;