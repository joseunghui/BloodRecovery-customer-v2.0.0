package com.potatoes.BloodRecoverycustomerv200.domain.model.commands;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Builder
@Getter
@ToString
public class AddUserCommand {
    private String userId;
    private String name;
    private String nickname;
    private String password;
    private String bldTp;
    private String address;
    private String email;
    private String phone;
    private String gradeSn;
    private String userStatus;
    private Date date;
}
