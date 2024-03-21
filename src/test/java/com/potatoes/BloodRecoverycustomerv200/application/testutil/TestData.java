package com.potatoes.BloodRecoverycustomerv200.application.testutil;

import com.potatoes.BloodRecoverycustomerv200.domain.model.aggregates.Customer;
import com.potatoes.BloodRecoverycustomerv200.domain.model.commands.AddCustomerCommand;
import com.potatoes.BloodRecoverycustomerv200.interfaces.rest.dto.AddCustomerFormDto;

import java.util.Date;

public class TestData {

    public static AddCustomerFormDto mockAddMemberFormDto = AddCustomerFormDto.builder()
            .userId("exampleId")
            .name("김예시")
            .nickname("samplenickname")
            .password("password1@")
            .email("example@gmail.com")
            .phone("010-9346-2991")
            .fileNm("sample.jpg")
            .gradeSn("BASIC")
            .userStatus("VALID")
            .date(new Date())
            .build();

    public static AddCustomerCommand mockAddCustomerCommand = AddCustomerCommand.builder()
            .userId(mockAddMemberFormDto.getUserId())
            .name(mockAddMemberFormDto.getName())
            .nickname(mockAddMemberFormDto.getNickname())
            .password(mockAddMemberFormDto.getPassword())
            .email(mockAddMemberFormDto.getEmail())
            .phone(mockAddMemberFormDto.getPhone())
            .build();

    public static Customer mockCustomer = new Customer(mockAddCustomerCommand);


}
