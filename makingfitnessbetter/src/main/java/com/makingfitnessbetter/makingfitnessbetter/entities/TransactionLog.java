package com.makingfitnessbetter.makingfitnessbetter.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@Data
@Entity
@Table(name = "transactionlog")
@AllArgsConstructor
@NoArgsConstructor
public class TransactionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer transId;
    private Integer memberId;
    private Integer entryId;
    private Integer exerciseId;
    private String transCd;
    private String transDescription;
    private Date logDateMade;
    private String userMod;

}
