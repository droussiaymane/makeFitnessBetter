import React, {useContext, useState} from 'react';
import axios from "axios";
import {useNavigate} from "react-router-dom";
import Auth from "../../services/Auth";
import {AuthContext} from "../../dataStore";
import { Form, Button, FormGroup, FormLabel, FormControl, FormCheck } from 'react-bootstrap';

// @ts-ignore
const Login = ({onSubmit ,username, setUsername, password, setPassword}) => {

    let auth : Auth = new Auth();
    const authContext = useContext(AuthContext);

    const [error,setError]=useState(false);
    const [message,setMessage]=useState("")
    const navigate=useNavigate();



    // @ts-ignore
    return (
        <>
        {/* **************************** Best of making forms*/}
        {/*<Form inline>*/}
        {/*    <Row>*/}
        {/*        <Col xs="auto">*/}
        {/*            <Form.Control*/}
        {/*                type="text"*/}
        {/*                placeholder="Search"*/}
        {/*                className=" mr-sm-2"*/}
        {/*            />*/}
        {/*        </Col>*/}
        {/*        <Col xs="auto">*/}
        {/*            <Button type="submit">Submit</Button>*/}
        {/*        </Col>*/}
        {/*    </Row>*/}
        {/*</Form>*/}
        {/*</Navbar>*/}

            <Form className="login-form-container" onSubmit={onSubmit}>
                <div className="login-form-background">
                    <FormGroup>
                        <FormLabel className="login-form-label"> Username</FormLabel>
                        <FormControl
                            type="text"
                            name="username"
                            placeholder="username"
                            onChange={(e) => (setUsername(e.target.value))}
                        />
                    </FormGroup>
                    <FormGroup>
                        <FormLabel className="login-form-label">Password</FormLabel>
                        <FormControl
                            type="password"
                            name="password"
                            placeholder="password"
                            onChange={(e) => (setPassword(e.target.value))}
                        />
                    </FormGroup>

                    {/*<FormCheck*/}
                    {/*    className="login-form-label"*/}
                    {/*    type="checkbox"*/}
                    {/*    label="rememberme"*/}
                    {/*    defaultChecked={false}*/}
                    {/*    onChange={(e) => setRemember(!remember)}*/}
                    {/*/>*/}

                    <div className="login-form-button-group">
                        <Button
                            className="login-form-button"
                            type="submit"
                        >
                            Enter
                        </Button>
                    </div>
                    <div>
                        {error && (<>{message}</>)}
                    </div>
                </div>
            </Form>
            
        </>
    );
};

export default Login;