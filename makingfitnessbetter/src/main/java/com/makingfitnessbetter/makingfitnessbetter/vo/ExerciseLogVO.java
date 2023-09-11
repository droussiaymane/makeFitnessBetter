package com.makingfitnessbetter.makingfitnessbetter.vo;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@Data
public class ExerciseLogVO {

    private Integer ExerciseId;
    private String ExerciseName;
    private Integer sets;
    private Integer reps;
    private String comments;

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object objResponse){
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("status", status.value());
        map.put("response", objResponse);
        return new ResponseEntity<Object>(map, status);
    }
}
