package com.potatoes.BloodRecoverycustomerv200.interfaces.rest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Getter
@Setter
@Builder
public class CustomerFormDto {
    private interface userValid{ }

    @NotBlank(groups = {userValid.class}, message = "아이디는 필수 입니다.")
    @NotNull(groups = {userValid.class}, message = "아이디는 필수 입니다.")
    private String userId;

    @NotBlank(groups = {userValid.class}, message = "이름은 필수입니다.")
    @NotNull(groups = {userValid.class}, message = "이름은 필수입니다.")
    private String name;

    @NotBlank(groups = {userValid.class}, message = "닉네임은 필수입니다.")
    @NotNull(groups = {userValid.class}, message = "닉네임은 필수입니다.")
    private String nickname;

    @NotBlank(groups = {userValid.class}, message = "비밀번호는 필수 입니다.")
    @NotNull(groups = {userValid.class}, message = "비밀번호는 필수 입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d~!@#$%^&*()+|=].{8,20}+$") //'숫자', '문자' 무조건 1개 이상, '최소 8자에서 최대 20자' 허용
    private String password;

    @NotBlank(groups = {userValid.class}, message = "비밀번호는 필수 입니다.")
    @NotNull(groups = {userValid.class}, message = "비밀번호는 필수 입니다.")
    private String bldTp;

    @NotBlank(groups = {userValid.class}, message = "비밀번호는 필수 입니다.")
    @NotNull(groups = {userValid.class}, message = "비밀번호는 필수 입니다.")
    private String address;

    @NotBlank(groups = {userValid.class}, message = "비밀번호는 필수 입니다.")
    @NotNull(groups = {userValid.class}, message = "비밀번호는 필수 입니다.")
    private String email;

    @NotBlank(groups = {userValid.class}, message = "비밀번호는 필수 입니다.")
    @NotNull(groups = {userValid.class}, message = "비밀번호는 필수 입니다.")
    private String phone;

    private MultipartFile file;
    private String fileNm;

    private String gradeSn;

    private String userStatus;

    private Date date;
}
