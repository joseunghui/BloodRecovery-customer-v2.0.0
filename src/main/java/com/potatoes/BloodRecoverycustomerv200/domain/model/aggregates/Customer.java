package com.potatoes.BloodRecoverycustomerv200.domain.model.aggregates;


import com.potatoes.BloodRecoverycustomerv200.domain.model.commands.CustomerCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@AllArgsConstructor
@Builder
@Table(schema = "Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "CID", nullable = false)
    private String cid;

    @Column(name = "USER_ID", nullable = false)
    private String userId;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "NICKNAME", nullable = false)
    private String nickname;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "PHONE", nullable = false)
    private String phone;

    @Column(name = "FILE_NM")
    private String fileNm;

    @Column(name = "GRADE_SN", nullable = false)
    private String gradeSn;

    @Column(name = "USER_STATUS", nullable = false)
    private String userStatus;

    @Column(name = "DATE", nullable = false)
    private Date date;

    public Customer(CustomerCommand command) {
        this.userId = command.getUserId();
        this.name = command.getName();
        this.nickname = command.getNickname();
        this.password = command.getPassword();
        this.email = command.getEmail();
        this.phone = command.getPhone();
        this.fileNm = command.getFileNm();
        this.gradeSn = command.getGradeSn();
        this.userStatus = command.getUserStatus();
        this.date = command.getDate();
    }


}
