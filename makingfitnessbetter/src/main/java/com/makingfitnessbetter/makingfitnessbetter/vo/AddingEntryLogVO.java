package com.makingfitnessbetter.makingfitnessbetter.vo;

import com.makingfitnessbetter.makingfitnessbetter.entities.ExerciseLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddingEntryLogVO {

    private List<ExerciseLog> exerciseLogList;

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object objResponse){
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("status", status.value());
        map.put("response", objResponse);
        return new ResponseEntity<Object>(map, status);
    }
}
