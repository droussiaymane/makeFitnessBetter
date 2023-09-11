package com.makingfitnessbetter.makingfitnessbetter.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Data
@Entity
public class EntryLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer entryId;
    private Integer memberId;
    private String entryName;
    private String overallComments;
    private String actionCd;


    // ...

    @OneToMany(mappedBy = "entryLog", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ExerciseLog> exerciseLogList;

// ...


    // ...

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_memberId")
    @JsonBackReference
    private User user;

// ...
}
