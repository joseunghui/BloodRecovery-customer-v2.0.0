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

    private String cid;

    @NotBlank(groups = {userValid.class}, message = "닉네임은 필수입니다.")
    @NotNull(groups = {userValid.class}, message = "닉네임은 필수입니다.")
    private String nickname;

    @NotBlank(groups = {userValid.class}, message = "닉네임 중복 확인은 필수입니다.")
    @NotNull(groups = {userValid.class}, message = "닉네임 중복 확인은 필수입니다.")
    private boolean IsCorrectNickname;

    private String email;

    @NotBlank(groups = {userValid.class}, message = "본인인증용 전화번호는 필수 입니다.")
    @NotNull(groups = {userValid.class}, message = "본인인증용 전화번호는 필수 입니다.")
    @Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$")
    private String phone;

    private String fileNm;
}
