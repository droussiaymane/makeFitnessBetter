package com.makingfitnessbetter.makingfitnessbetter.vo;

import com.makingfitnessbetter.makingfitnessbetter.entities.EntryLog;
import lombok.Data;

import java.util.List;

@Data
public class ExerciseValidationVO {

    private Integer ExerciseId;
    private String ExerciseName;
    private Integer sets;
    private Integer reps;
    private String comments;
    private Integer memberId;
    private String actionCd;
    private List<EntryLog> liEntryExerciseLogs;





}

