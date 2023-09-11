package com.makingfitnessbetter.makingfitnessbetter.vo;

import com.makingfitnessbetter.makingfitnessbetter.entities.ExerciseLog;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class CreateEntryLogVO {

    private Integer entryId;
    private Integer memberId;
    private String entryName;
    private String overallComments;

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object objResponse){
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("status", status.value());
        map.put("response", objResponse);
        return new ResponseEntity<Object>(map, status);
    }

}
