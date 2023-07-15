package com.potatoes.BloodRecoverycustomerv200.interfaces.rest.controller;

import com.potatoes.BloodRecoverycustomerv200.application.commandservices.UserCommandService;
import com.potatoes.BloodRecoverycustomerv200.domain.model.commands.AddUserCommand;
import com.potatoes.BloodRecoverycustomerv200.interfaces.rest.dto.AddUserFormDto;
import com.potatoes.BloodRecoverycustomerv200.interfaces.rest.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.potatoes.BloodRecoverycustomerv200.interfaces.rest.constants.UserWebUrl.USER;
import static com.potatoes.BloodRecoverycustomerv200.interfaces.rest.constants.UserWebUrl.USER_ADD;

@RestController
@RequiredArgsConstructor
@RequestMapping(USER)
public class UserController extends BaseController {
    private final UserCommandService userCommandService;

    private final UserMapper userMapper;

    @PostMapping(USER_ADD)
    public ResponseEntity<Object> addUser(
            @Validated @RequestBody AddUserFormDto form) {

        // 회원가입 등록
        AddUserCommand command = userMapper.dtoToCommand(form);
        userCommandService.addUser(command);

        return new ResponseEntity<>(
                getSuccessHeader(),
                HttpStatus.OK
        );
    }
}
