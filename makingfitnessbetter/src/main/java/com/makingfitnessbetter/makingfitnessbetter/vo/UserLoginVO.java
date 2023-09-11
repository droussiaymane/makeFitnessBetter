package com.makingfitnessbetter.makingfitnessbetter.vo;


import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.Column;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class UserLoginVO {

    private Integer memberId;
    private String username;
    private String password;
    private String email;
    private String role;
    //only two options : ROLE_ADMIN, ROLE_DEV, ROLE_USER
    private Integer failedAttempt;
    private Boolean accountNotLocked;
    private Date lockTime;
    @Column(name = "verifcation_code", length = 64)
    private String verifcationCode;

     public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object objResponse){
         Map<String, Object> map = new HashMap<>();
         map.put("message", message);
         map.put("status", status.value());
         map.put("response", objResponse);
         return new ResponseEntity<Object>(map, status);
     }



}
