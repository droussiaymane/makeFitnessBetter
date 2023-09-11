import React, {useState, Suspense} from 'react';
import HomepageComponent from "./components/layout/HomepageComponent";
import {BrowserRouter as Router, Routes, Route, BrowserRouter, useNavigate} from "react-router-dom";
import Auth from "./services/Auth";
import axios from "axios";
import { AuthContext } from './dataStore';
import jwtDecode from "jwt-decode";
import Login from "./components/authenticationComponents/Login";

import 'bootstrap/dist/css/bootstrap.min.css';
import './stylesheets/customCSS.css';
import Logs from "./components/logsComponents/Logs";
import UserRegistration from "./components/userRelatedComponents/UserRegistration";
// import UserRegistration from "./components/userRelatedComponents/UserRegistration";


function App() {

    const [token, setToken] = useState(localStorage.getItem("token"));

    let auth:Auth=new Auth();

    // @ts-ignore

    if (token) {
        axios.defaults.headers.common['Authorization'] = 'Bearer ' + token ;

        // const rememberMe= localStorage.getItem("remember-me");
        // @ts-ignore
        //TO USE THE TOKEN, FOR REQUESTS...ECT..NEEDS TO BE DECODED ( Base 64 format )
        let exp=jwtDecode(token).exp;
        // if(rememberMe=="false"){
        //     //Use Seconds For this
        //     console.log(exp)
        //     //The example calucation::::IN SECONDS
        //     // for 1 hour (1 h = 36000s , and 7 days = 604800000 ms = 604800 s)
        //     // the finished answer equals (604800 - 36000 = 601200 )
        //     exp-=601200;
        // }
        //it uses Milliseconds through the security
        // if(exp*1000 < Date.now()){
        //     auth.logout();
        // }

    }



  return (
      <Suspense fallback={null}>

          <AuthContext.Provider
              value={{token,setToken}}
          >
            <>
              <Router>
                <Routes>
                    <Route path="/" element={<HomepageComponent/>} />
                    <Route path="/logs" element={<Logs/>}/>
                    <Route path="/registration" element={<UserRegistration/>}/>
                    {/*<Route path="/login" element={<Login/>} />*/}
                </Routes>
              </Router>

            </>
          </AuthContext.Provider>
      </Suspense>
  );
}

export default App;
