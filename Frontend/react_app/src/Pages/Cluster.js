import React, {useState} from "react";
import axios from "axios";
import {Form} from "react-bootstrap";
import Button from "react-bootstrap/Button";

const Cluster = () => {

    const [content, setContent] = useState(null);
    const [algorithm, setAlgorithm] = useState(null);
    const [percentage, setPercentage] = useState("");

    const submitHandler = (event) => {
        event.preventDefault();
        setContent(null);
        let link = "http://localhost:8084/api/cluster/getSummary?algorithm=" + algorithm + "&percentage=" + percentage;
        // let link = "http://localhost:8084/api/cluster/getSummary?algorithm=" + "SimpleKmeans" + "&percentage=" + "80";
        let summary = null;
        axios.get(link)
            .then(res => {
                summary = res.data;
                setContent(summary);
            })

        event.target['algorithm'].value = "";
        setAlgorithm("");
        setPercentage("");
    }


    return (
        <div>
            <div className="form-container">
                <Form onSubmit={submitHandler}>
                    <Form.Group>
                        <Form.Select name="algorithm" onChange={event => setAlgorithm(event.target.value)}>
                            <option value="">Select an algorithm for clustering</option>
                            <option value="SimpleKmeans">Simple KMeans</option>
                            <option value="EM">Expectation Maximization (EM)</option>
                            <option value="Cobweb">Cobweb</option>
                            <option value="Canopy">Canopy</option>
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
            <div>
                {/* If content exists, show it */}
                {content ? (
                    <div className="cluster-summary">
                        <div dangerouslySetInnerHTML={{ __html: content }}/>
                        {/*<br/><br/>*/}
                    </div>
                ): (
                    <div className="cluster-content"></div>
                )}
            </div>
        </div>
    )
}

export default Cluster;