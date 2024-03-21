package com.potatoes.BloodRecoverycustomerv200.interfaces.rest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Builder
public class ModifyCustomerFormDto {
    private interface userValid{ }

    @NotBlank(groups = {userValid.class}, message = "비밀번호는 필수 입니다.")
    @NotNull(groups = {userValid.class}, message = "비밀번호는 필수 입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d~!@#$%^&*()+|=].{8,20}+$") //'숫자', '문자' 무조건 1개 이상, '최소 8자에서 최대 20자' 허용
    private String password;

    @NotBlank(groups = {userValid.class}, message = "닉네임은 필수입니다.")
    @NotNull(groups = {userValid.class}, message = "닉네임은 필수입니다.")
    private String nickname;

    private String email;

    @NotBlank(groups = {userValid.class}, message = "본인인증용 전화번호는 필수 입니다.")
    @NotNull(groups = {userValid.class}, message = "본인인증용 전화번호는 필수 입니다.")
    @Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$")
    private String phone;

    private String fileNm;

    private String userStatus;
}
