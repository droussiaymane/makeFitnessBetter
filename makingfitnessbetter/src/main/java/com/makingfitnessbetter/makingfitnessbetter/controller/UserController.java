package com.makingfitnessbetter.makingfitnessbetter.controller;

import com.makingfitnessbetter.makingfitnessbetter.entities.User;
import com.makingfitnessbetter.makingfitnessbetter.exceptions.UserException;
import com.makingfitnessbetter.makingfitnessbetter.service.UserService;
import com.makingfitnessbetter.makingfitnessbetter.vo.SubmitRegistrationVO;
import com.makingfitnessbetter.makingfitnessbetter.vo.UserLoginVO;
import com.makingfitnessbetter.makingfitnessbetter.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    //CREATE A USER
    @PostMapping("/create")
    public ResponseEntity<Object> createNewUser(@RequestBody User user){
        try{
            User result = userService.create(user);
            return UserVO.generateResponse("User created", HttpStatus.OK, result);
        } catch(UserException e){
            throw new UserException("Make sure to enter all information");
        }

    }

    @GetMapping("/getUser")
    public UserLoginVO getUserViaUsername(@RequestParam String username){
        UserLoginVO user = userService.getUserVOByUsername(username);
        return user;

    }

    @PostMapping("/submitRegistration")
    public ResponseEntity<Object> sumbitRegistration(@RequestBody SubmitRegistrationVO submitRegistrationVO){
        try{
            User result = userService.submitRegistration(submitRegistrationVO);
            return UserVO.generateResponse("User created", HttpStatus.CREATED, result);
        } catch(UserException e){
            throw new UserException("Make sure to enter all information");
        }

    }

    // Create a larger class called User Registration
    // ---- I wil require the translong model and modifation to the existin tbale
    //1) Ask if the if the user exist
        //2)    Craete the user
        // Stop process
        // Add a validation feild
    //2) Will add a later conditon for they have existing user
        //Get them to log in
    //3) Make transaction log


}
