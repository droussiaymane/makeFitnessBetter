import React, {useContext, useState} from 'react';
import Login from "../authenticationComponents/Login";

// @ts-ignore
import MainNavigation from "../navigationComponents/MainNavigation";
import {Button, Card, Container, Form, Modal} from "react-bootstrap";
import {useNavigate} from "react-router-dom";
import LoginModal from "../authenticationComponents/LoginModal";
import styles from "./Modal.module.css";
import Auth from "../../services/Auth";
import {AuthContext} from "../../dataStore";


const HomepageComponent = () => {

    const navigate=useNavigate();

    let auth : Auth = new Auth();
    const authContext = useContext(AuthContext);


    function NavigateToRegistrationForm(){
        navigate("/registration");
    }


    // @ts-ignore
    return (
        <>

            <MainNavigation/>

            <Container className = 'page-container'>
                <cite className="progress-warning"> *Frontend and backend application is still undergoing progress and introducing updates on newly planned features soon *</cite>

                <h1 className="home-title"> Making Fitness Better</h1>

                <div className="home-description-group">
                    <h3 className="home-description-first">
                        Redefine
                    </h3>
                    <p className="home-description">
                        Fitness
                    </p>
                </div>
                <div className="home-description-group">
                    <h3 className="home-description-first">
                        Redefine
                    </h3>
                    <p className="home-description">
                        Tracking
                    </p>
                </div>
                <div className="home-description-group">
                    <h3 className="home-description-first">
                        Redefine
                    </h3>
                    <p className="home-description">
                        Your Time
                    </p>
                </div>

                <Card className = "home-card">
                    <Card.Body>
                        <blockquote className="blockquote mb-0">
                            <p>
                                Motivation Quote that will generate from backend
                            </p>
                            <footer className="blockquote-footer">
                                Someone famous in <cite title="Source Title">Writer Name here</cite>
                            </footer>
                        </blockquote>
                    </Card.Body>
                </Card>


                <div className="home-signup-button-container">
                    <Button
                        className="home-signup-button"
                        onClick={NavigateToRegistrationForm}
                    >
                        <h2 className="home-signin"> Sign up NOW!</h2>
                    </Button>
                </div>



            </Container>


            </>
    );
};

export default HomepageComponent;