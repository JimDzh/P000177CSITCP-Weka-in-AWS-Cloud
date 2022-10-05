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
    const [attribute, setAttribute] = useState(null);
    const [content, setContent] = useState(null);


    const submitHandler = (event) => {
        event.preventDefault();
        setContent(null);
        let base_link = "http://localhost:8083/api/filter/";
        let summary = null;

        if(remove) {
            let link = base_link + "removeAttribute?attribute=" + event.target["attribute"].value;
            axios.post(link)
                .then(res => {
                    summary = res.data;
                    // setContent(summary);
                })
        } else if(replaceConstant) {
            let link = base_link + "replaceMissing-constant?constant=" + constant;
            axios.post(link)
                .then(res => {
                    summary = res.data;
                    // setContent(summary);
                })
        } else if(replaceMean) {
            let link = base_link + "replaceMissing-mean";
            axios.post(link)
                .then(res => {
                    summary = res.data;
                    // setContent(summary);
                })
        }

        event.target['filter'].value = "";
        setRemove(null);
        setReplaceMean(null);
        setReplaceConstant(null);
    }

    const callOption = (value) => {
        switch(value) {
            case "remove":
                setRemove(true);
                setReplaceMean(null);
                setReplaceConstant(null);
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
            <Form.Group>
                <Form.Label>Constant value</Form.Label>
                <Form.Control type="text" value={constant} onChange={event => setConstant(event.target.value)}/>
                <Form.Text muted>
                    Enter a constant that will replace all the missing values (nominal values are set to null)
                </Form.Text>
            </Form.Group>
        );
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
            </div>
        </div>
    );
}

export default Filter;