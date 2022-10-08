import Home from "./Home";
import React, {useEffect, useState} from "react";
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
    const [numeric, setNumeric] = useState(null);
    const [replaceAttrOptions, setReplaceAttrOptions] = useState(null);
    const [attrValues, setAttrValues] = useState(null);
    // const [replaceAttrValue, setReplaceAttrValue] = useState(null);
    // const [numericOptions, setNumericOptions] = useState(null);


    useEffect(()=>{
        if(numeric === false) {
            generateReplaceAttrOptions("nominal");
        } else if(numeric === true){
            setReplaceAttrOptions(null);
            setAttrValues(null);
        }
        // console.log(numeric);
    },[numeric])


    const submitHandler = (event) => {
        event.preventDefault();
        setContent(null);
        let base_link = "http://localhost:8083/api/filter/";

        if(remove) {
            let link = base_link + "removeAttribute?attribute=" + event.target["attribute"].value;
            generateSummary(link, event.target["attribute"].value);
        } else if(replaceConstant) {
            let type = "";
            if(!numeric) {
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
        setNumeric(null);
    }

    const callOption = (value) => {
        switch(value) {
            case "remove":
                setRemove(true);
                setReplaceMean(null);
                setReplaceConstant(null);
                setReplaceAttrOptions(null);
                setAttrValues(null);
                // setAttribute(null);
                generateRemoveOptions();
                break;
            case "replaceConstant":
                setReplaceConstant(true);
                setRemove(null);
                setReplaceMean(null);
                break;
            case "replaceMean":
                setReplaceMean(true);
                setRemove(null);
                setReplaceConstant(null);
                setReplaceAttrOptions(null);
                setAttrValues(null);
                break;
        }
    }

    const generateRemoveOptions = () => {
        let type = "";
        let link = "http://localhost:8083/api/filter/getAttributes?type=" + type;
        let attributes = null;
        axios.get(link)
            .then(res => {
                attributes = Array.prototype.slice.call(res.data);
                setRemoveOptions(
                    <div>
                        <Form.Select name="attribute">
                            <option value="">Select an attribute you want to remove</option>
                            {
                                attributes.map((attribute) => (
                                    <option value={attribute}>{attribute}</option>
                                ))
                            }
                        </Form.Select>
                        <br/>
                    </div>
                );
            })
    }


    const generateReplaceAttrOptions = (type) => {
        let link = "http://localhost:8083/api/filter/getAttributesWithMissingValues?type=" + type;
        let attributes = null;
        axios.get(link)
            .then(res => {
                attributes = Array.prototype.slice.call(res.data);
                setReplaceAttrOptions(
                    <div>
                        <Form.Select name="replaceAttr" onChange={event => generateAttrValues(event.target.value)}>
                            <option value="">Select an attribute in which you want to replace missing values</option>
                            {
                                attributes.map((attribute) => (
                                    <option value={attribute}>{attribute}</option>
                                ))
                            }
                        </Form.Select>
                        <br/>
                    </div>
                );
            })
    }

    const generateAttrValues = (attribute) => {
        let link = "http://localhost:8083/api/filter/getAttributeValues?attribute=" + attribute;
        let values = null;
        axios.get(link)
            .then(res => {
                values = Array.prototype.slice.call(res.data);
                setAttrValues(
                    <div>
                        <Form.Select name="attributeValue" onChange={event => setConstant(event.target.value)}>
                            <option value="">Select a value that will replace missing values in this attribute</option>
                            {
                                values.map((value) => (
                                    <option value={value}>{value}</option>
                                ))
                            }
                        </Form.Select>
                        <br/>
                    </div>
                );
            })
    }

    // const generateNumericOptions = () => {
    //     setNumericOptions(
    //         <div>
    //             <Form.Group>
    //                 <Form.Label>Constant value</Form.Label>
    //                 <Form.Control type="text" value={constant} onChange={event => setConstant(event.target.value)}/>
    //                 <Form.Text muted>
    //                     Enter a constant that will replace all the numeric missing values
    //                 </Form.Text>
    //                 <br/>
    //             </Form.Group>
    //             <br/>
    //         </div>
    //     );
    // }

    // const generateReplaceConstantOptions = () => {
    //     setReplaceConstantOptions(
    //         <div>
    //             <Form.Group>
    //                 <Form.Select name="type" onChange={event => setNumeric(event.target.value === "numeric")}>
    //                     <option value="">Select the type of attribute you want to replace missing values in</option>
    //                     <option value="nominal">Nominal</option>
    //                     <option value="numeric">Numeric</option>
    //                 </Form.Select>
    //             </Form.Group>
    //             <br/>
    //         </div>
    //     );
    // }

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
                        <div>
                            <Form.Group>
                                <Form.Select name="type" onChange={event => setNumeric(event.target.value === "numeric")}>
                                    <option value="">Select the type of attribute you want to replace missing values in</option>
                                    <option value="nominal">Nominal</option>
                                    <option value="numeric">Numeric</option>
                                </Form.Select>
                            </Form.Group>
                            <br/>
                            {numeric ? (
                                <div>
                                    <Form.Group>
                                        <Form.Label>Constant value</Form.Label>
                                        <Form.Control type="text" value={constant} onChange={event => setConstant(event.target.value)}/>
                                        <Form.Text muted>
                                            Enter a constant that will replace all the numeric missing values
                                        </Form.Text>
                                    </Form.Group>
                                    <br/>
                                </div>
                            ):null}
                        </div>
                    ): null}
                    {replaceAttrOptions ? (
                        replaceAttrOptions
                    ) : null}
                    {attrValues ? (
                        attrValues
                    ) : null}
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