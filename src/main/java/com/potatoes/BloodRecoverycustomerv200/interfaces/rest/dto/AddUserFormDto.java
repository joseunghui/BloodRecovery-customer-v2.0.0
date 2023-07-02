package com.potatoes.BloodRecoverycustomerv200.interfaces.rest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddUserFormDto {

    @NotBlank(message = "이름은 필수입니다.")
    private String userName;

}
