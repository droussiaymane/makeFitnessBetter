package com.makingfitnessbetter.makingfitnessbetter.service;

import com.makingfitnessbetter.makingfitnessbetter.entities.User;
import com.makingfitnessbetter.makingfitnessbetter.exceptions.UserException;
import com.makingfitnessbetter.makingfitnessbetter.repositories.UserRepository;
import com.makingfitnessbetter.makingfitnessbetter.vo.SubmitRegistrationVO;
import com.makingfitnessbetter.makingfitnessbetter.vo.UserLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ValidationService validationService;

    @Autowired
    TransactionLogService transactionLogService;

    @Autowired
    PasswordEncoder encoder;


    //CREATE A NEW USER
    public User create(User user){
        user.setFailedAttempt(0);
        user.setAccountNotLocked(true);
        user.setPassword(encoder.encode(user.getPassword()));
        User userModel = userRepository.save(user);
        return userModel;

    }


    public User submitRegistration(SubmitRegistrationVO submitRegistrationVO){

        User user = new User();

        // validation layer --- found out if they have an already existing user
        user =  validationService.userValidation(submitRegistrationVO);

        //If they don't exist, create the user
         User submitUser = create(user);

        // Adding the translog for creation and existing
        transactionLogService.createUserLog(user);

        return submitUser;

    }




    // ============== SECURITY  RELATED REQUESTS ==============

    // ***** GET ROLE OF USER BY EMAIL *****
    public String getRoleByEmail(String email){
        try{
            String role = userRepository.findByUsername(email).get().getRole();
            return role;
        } catch(UserException e){
            throw new UserException("NO ROLE OF THE USER FOUND. CHECK IF THE EMAIL IS CORRECT");
        }

    }

    // ***** GET ROLE OF USER BY USERNAME *****
    public String getRoleByUsername(String username){
        try{
            String role = userRepository.findByUsername(username).get().getRole();
            return role;
        }catch(UserException e){
            throw new UserException("NO ROLE OF THE USER FOUND. CHECK IF THE USERNAME IS CORRECT");
        }
    }

    // ***** GET USER BY EMAIL *****
    public User getUserByEmail(String email)  {
        try{
            User user= userRepository.findByEmail(email).get();
//            ModelMapper modelMapper=new ModelMapper();
//            UserResponse userresponse =modelMapper.map(user,UserResponse.class);
//            return userresponse;
            return user;
        } catch(UserException e){
            throw new UserException("NO USER FOUND. CHECK IF THE EMAIL IS CORRECT");
        }

    }

    // ***** GET USER BY USERNAME *****
    @Override
    public User getUserByUsername(String username) {
        try {
            Optional<User> user=userRepository.findByUsername(username);
            if (!user.isPresent()){
                throw new BadCredentialsException("user not found exception");
            }
            return user.get();
        }catch(UserException e){
            throw new UserException("NO USER FOUND. CHECK IF THE USERNAME IS CORRECT");
        }

    }

    @Override
    public UserLoginVO getUserVOByUsername(String username) {
        try {
            Optional<User> user=userRepository.findUserVOByUsername(username);
            UserLoginVO userLoginVO = new UserLoginVO();
            userLoginVO.setMemberId(user.get().getMemberId());
            userLoginVO.setUsername(user.get().getUsername());
            userLoginVO.setFailedAttempt(user.get().getFailedAttempt());
            userLoginVO.setLockTime(user.get().getLockTime());
            userLoginVO.setRole(user.get().getRole());

            if (!user.isPresent()){
                throw new BadCredentialsException("user not found exception");
            }
            return userLoginVO;
        }catch(UserException e){
            throw new UserException("NO USER FOUND. CHECK IF THE USERNAME IS CORRECT");
        }

    }

    // ***** UPDATE FAILED ATTEMPTS *****
    @Override
    public void updateFailedAttempts(int failAttempts, String email){
        try{
            User user=userRepository.findByEmail(email).get();
            user.setFailedAttempt(failAttempts);
            userRepository.save(user);
        }
        catch(UserException e){
            throw new UserException("UNABLE TO UPDATE FAILEDATTEMPTS");
        }

    }

    // ***** INCREASE FAILED ATTEMPTS FAILING TO LOGIN *****
    @Override
    public void increaseFailedAttempts(User user) {
        try{
            int newFailAttempts = user.getFailedAttempt() + 1;
            updateFailedAttempts(newFailAttempts, user.getEmail());
        }
        catch(UserException e){
            throw new UserException("UNABLE TO INCREASE FAILED ATTEMPTS");
        }
    }

    // ***** RESET FAILED ATTEMPTS *****
    @Override
    public void resetFailedAttempts(String email) {
        try{
            updateFailedAttempts(0, email);
        }
        catch(UserException e){
            throw new UserException("UNABLE TO RESEST THE FAILED ATTEMPTS");
        }
    }

    // ***** LOCK USER OUT WHEN EXCEEDING FAILED ATTEMPTS *****
    @Override
    public void lock(User user) {
        try{
            user.setAccountNotLocked(false);
            user.setLockTime(new Date());
            userRepository.save(user);
        }
        catch(UserException e){
            throw new UserException("UNABLE TO ACCESS ACCOUNT, YOU HAVE BEEN LOCKED");
        }
    }

    // ***** UNLOCK A USER WHEN TIME EXPIRES *****
    @Override
    public boolean unlockWhenTimeExpired(User user) {
        try{
            long lockTimeInMillis = user.getLockTime().getTime();
            long currentTimeInMillis = System.currentTimeMillis();

            if (lockTimeInMillis + LOCK_TIME_DURATION < currentTimeInMillis) {
                user.setAccountNotLocked(true);
                user.setLockTime(null);
                user.setFailedAttempt(0);
                userRepository.save(user);
                return true;
            }
            return false;
        }
        catch(UserException e){
            throw new UserException("ERROR SETTING UP UNLOCK COUNTDOWN");
        }
    }






}
