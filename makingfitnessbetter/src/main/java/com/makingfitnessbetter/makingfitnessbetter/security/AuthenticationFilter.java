package com.makingfitnessbetter.makingfitnessbetter.security;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.makingfitnessbetter.makingfitnessbetter.entities.User;
import com.makingfitnessbetter.makingfitnessbetter.requests.UserLoginRequest;
import com.makingfitnessbetter.makingfitnessbetter.service.UserService;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private UserService userService;

    private CustomAuthenticationManager authenticationManager;

    public AuthenticationFilter(CustomAuthenticationManager authenticationManager) {
        this.authenticationManager=authenticationManager;

    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {

        try {
            //COMES FROM UserLoginRequest WHICH LOGIN THE USER IN
            UserLoginRequest creds = new ObjectMapper().readValue(req.getInputStream(), UserLoginRequest.class);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword(), new ArrayList<>());

            //MAKES CALLS TO THE LoadUserByUser FROM THE USERSERVER WHICH GETS THE USERNAME
            return authenticationManager.authenticate(usernamePasswordAuthenticationToken
            );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        //This is fetching the username
        String email = ( auth.getName());

        // Enter a method to get the
//        User selectedUser = userService.getUserByUsername(username);
//        Integer id = selectedUser.getMemberId();
//        String email = selectedUser.getEmail();

//        System.out.println("id " + selectedUser.getMemberId());
//        System.out.println("email " + selectedUser.getEmail());



        String token = Jwts.builder()
                .setSubject(email)
                //REQURIES SECURITY CONSTRANT CLASS
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.Token_Expiration_Time))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.TOKEN_SECRET )
                .compact();

        System.out.println("local username is : " + email);



        //One you login, the token knows who you are
//        res.getWriter().write("{\"token\": \"" + token + "\", \"role\": \""+ auth.getAuthorities().stream().findFirst().get().getAuthority() + "\" }");
//        res.getWriter().write("{\"token\": \"" + token + "\", \"role\": \""+ auth.getAuthorities().stream().findFirst().get().getAuthority() + "\", \"email\": \"" + username + "\" + \"id\": \"" + id + "\" }");
        res.getWriter().write("{\"token\": \"" + token + "\", \"role\": \""+ auth.getAuthorities().stream().findFirst().get().getAuthority() + "\", \"email\": \"" + email + "\"}");

    }


}

