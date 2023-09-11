package com.makingfitnessbetter.makingfitnessbetter.service;

import com.makingfitnessbetter.makingfitnessbetter.entities.User;
import com.makingfitnessbetter.makingfitnessbetter.vo.ExerciseValidationVO;
import com.makingfitnessbetter.makingfitnessbetter.vo.SubmitExerciseLogVO;
import com.makingfitnessbetter.makingfitnessbetter.vo.SubmitRegistrationVO;

public interface ValidationService {

    public User userValidation( SubmitRegistrationVO submitRegistrationVO);
    public SubmitExerciseLogVO validateExerciseLog(SubmitExerciseLogVO submitExerciseLogVO);

}
