package com.potatoes.BloodRecoverycustomerv200.interfaces.rest.controller;

import com.potatoes.BloodRecoverycustomerv200.application.commandservices.UserCommandService;
import com.potatoes.BloodRecoverycustomerv200.domain.model.aggregates.User;
import com.potatoes.BloodRecoverycustomerv200.domain.model.commands.AddUserCommand;
import com.potatoes.BloodRecoverycustomerv200.interfaces.rest.dto.AddUserFormDto;
import com.potatoes.BloodRecoverycustomerv200.interfaces.rest.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.potatoes.BloodRecoverycustomerv200.interfaces.rest.constants.UserWebUrl.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(USER)
public class UserController extends BaseController {
    private final UserCommandService userCommandService;

    private final UserMapper userMapper;

    /**
     * 회원 가입
     * 
     * @param form
     * @return
     */
    @PostMapping(USER_ADD)
    public ResponseEntity<Object> addUser(
            @Validated @RequestBody AddUserFormDto form) {

        // 회원가입 (신규 등록)
        AddUserCommand command = userMapper.dtoToCommand(form);
        userCommandService.addUser(command);

        return new ResponseEntity<>(
                getSuccessHeader(),
                HttpStatus.OK
        );
    }

    /**
     * ID 중복 확인
     *
     * @param userId
     * @return
     */
    @GetMapping(USER_ID_CHECK)
    public ResponseEntity<Boolean> isDuplicateId(
            @Validated @RequestParam String userId) {

        // 유저 아이디 중복 확인
        boolean isValid = userCommandService.isDuplicateId(userId);

        return new ResponseEntity<>(
                isValid,
                getSuccessHeader(),
                HttpStatus.OK
        );
    }

    /**
     * 닉네임 중복확인
     *
     * @param nickname
     * @return
     */
    @GetMapping(USER_NICK_CHECK)
    public ResponseEntity<Boolean> isDuplicateNickname(
            @Validated @RequestParam String nickname) {

        // 닉네임 중복확인
        boolean isValid = userCommandService.isDuplicateNickname(nickname);

        return new ResponseEntity<>(
                isValid,
                getSuccessHeader(),
                HttpStatus.OK
        );
    }

    //TODO 실명인증 API 갖고와서 적용시키고 회원가입 개발 완료 해야함

    /**
     * 일반 로그인
     *
     * @param userId
     * @param password
     * @return
     */
    @GetMapping(USER_LOGIN)
    public ResponseEntity<String> loginUser(
            @RequestParam String userId, @RequestParam String password) {

        // 해당 회원 username 값으로 Member 가져오기
        Optional<User> user = Optional.of(userCommandService.loginUser(userId));

        // 비번 동일한지 확인(암호화 안한 버전)
        if (!user.get().getPassword().equals(password)) {
            try {
                throw new Exception(userId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return new ResponseEntity<>(
                getSuccessHeader(),
                HttpStatus.OK
        );
    }

    //TODO Social 로그인 기능 추가해야함



}
