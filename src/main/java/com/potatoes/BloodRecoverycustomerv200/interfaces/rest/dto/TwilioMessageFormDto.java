package com.potatoes.BloodRecoverycustomerv200.interfaces.rest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TwilioMessageFormDto {

    private String userPhoneNumber;
    private String userPhoneValidCode;
}
