package com.makingfitnessbetter.makingfitnessbetter.vo;


import lombok.Data;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.HashMap;
import java.util.Map;

@Data
public class UserVO {

    private Integer memberId;
    private String username;
    private String password;
    private String email;

     public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object objResponse){
         Map<String, Object> map = new HashMap<>();
         map.put("message", message);
         map.put("status", status.value());
         map.put("response", objResponse);
         return new ResponseEntity<Object>(map, status);
     }



}
