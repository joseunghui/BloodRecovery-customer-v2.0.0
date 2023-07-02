package com.potatoes.BloodRecoverycustomerv200.domain.model.aggregates;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.Date;

@Entity
public class User {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;


    @Column(name = "USER_NAME", nullable = false)
    private String userName;
    @Column(name = "USER_NICKNAME", nullable = false)
    private String userNickname;
    @Column(name = "USER_PASSWORD", nullable = false)
    private String userPassword;
    @Column(name = "PERSONAL_NO", nullable = false)
    private String personalNo;
    @Column(name = "GRADE_SN", nullable = false)
    private String gradeSn;
    @Column(name = "DATE", nullable = false)
    private Date date;

}
