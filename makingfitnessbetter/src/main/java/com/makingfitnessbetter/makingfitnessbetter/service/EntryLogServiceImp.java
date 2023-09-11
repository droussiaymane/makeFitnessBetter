package com.makingfitnessbetter.makingfitnessbetter.service;

import com.makingfitnessbetter.makingfitnessbetter.entities.EntryLog;
import com.makingfitnessbetter.makingfitnessbetter.entities.ExerciseLog;
import com.makingfitnessbetter.makingfitnessbetter.entities.User;
import com.makingfitnessbetter.makingfitnessbetter.exceptions.EntryLogException;
import com.makingfitnessbetter.makingfitnessbetter.repositories.EntryLogRepository;
import com.makingfitnessbetter.makingfitnessbetter.repositories.UserRepository;
import com.makingfitnessbetter.makingfitnessbetter.vo.CreateEntryLogVO;
import com.makingfitnessbetter.makingfitnessbetter.vo.SubmitEntryLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class EntryLogServiceImp implements EntryLogService {

    @Autowired
    EntryLogRepository entryLogRepository;

    @Autowired
    UserRepository userRepository;

    //CREATE ENTRYLOG
//    public EntryLog createEntry(CreateEntryLogVO entryLogVo){
//        try{
//            EntryLog newEntry = new EntryLog();
//            newEntry.setEntryName(entryLogVo.getEntryName());
//            newEntry.setOverallComments(entryLogVo.getOverallComments());
//            entryLogRepository.save(newEntry);
//            return newEntry;
//        } catch(EntryLogException e){
//            throw new EntryLogException("All indicated fields not properly filled in");
//        }
//
//    }

    public EntryLog createEntry(CreateEntryLogVO entryLogVo, Integer id){
        try{
            Optional<User> userOpt = userRepository.findById(id);
            if(userOpt.isPresent()) {
                User user = userOpt.get();

                EntryLog newEntry = new EntryLog();
                newEntry.setUser(user);  // Setting the user relationship here
                newEntry.setEntryName(entryLogVo.getEntryName());
                newEntry.setOverallComments(entryLogVo.getOverallComments());
                entryLogRepository.save(newEntry);
                return newEntry;
            } else {
                throw new EntryLogException("User not present");
            }
        } catch(EntryLogException e){
            throw new EntryLogException("All indicated fields not properly filled in");
        }
    }


    public List<EntryLog> fetchAllEntryRecords(Integer id){
        //Find all entries by memeber Id
        try{
            List<EntryLog> allEntries = entryLogRepository.findAllByMemberId(id);
            return allEntries;
        } catch(EntryLogException e){
            throw new EntryLogException("No entries were found");
        }



    }

    public EntryLog submitEntryLog(SubmitEntryLog submitEntryLog){
        // Check if the post entry is already an entry

        // If it is a new entry, go to the create new entry flow

        // If it is an existing, go to the edit exisitn entry flow

        // If it is an entry to be deleted, then enter delete entry flow

        // Log according to the actionCd Status


        return null;

    }




}
