package com.makingfitnessbetter.makingfitnessbetter.exceptions;

import com.makingfitnessbetter.makingfitnessbetter.repositories.ExerciseLogRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExpectionHandler {
    @ExceptionHandler(UserException.class)
    public ResponseEntity<String> UserExceptionHandler(UserException e){
        return ResponseEntity.ok(e.getMessage());
    }
    @ExceptionHandler(EntryLogException.class)
    public ResponseEntity<String> EntryLogExceptionHandler(EntryLogException e){return ResponseEntity.ok(e.getMessage());}
    @ExceptionHandler(ExerciseLogException.class)
    public ResponseEntity<String> ExerciseLogExceptionHandler(EntryLogException e){return ResponseEntity.ok(e.getMessage());}

}
