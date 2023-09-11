import React, {useState} from 'react';
import {Button, Card, Form, Modal, ModalHeader} from "react-bootstrap";
import {inspect} from "util";
import {RiCloseLine} from "react-icons/ri";
// @ts-ignore
const LoginModal = ({ onSubmit   }) => {

    const [email, setEmail] = useState("");

    const [password, setPassword] = useState("");



    return (
        <>
            <Form onSubmit={onSubmit}>

                <Form.Group controlId="formBasicEmail">

                    <Form.Label>Email address</Form.Label>

                    <Form.Control

                        type="email"

                        placeholder="Enter email"

                        value={email}

                        onChange={(e) => setEmail(e.target.value)}

                    />

                </Form.Group>


                <Form.Group controlId="formBasicPassword">

                    <Form.Label>Password</Form.Label>

                    <Form.Control

                        type="password"

                        placeholder="Password"

                        value={password}

                        onChange={(e) => setPassword(e.target.value)}

                    />

                </Form.Group>

                <Form.Group controlId="formBasicCheckbox">

                    <Form.Check type="checkbox" label="Remember Me!" />

                </Form.Group>

                <Button variant="primary" type="submit" >

                    Login

                </Button>

            </Form>
        </>
    );
};

export default LoginModal;