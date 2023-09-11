import React, {useContext, useState} from 'react';

import {Link, useNavigate} from "react-router-dom";
import {IoMdLogIn} from "react-icons/io";
import {FiLogOut} from "react-icons/fi";
import {MdAccountCircle} from "react-icons/md";
import {BsCartCheck} from "react-icons/bs";
import {ImHome} from "react-icons/im";
import {GrLogin} from "react-icons/gr";
import {LuLogOut} from "react-icons/lu";
import {GiHamburgerMenu} from "react-icons/gi";
import {FaClipboardList} from "react-icons/fa";
import {MdOutlineAssignmentInd} from "react-icons/md";
import brandLogo from '../images/placeholder.png';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
import {Button, Container, Modal} from 'react-bootstrap';
import Auth from "../../services/Auth";
import {AuthContext} from "../../dataStore";
import Login from "../authenticationComponents/Login";

// import brandLogo from './images/placeholder.png';

const MainNavigation = () => {

    const navigate=useNavigate();

    let auth : Auth = new Auth();
    const authContext = useContext(AuthContext);

    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    // const [remember, setRemember] = useState(false);
    const [error,setError]=useState(false);
    const [message,setMessage]=useState("")


    function loginUser(e:any){
        e.preventDefault();
        auth.login(username,password).then((response)=>{
            console.log(response.data);

            if(response.data.token){
                localStorage.setItem("token",response.data.token);
                localStorage.setItem("email", response.data.email);
                localStorage.setItem("role",response.data.role);
                authContext.setToken(response.data.token);
            }

            const currentUser = auth.getUser(username).then((response) => {
                if(response.data){
                    localStorage.setItem("id",response.data.memberId);
                    localStorage.setItem("username",response.data.username);
                }
            });

            navigate("/");

            handleClose();

        }).catch(err=>{
            setMessage(err.response.data.exception);
            setError(true)
        });
        // localStorage.setItem("remember-me",String(remember));
    }

    function logOutUser(){
        auth.logout();

    }



    // @ts-ignore
    return (
        <>
            <Navbar expand="lg" className="bg-body-tertiary">
                <Container>
                    {/*<Navbar.Brand href="#home">*/}
                        <img
                            alt=""
                            src={brandLogo}
                            width="30"
                            height="30"
                            className=" main-nav-brand-title d-inline-block align-top "
                        />
                    <h1 className="main-nav-brand-title">
                        Make Fitness Better
                    </h1>
                    {/*</Navbar.Brand>*/}
                    {/*<Navbar.Toggle aria-controls="basic-navbar-nav" />*/}
                    {/*<Navbar.Collapse id="basic-navbar-nav">*/}
                    {/*    /!*<Nav className="me-auto">*!/*/}
                    {/*    <Nav className="me-auto">*/}

                        <div className="main-nav-link-group">
                            <Link to="/" className="main-nav-link">
                                {/*<ImHome*/}
                                {/*    size={35}*/}
                                {/*    color="green"*/}
                                {/*/>*/}
                                home
                            </Link>
                            <Link
                                to="/registration"
                                className="main-nav-link"
                            >
                                register
                            </Link>
                            <Link
                                to="/logs"
                                className="main-nav-link"
                            >
                                Logs
                            </Link>

                            {
                                authContext.token ?

                                    <div>
                                        <Button
                                            variant="primary"
                                            onClick={logOutUser}
                                            className="link-login-button"
                                        >
                                            Logout
                                        </Button>
                                    </div>

                                    :

                                    <div>
                                        <Button
                                            className="link-login-button"
                                            variant="primary"
                                            onClick={handleShow}
                                        >
                                            Login
                                        </Button>
                                    </div>


                            }






                            <Modal show={show} onHide={handleClose}>
                                <Modal.Header closeButton>
                                    <Modal.Title>Login Form</Modal.Title>
                                </Modal.Header>
                                <Modal.Body>
                                    <Login onSubmit={loginUser} username={username} setUsername={setUsername} password={password}
                                           setPassword={setPassword} />

                                </Modal.Body>
                                <Modal.Footer>
                                    <Button variant="secondary" onClick={handleClose}>
                                        Close Modal
                                    </Button>
                                </Modal.Footer>
                            </Modal>




                        </div>
                </Container>
            </Navbar>






        </>
    );
};

export default MainNavigation;