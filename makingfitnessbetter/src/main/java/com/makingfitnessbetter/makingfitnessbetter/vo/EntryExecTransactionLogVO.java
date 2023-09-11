package com.makingfitnessbetter.makingfitnessbetter.vo;

import com.makingfitnessbetter.makingfitnessbetter.entities.EntryLog;
import com.makingfitnessbetter.makingfitnessbetter.entities.ExerciseLog;
import lombok.Data;

import java.util.List;

@Data
public class EntryExecTransactionLogVO {
    private Integer ExerciseId;
    private String ExerciseName;
    private Integer sets;
    private Integer reps;
    private String comments;
    private Integer memberId;
    private Integer entryId;
    private String actionCd;
    private List<EntryLog> liEntryExerciseLogs;
    private String entryName;
    private String overallComments;
    private List<ExerciseLog> exerciseLogList;
//    private EntryLog entryLog;
//    private ExerciseLog exerciseLog;

}
