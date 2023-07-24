package com.potatoes.BloodRecoverycustomerv200.domain.model.aggregates;


import com.potatoes.BloodRecoverycustomerv200.domain.model.commands.AddUserCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@AllArgsConstructor
@Builder
@Table(schema = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "USER_ID", nullable = false)
    private String userId;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "NICKNAME", nullable = false)
    private String nickname;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "BLD_TP", nullable = false)
    private String bldTp;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "PHONE", nullable = false)
    private String phone;

    @Column(name = "GRADE_SN", nullable = false)
    private String gradeSn;

    @Column(name = "USER_STATUS", nullable = false)
    private String userStatus;

    @Column(name = "DATE", nullable = false)
    private Date date;

    public User(AddUserCommand command) {
        this.userId = command.getUserId();
        this.name = command.getName();
        this.nickname = command.getNickname();
        this.password = command.getPassword();
        this.bldTp = command.getBldTp();
        this.address = command.getAddress();
        this.email = command.getEmail();
        this.phone = command.getPhone();
        this.gradeSn = command.getGradeSn();
        this.userStatus = command.getUserStatus();
        this.date = command.getDate();
    }

    public User() {

    }
}
