import axios from "axios";
import {useContext} from "react";

class Auth {

    async login(username : string, password : string){
        return await axios.post("http://localhost:8080/login",
            {
                username,
                password
            });
    }

    async getUser(username : string){
        return await axios.get("http://localhost:8080/user/getUser?username=" + username);
    }




    logout():void{
        localStorage.removeItem("token");
        localStorage.removeItem("id");
        localStorage.removeItem("role");
        localStorage.removeItem("remember-me");
        localStorage.removeItem("email");
        localStorage.removeItem("username");
    }

}

export default Auth;