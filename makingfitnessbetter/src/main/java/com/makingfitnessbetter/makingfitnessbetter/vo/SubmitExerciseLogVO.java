package com.makingfitnessbetter.makingfitnessbetter.vo;

import com.makingfitnessbetter.makingfitnessbetter.entities.EntryLog;
import com.makingfitnessbetter.makingfitnessbetter.entities.ExerciseLog;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class SubmitExerciseLogVO {

    private Integer exerciseId;
    private String exerciseName;
    private Integer sets;
    private Integer reps;
    private String comments;
    private Integer memberId;
    private Integer entryId;
    private String actionCd;
    private List<EntryLog> liExistingEntryLog;
//    private List<EntryLog> liExistingEntryLog;

//    private EntryLog processedEntry;

}

