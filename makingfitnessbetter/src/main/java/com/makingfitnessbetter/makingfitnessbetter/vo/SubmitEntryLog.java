package com.makingfitnessbetter.makingfitnessbetter.vo;

import com.makingfitnessbetter.makingfitnessbetter.entities.ExerciseLog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubmitEntryLog {

    private Integer entryId;
    private Integer memberId;
    private String entryName;
    private String overallComments;

    private List<ExerciseLog> exerciseLogList;

}
