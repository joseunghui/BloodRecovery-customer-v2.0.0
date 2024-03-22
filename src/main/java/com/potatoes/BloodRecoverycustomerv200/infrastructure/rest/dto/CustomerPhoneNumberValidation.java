package com.potatoes.BloodRecoverycustomerv200.infrastructure.rest.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CustomerPhoneNumberValidation {
    private String phone;
    private String message;

    // setter
    public void setCustomerPhoneNumberValidation(String phone, String message) {
        this.phone = phone;
        this.message = message;
    }
}
