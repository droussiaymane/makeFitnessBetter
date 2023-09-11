package com.makingfitnessbetter.makingfitnessbetter.service;

import com.makingfitnessbetter.makingfitnessbetter.entities.EntryLog;
import com.makingfitnessbetter.makingfitnessbetter.entities.User;
import com.makingfitnessbetter.makingfitnessbetter.exceptions.EntryLogException;
import com.makingfitnessbetter.makingfitnessbetter.exceptions.ExerciseLogException;
import com.makingfitnessbetter.makingfitnessbetter.exceptions.UserException;
import com.makingfitnessbetter.makingfitnessbetter.repositories.UserRepository;
import com.makingfitnessbetter.makingfitnessbetter.utility.transactionCode;
import com.makingfitnessbetter.makingfitnessbetter.vo.ExerciseValidationVO;
import com.makingfitnessbetter.makingfitnessbetter.vo.SubmitExerciseLogVO;
import com.makingfitnessbetter.makingfitnessbetter.vo.SubmitRegistrationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ValidationImp implements ValidationService {

    @Autowired
    private UserRepository userRepository;

    public User userValidation(SubmitRegistrationVO submitRegistrationVO){

        User user = new User();
        user.setUsername(submitRegistrationVO.getUsername());
        user.setPassword(submitRegistrationVO.getPassword());
        user.setEmail(submitRegistrationVO.getEmail());
        user.setRole(submitRegistrationVO.getRole());

        //Checking if user already exists
        if(user != null) {
            Optional<User> formUser = userRepository.findByUsername(user.getUsername());
            if(formUser.isPresent()) {
                user.setActionCd("USER_EXISTS");
                throw new UserException("Account already exist, please log in to existing account");
            }
            user.setActionCd("USER_CREATED");
        }

        return user;
    }

    public SubmitExerciseLogVO validateExerciseLog(SubmitExerciseLogVO submitExerciseLogVO){

        SubmitExerciseLogVO validationResults = new SubmitExerciseLogVO();

        List<EntryLog> finalList = new ArrayList<>();

        try{
            //Reassigning the list of al the expercises for th entry
            List<EntryLog> checkingList = submitExerciseLogVO.getLiExistingEntryLog();
            finalList = checkingList.stream().filter(valCheck -> Objects.equals(valCheck.getEntryId(), submitExerciseLogVO.getExerciseId())).collect(Collectors.toList());

            System.out.println(finalList);

//            if(finalList.isEmpty()){
//                submitExerciseLogVO.setActionCd(transactionCode.CRE_EXE_LOG);
//            } else {
//                submitExerciseLogVO.setActionCd(transactionCode.EXE_EXE_LOG);
//                submitExerciseLogVO.setEntryId(finalList.get(0).getEntryId());
//            }

            if(submitExerciseLogVO.getExerciseId() == null){
                submitExerciseLogVO.setActionCd(transactionCode.CRE_EXE_LOG);
            } else {
                submitExerciseLogVO.setActionCd(transactionCode.EXE_EXE_LOG);
            }

            return  validationResults;
        } catch (ExerciseLogException e){
            throw new ExerciseLogException("Error validation Exercise");
        }



    }





}
