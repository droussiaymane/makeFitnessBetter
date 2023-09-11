import axios from "axios";


class UserService {

    async registerUser(username :string, password : string, email : string, role : string){
        return await axios.post("http://localhost:8080/user/submitRegistration",
            {
                username,
                password,
                email,
                role
            });
    }



}

export default UserService;