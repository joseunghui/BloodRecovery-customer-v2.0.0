package com.potatoes.BloodRecoverycustomerv200.domain.model.commands;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class FindCustomerMyInfoCommand {
    private String userId;
    private String name;
    private String phone;
}
