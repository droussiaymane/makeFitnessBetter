package com.makingfitnessbetter.makingfitnessbetter.security;

//import com.digitalleafmarketing.orderappback.SpringApplicationContext;
//import com.digitalleafmarketing.orderappback.entity.User;
//import com.digitalleafmarketing.orderappback.entity.User;
//import com.digitalleafmarketing.orderappback.service.UserService;
//import com.digitalleafmarketing.orderappback.service.UserService;
import com.makingfitnessbetter.makingfitnessbetter.SpringApplicationContext;
import com.makingfitnessbetter.makingfitnessbetter.entities.User;
import com.makingfitnessbetter.makingfitnessbetter.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

public class CustomAuthenticationManager implements AuthenticationManager {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserService userService = (UserService) SpringApplicationContext.getBean("userServiceImp");
        PasswordEncoder encoder = (PasswordEncoder) SpringApplicationContext.getBean("passwordEncoder");
        User user = userService.getUserByUsername(username);


        if (user != null) {
            if (user.getAccountNotLocked()) {
                if (!encoder.matches(password, user.getPassword())) {
                    if (user.getFailedAttempt() < userService.MAX_FAILED_ATTEMPTS - 1) {
                        userService.increaseFailedAttempts(user);
                    } else {
                        userService.lock(user);
                        throw new LockedException("Your account has been locked due to 3 failed attempts."
                                + " It will be unlocked after 24 hours.");
                    }
                    throw new BadCredentialsException("Password incorrect");
                } else {
                    return new UsernamePasswordAuthenticationToken(username, password, Arrays.asList(new SimpleGrantedAuthority(user.getRole())));

                }

            } else {
                if (userService.unlockWhenTimeExpired(user)) {
                    throw new LockedException("Your account has been unlocked. Please try to login again.");
                } else {
                    throw new LockedException("Your account has been locked due to 3 failed attempts."
                            + " It will be unlocked after 24 hours.");
                }

            }
        } else {
            throw new BadCredentialsException("user not found exception");
        }

    }

}