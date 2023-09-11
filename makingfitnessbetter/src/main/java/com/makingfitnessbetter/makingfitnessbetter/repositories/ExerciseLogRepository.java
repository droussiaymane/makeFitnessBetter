package com.makingfitnessbetter.makingfitnessbetter.repositories;

import com.makingfitnessbetter.makingfitnessbetter.entities.EntryLog;
import com.makingfitnessbetter.makingfitnessbetter.entities.ExerciseLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public interface ExerciseLogRepository extends JpaRepository<ExerciseLog, Integer> {

//    @Modifying
////    @Query("update exercise_log set  = ?1, u.lastname = ?2 where u.id = ?3")
//    @Query("update exercise_log set  exercise_name = ?, reps = ?, sets = ?, comments = ? where exercise_id = exerciseId")
//    void updateExerciseLog(String firstname, String lastname, Integer userId);
//
//    //https://stackoverflow.com/questions/11881479/how-do-i-update-an-entity-using-spring-data-jpa
//




//    TypedQuery<ExerciseLog> query = em.createQuery(
//            "select * from exercise_log where entry_id = :entry_id", ExerciseLog.class);
////            "SELECT e FROM Employee e WHERE e.name = :name AND e.age = :empAge" , ExerciseLog.class);
//    Integer entry_id = ;
//    int empAge = 55;
//    List<ExerciseLog> allExercises = query
//            .setParameter("name", empName)
//            .setParameter("empAge", empAge)
//            .getResultList();

//    @Query(value = "select * from exercise_log where entry_id = ?")
//        List<ExerciseLog> findAllExerciseLogsById(Integer entryId);


//    List<ExerciseLog> findAllExerciseLogsById(EntryLog entryLog);
}
