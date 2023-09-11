import React, {useState} from 'react';
import {Button, Card, Container, Form, FormControl, FormGroup, FormLabel} from "react-bootstrap";
import UserService from "../../services/UserService";
import {useNavigate} from "react-router-dom";
import MainNavigation from "../navigationComponents/MainNavigation";

const UserRegistration = () => {
    let userService : UserService = new UserService();

    const navigate = useNavigate()



    // interface FormData {
    //     username: string;
    //     password: string;
    //     email : string;
    //     role : string;
    //
    // }
    //
    // const [formRegstration, setFormRegstration] = React.useState<FormData>(
    //     {
    //         username : "",
    //         password : "",
    //         email : "",
    //         role : ""
    //     }
    //
    // );

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [email, setEmail] = useState("");
    const [role, setRole] = useState("");


    function registerUserForm(e : any){
        e.preventDefault()
        userService.registerUser(username, password, email, role).then(response=> {
            if(response.data){
                setUsername(response.data.username);
                setPassword(response.data.package)
                setEmail(response.data.email);
                setRole(response.data.role);
                console.log("Regiration was successful")

                navigate("/")

            } else {
                console.log("not sucessful")
            }
        });

    }






    return (
        <>
            <Container>

                <MainNavigation/>

            <h1 className="registration-title">Let's Register For An Account</h1>

            <Card className="registration-card-container">
                <Form onSubmit = {registerUserForm}>
                    <FormGroup className = "registration-form-container">
                        <FormLabel className = "registration-form-label">
                            <p>
                                Username
                            </p>

                        </FormLabel>
                        <FormControl
                            className="registration-form-placeholder"
                            type="text"
                            name="username"
                            placeholder="username"
                            onChange={(e) => (setUsername(e.target.value))}
                        />
                    </FormGroup>
                    <FormGroup>
                        <FormLabel className = "registration-form-label">
                            <p>
                                Password
                            </p>
                        </FormLabel>
                        <FormControl
                            className="registration-form-placeholder"
                            type="text"
                            name="password"
                            placeholder="password"
                            onChange={e => (setPassword(e.target.value))}
                        />
                    </FormGroup>
                    <FormGroup>
                        <FormLabel className = "registration-form-label">
                            <p>
                                Email
                            </p>
                        </FormLabel>
                        <FormControl
                            className="registration-form-placeholder"
                            type="text"
                            name="email"
                            placeholder="Email"
                            onChange={e => (setEmail(e.target.value))}
                        />
                    </FormGroup>
                    <FormGroup>
                        <FormLabel className = "registration-form-label">
                            <p>
                                role
                            </p>
                        </FormLabel>
                        <FormControl
                            className="registration-form-placeholder"
                            type="text"
                            name="role"
                            placeholder="Role"
                            onChange={e => (setRole(e.target.value))}
                        />
                    </FormGroup>
                    <div className="registration-form-button-group">
                        <Button
                            className="registration-form-button"
                            type="submit"
                        >
                            Enter
                        </Button>
                    </div>




                </Form>
            </Card>

            </Container>




        </>
    );
};

export default UserRegistration;