package com.potatoes.BloodRecoverycustomerv200.interfaces.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddUserFormDto {
    private interface userValid{ }

    @NotBlank(groups = {userValid.class}, message = "아이디는 필수 입니다.")
    @NotNull(groups = {userValid.class}, message = "아이디는 필수 입니다.")
    private String userId;

    @NotBlank(groups = {userValid.class}, message = "이름은 필수입니다.")
    @NotNull(groups = {userValid.class}, message = "이름은 필수입니다.")
    private String userName;

    @NotBlank(groups = {userValid.class}, message = "비밀번호는 필수 입니다.")
    @NotNull(groups = {userValid.class}, message = "비밀번호는 필수 입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d~!@#$%^&*()+|=]{8,20}$") //'숫자', '문자' 무조건 1개 이상, '최소 8자에서 최대 20자' 허용
    private String userPassword;

}
