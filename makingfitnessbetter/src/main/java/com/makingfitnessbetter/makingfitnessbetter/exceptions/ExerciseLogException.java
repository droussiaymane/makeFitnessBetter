package com.makingfitnessbetter.makingfitnessbetter.exceptions;

import org.springframework.http.ResponseEntity;

public class ExerciseLogException extends RuntimeException{
    public ExerciseLogException(String message){super(message);}
}
