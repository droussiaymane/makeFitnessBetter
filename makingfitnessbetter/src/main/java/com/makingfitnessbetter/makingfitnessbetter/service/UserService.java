package com.makingfitnessbetter.makingfitnessbetter.service;


import com.makingfitnessbetter.makingfitnessbetter.entities.User;
import com.makingfitnessbetter.makingfitnessbetter.vo.SubmitRegistrationVO;
import com.makingfitnessbetter.makingfitnessbetter.vo.UserLoginVO;
import com.makingfitnessbetter.makingfitnessbetter.vo.UserVO;

public interface UserService {


    // ============== STATIC SECURITY VARIABLS ==============

    // ***** MAXIUM AMOUNT OF FAILED ATTEMPTS *****
    public static final int MAX_FAILED_ATTEMPTS = 3;

    // ***** LOCK OUT TIME DURATION AFTER EXCEEDING LOGIN ATTEMPTS *****
    static final long LOCK_TIME_DURATION = 1000*60*60*24; // 1 day ( in milli seconds )


    // ============== USER NORMAL RELATED REQUESTS ==============


    //CREATE A NEW USER
    User create(User user);

    User submitRegistration(SubmitRegistrationVO submitRegistrationVO);



    // ============== SECURITY RELATED REQUESTS ==============

    // ***** GET ROLE OF USER BY EMAIL *****
    public String getRoleByEmail(String email);

    // ***** GET ROLE OF USER BY USERNAME *****
    public String getRoleByUsername(String username);

    // ***** GET USER BY EMAIL *****
    public User getUserByEmail(String email);

    // *****  GET USER BY USERNAME *****
    public User getUserByUsername(String username);
    public UserLoginVO getUserVOByUsername(String username);


    // ***** UDPATE FAILED ATTEMPTS *****
    public void updateFailedAttempts(int failAttempts, String email);

    // ***** INCREASE FAILED ATTEMPTS *****
    public void increaseFailedAttempts(User user) ;

    // ***** RESET THE FAILED ATTEMPTS *****
    public void resetFailedAttempts(String email);

    // ***** LOCK USER AFTER EXCEEDING LOGIN TRIES *****
    public void lock(User user) ;

    // ***** UNLOCKED USER ACCESS TO LOGIN AFTER CERTAIN TIME *****
    public boolean unlockWhenTimeExpired(User user) ;


}
