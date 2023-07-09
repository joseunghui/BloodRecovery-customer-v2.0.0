package com.potatoes.BloodRecoverycustomerv200.interfaces.rest.controller;

import com.potatoes.BloodRecoverycustomerv200.application.commandservices.AddUserCommandService;
import com.potatoes.BloodRecoverycustomerv200.domain.model.commands.AddUserCommand;
import com.potatoes.BloodRecoverycustomerv200.interfaces.rest.dto.AddUserFormDto;
import com.potatoes.BloodRecoverycustomerv200.interfaces.rest.mapper.AddUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.potatoes.BloodRecoverycustomerv200.interfaces.rest.constants.UserWebUrl.USER;
import static com.potatoes.BloodRecoverycustomerv200.interfaces.rest.constants.UserWebUrl.USER_ADD;

@RestController
@RequiredArgsConstructor
@RequestMapping(USER)
public class AddUserController {

    @Autowired
    private AddUserCommandService addUserCommandService;

    @Autowired
    private final AddUserMapper addUserMapper;

    @PostMapping(USER_ADD)
    public ResponseEntity add(
            @Validated @ModelAttribute("form") AddUserFormDto form) {

        // 회원가입 등록
        AddUserCommand command = addUserMapper.dtoToCommand(form);
        addUserCommandService.addUser(command);

        return new ResponseEntity<>(
                getSuccessHeader(),
                HttpStatus.OK
        );
    }

    // Http header 이용 에러 설정
    protected HttpHeaders getSuccessHeader() {
        HttpHeaders headers = new HttpHeaders();

        headers.set("resultCode", "200");
        headers.set("resultMessage", "청상 처리 하였습니다.");
        headers.set("userStatus", "R");

        return headers;
    }
}
