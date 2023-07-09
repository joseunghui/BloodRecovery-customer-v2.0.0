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
    private String userName;
    private String userNickname;
    private String userPassword;
    private String personalNo;
    private String gradeSn;
    private Date date;
}
