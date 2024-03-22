package com.potatoes.BloodRecoverycustomerv200.domain.model.aggregates;


import com.potatoes.BloodRecoverycustomerv200.domain.model.commands.AddCustomerCommand;
import com.potatoes.BloodRecoverycustomerv200.domain.model.commands.LoginCustomerCommand;
import com.potatoes.BloodRecoverycustomerv200.domain.model.commands.ModifyCustomerCommand;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@AllArgsConstructor
@Builder
@DynamicUpdate
@Table(schema = "Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @PrimaryKeyJoinColumn
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

    // 가입
    public Customer(AddCustomerCommand command) {
        this.cid = command.getCid();
        this.userId = command.getUserId();
        this.name = command.getName();
        this.nickname = command.getNickname();
        this.password = command.getPassword();
        this.email = command.getEmail();
        this.phone = command.getPhone();
    }

    // 로그인
    public Customer(LoginCustomerCommand command) {
        this.userId = command.getUserId();
        this.password = command.getPassword();
    }

    // 수정
    public  Customer(ModifyCustomerCommand command) {
        this.cid = command.getCid();
        this.nickname = command.getNickname();
        this.email = command.getEmail();
        this.phone = command.getPhone();
        this.fileNm = command.getFileNm();
    }

}
