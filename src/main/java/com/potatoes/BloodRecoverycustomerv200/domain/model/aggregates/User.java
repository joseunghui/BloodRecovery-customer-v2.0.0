package com.potatoes.BloodRecoverycustomerv200.domain.model.aggregates;


import com.potatoes.BloodRecoverycustomerv200.domain.model.commands.AddUserCommand;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Entity
@Getter
@AllArgsConstructor
@Builder
@Table(schema = "User")
public class User {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "USER_ID", nullable = false)
    private String userId;

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

    public User(AddUserCommand command) {
        this.userId = command.getUserId();
        this.userName = command.getUserName();
        this.userNickname = command.getUserNickname();
        this.userPassword = command.getUserPassword();
        this.gradeSn = command.getGradeSn();
        this.date = command.getDate();
    }

    public User() {

    }
}
