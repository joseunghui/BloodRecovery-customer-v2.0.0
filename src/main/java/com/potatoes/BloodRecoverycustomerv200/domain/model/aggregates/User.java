package com.potatoes.BloodRecoverycustomerv200.domain.model.aggregates;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column
    private String name;

}
