import React, {useState} from 'react';
import Button from 'react-bootstrap/Button';
import { Form } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import './classify-style.css';
import axios from "axios";

// import {fileName} from './Home';

// Classify component
const Preprocess = () => {

    // const [fileName, setFileName] = useState("");
    const [content, setContent] = useState(null);
    const [algorithm, setAlgorithm] = useState(null);
    const [percentage, setPercentage] = useState("");

    const fileSubmitHandler = (event) => {
        event.preventDefault();
        console.log(percentage);
        console.log(algorithm);

        const link = "http://localhost:8082/api/classify/getDataSummary?algorithm=" + algorithm + "&percentage=" + percentage;
        let summary = null;
        axios.get(link)
            .then(res => {
                summary = res.data;
                // let rows = Array.prototype.slice.call(summary);
                // rows = rows.join("<br/>");
                // rows = "<p>" + rows + "</p>";
                // setContent(rows);

                setContent(summary);
            })

        setAlgorithm("");
        setPercentage("");
    }

    return (
        <div>
            <div className="form-container">
                <Form onSubmit={fileSubmitHandler}>
                    <Form.Group>
                        <Form.Select name="algorithm" onChange={event => setAlgorithm(event.target.value)}>
                            <option>Select an algorithm for classification</option>
                            {/* used for nominal target variables */}
                            <option value="NaiveBayes">Naive Bayes</option>
                        </Form.Select>
                    </Form.Group>
                    <br/>
                    <Form.Group>
                        <Form.Label>Split Percentage</Form.Label>
                        <Form.Control type="text" value={percentage} onChange={event => setPercentage(event.target.value)}/>
                        <Form.Text muted>
                            Enter the split percentage to split the database into train and test sets
                        </Form.Text>
                    </Form.Group>
                    <br/>
                    <Button variant="primary" type="submit">
                        Submit
                    </Button>
                </Form>
            </div>
            <br/>
            <div className="classify-content">
                {/* If content exists, show it */}
                {content ? (
                    <div className="data-summary">
                        <div dangerouslySetInnerHTML={{ __html: content }}/>
                    </div>
                ):(
                    <div>
                        <br/><br/><br/><br/><br/><br/><br/><br/>
                    </div>
                )}
            </div>
        </div>
    )
}

export default Preprocess;
