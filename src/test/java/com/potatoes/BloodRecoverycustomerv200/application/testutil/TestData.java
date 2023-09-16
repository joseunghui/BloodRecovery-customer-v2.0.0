package com.potatoes.BloodRecoverycustomerv200.application.testutil;

import com.potatoes.BloodRecoverycustomerv200.domain.model.aggregates.Customer;
import com.potatoes.BloodRecoverycustomerv200.domain.model.commands.CustomerCommand;
import com.potatoes.BloodRecoverycustomerv200.interfaces.rest.dto.CustomerFormDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

public class TestData {
    private static BCryptPasswordEncoder encoder;

    public static CustomerFormDto mockAddMemberFormDto = CustomerFormDto.builder()
            .userId("")
            .name("")
            .nickname("")
            .password(encoder.encode(""))
            .bldTp("")
            .address("")
            .email("")
            .phone("")
            .fileNm("")
            .gradeSn("")
            .userStatus("")
            .date(new Date())
            .build();

    public static CustomerCommand mockAddCustomerCommand = CustomerCommand.builder()
            .userId(mockAddMemberFormDto.getUserId())
            .name(mockAddMemberFormDto.getName())
            .nickname(mockAddMemberFormDto.getNickname())
            .password(mockAddMemberFormDto.getPassword())
            .bldTp(mockAddMemberFormDto.getBldTp())
            .address(mockAddMemberFormDto.getAddress())
            .email(mockAddMemberFormDto.getEmail())
            .phone(mockAddMemberFormDto.getPhone())
            .fileNm(mockAddMemberFormDto.getFileNm())
            .gradeSn(mockAddMemberFormDto.getGradeSn())
            .userStatus(mockAddMemberFormDto.getUserStatus())
            .date(mockAddMemberFormDto.getDate())
            .build();

    public static Customer mockCustomer = new Customer(mockAddCustomerCommand);

    public static String MockCustomerFormString = "memberId=test123&password=!23Qwe&name=테스터&phone=010-1234-5678&birth=2000-01-01&email=aaa@gmail.com&gender=male&city=서울&street=성수1가&zipcode=12345";




}
