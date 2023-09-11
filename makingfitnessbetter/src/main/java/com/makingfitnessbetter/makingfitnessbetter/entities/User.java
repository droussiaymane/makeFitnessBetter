package com.makingfitnessbetter.makingfitnessbetter.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "USERTABLE")
@AllArgsConstructor
@NoArgsConstructor
public class User {


    //
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer memberId;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String email;
    private String role;
    //only two options : ROLE_ADMIN, ROLE_DEV, ROLE_USER
    private Integer failedAttempt;
    private Boolean accountNotLocked;
    private Date lockTime;
    @Column(name = "verifcation_code", length = 64)
    private String verifcationCode;
    private String actionCd;



// ...

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<EntryLog> entryLogList;

// ...


    public User( String email, String password, String username, String role) {

        this.email = email;
        this.password = password;
        this.username = username;
        this.role = role;
    }

    public User(Integer memberId, String email, String password, String username, String role) {
        this.memberId = memberId;
        this.email = email;
        this.password = password;
        this.username = username;
        this.role = role;
    }


}
