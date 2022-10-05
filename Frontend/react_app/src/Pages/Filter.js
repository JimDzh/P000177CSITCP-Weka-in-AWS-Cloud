import Home from "./Home";
import React, {useState} from "react";
import axios from "axios";
import {Form} from "react-bootstrap";
import Button from "react-bootstrap/Button";


const Filter = () => {

    const [remove, setRemove] = useState(null);
    const [replaceConstant, setReplaceConstant] = useState(null);
    const [replaceMean, setReplaceMean] = useState(null);
    const [removeOptions, setRemoveOptions] = useState(null);
    const [replaceConstantOptions, setReplaceConstantOptions] = useState(null);
    const [constant, setConstant] = useState(null);
    const [content, setContent] = useState(null);
    const [nominal, setNominal] = useState(null);
    // const [selected, setSelected] = useState([]);

    const submitHandler = (event) => {
        event.preventDefault();
        setContent(null);
        let base_link = "http://localhost:8083/api/filter/";

        if(remove) {
            let link = base_link + "removeAttribute?attribute=" + event.target["attribute"].value;
            // let atts = selected
            // atts[atts.length] = event.target["attribute"].value;
            // setSelected(atts);
            generateSummary(link, event.target["attribute"].value);
        } else if(replaceConstant) {
            let type = "";
            if(nominal) {
                type = "nominal";
            } else {
                type = "numeric";
            }
            let link = base_link + "replaceMissing-constant?constant=" + constant + "&type=" + type;
            generateSummary(link, null);
        } else if(replaceMean) {
            let link = base_link + "replaceMissing-mean";
            generateSummary(link, null);
        }
        event.target['filter'].value = "";
        setRemove(null);
        setReplaceMean(null);
        setReplaceConstant(null);
        setConstant("");
    }

    const callOption = (value) => {
        switch(value) {
            case "remove":
                setRemove(true);
                setReplaceMean(null);
                setReplaceConstant(null);
                // setAttribute(null);
                generateRemoveOptions();
                break;
            case "replaceConstant":
                setReplaceConstant(true);
                setRemove(null);
                setReplaceMean(null);
                generateReplaceConstantOptions();
                break;
            case "replaceMean":
                setReplaceMean(true);
                setRemove(null);
                setReplaceConstant(null);
                break;
        }
    }

    const generateRemoveOptions = () => {
        let link = "http://localhost:8083/api/filter/getAttributes";
        let attributes = null;
        axios.get(link)
            .then(res => {
                attributes = Array.prototype.slice.call(res.data);
                setRemoveOptions(
                    <Form.Select name="attribute">
                        <option value="">Select an attribute you want to remove</option>
                        {
                            attributes.map((attribute) => (
                                <option value={attribute}>{attribute}</option>
                            ))
                        }
                    </Form.Select>
                );
            })
    }

    const generateReplaceConstantOptions = () => {
        setReplaceConstantOptions(
            <div>
                <Form.Group>
                    <Form.Select name="type" onChange={event => setNominal(event.target.value === "nominal")}>
                        <option value="">Select the type of your target attribute</option>
                        <option value="nominal">Nominal</option>
                        <option value="numeric">Numeric</option>
                    </Form.Select>
                </Form.Group>
                <br/>
                {nominal ? (
                    <Form.Group>
                        <Form.Label>Constant value</Form.Label>
                        <Form.Control type="text" value={constant} onChange={event => setConstant(event.target.value)}/>
                        <Form.Text muted>
                            Enter a constant that will replace all the nominal missing values
                        </Form.Text>
                    </Form.Group>
                ): (
                    <Form.Group>
                        <Form.Label>Constant value</Form.Label>
                        <Form.Control type="text" value={constant} onChange={event => setConstant(event.target.value)}/>
                        <Form.Text muted>
                            Enter a constant that will replace all the numeric missing values
                        </Form.Text>
                    </Form.Group>
                )}
            </div>
        );
    }

    const generateSummary = (link, attribute) => {

        let summary = null;
        axios.post(link)
            .then(res => {
                summary = res.data;
                let rows = Array.prototype.slice.call(summary);
                if (summary) {
                    let final = [];
                    let header = ["ID", "AttributeName", "Type", "Nominal", "Integer", "Real", "MissingValues", "UniqueValues", "DistinctValues"];
                    let info = [];
                    for(let i=0; i < rows.length;i++) {
                        if (i > 4) {
                            let data = rows[i];
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

                    setContent(
                        <div>
                            {remove ? (
                                <h3><b>The attribute '{attribute}' has been successfully removed!</b></h3>
                            ):null}
                            {replaceConstant ? (
                                <h3><b>All numerical missing values have been successfully replaced with '{constant}'</b></h3>
                            ):null}
                            {replaceMean ? (
                                <h3><b>All missing values have been successfully replaced with the mean!</b></h3>
                            ):null}
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
                } else {
                    setContent(
                        <div className="filter-error">
                            <h3><b>The attribute '{attribute}' cannot removed since it is the only attribute of the dataset!</b></h3>
                        </div>
                    );
                }
            })
    }


    return (
        <div>
            <div className="form-container">
                <Form onSubmit={submitHandler}>
                    <Form.Group>
                        <Form.Select name="filter" onChange={event => callOption(event.target.value)}>
                            <option value="">Select one of the preprocessing options</option>
                            <option value="remove">Remove an attribute</option>
                            <option value="replaceConstant">Replace all missing values with a constant</option>
                            <option value="replaceMean">Replace all missing values with mean</option>
                        </Form.Select>
                    </Form.Group>
                    <br/>
                    {remove ? (
                        removeOptions
                    ): null}
                    {replaceConstant ? (
                        replaceConstantOptions
                    ): null}
                    <br/>
                    <Button variant="primary" type="submit">
                        Submit
                    </Button>
                </Form>
                <div>
                    {/* If content exists, show it */}
                    {content ? (
                        <div className="filter-summary">
                            {content}
                        </div>
                    ): (
                        <div className="filter-content"></div>
                    )}
                </div>
            </div>
        </div>
    );
}

export default Filter;