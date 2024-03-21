package com.potatoes.BloodRecoverycustomerv200.domain.model.commands;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class ModifyCustomerCommand {
    private String password;
    private String nickname;
    private String email;
    private String phone;
    private String fileNm;
    private String userStatus;
}
