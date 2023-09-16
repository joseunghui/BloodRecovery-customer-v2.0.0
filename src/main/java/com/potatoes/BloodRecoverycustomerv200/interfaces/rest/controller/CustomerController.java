package com.potatoes.BloodRecoverycustomerv200.interfaces.rest.controller;

import com.amazonaws.services.kms.model.NotFoundException;
import com.potatoes.BloodRecoverycustomerv200.application.commandservices.CustomerCommandService;
import com.potatoes.BloodRecoverycustomerv200.domain.model.aggregates.Customer;
import com.potatoes.BloodRecoverycustomerv200.domain.model.commands.CustomerCommand;
import com.potatoes.BloodRecoverycustomerv200.infrastructure.rest.dto.CustomerValidation;
import com.potatoes.BloodRecoverycustomerv200.interfaces.rest.dto.CustomerFormDto;
import com.potatoes.BloodRecoverycustomerv200.interfaces.rest.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.potatoes.BloodRecoverycustomerv200.interfaces.rest.constants.CustomerWebUrl.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(USER)
public class CustomerController extends BaseController {
    private final CustomerCommandService customerCommandService;

    private final CustomerMapper customerMapper;

    /**
     * 회원 가입
     * 
     * @param form
     * @return
     */
    @PostMapping(USER_ADD)
    public ResponseEntity<Object> addNewCustomer(
            @Validated @RequestBody CustomerFormDto form) {

        // 회원가입 (신규 등록)
        CustomerCommand command = customerMapper.addNewCustomerDtoToCommand(form);
        customerCommandService.addNewCustomer(command);

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
    public ResponseEntity<Object> isDuplicateId(
            @Validated @RequestParam String userId) {

        // 유저 아이디 중복 확인
        CustomerValidation valid = CustomerValidation.builder()
                .isValid(customerCommandService.isDuplicateId(userId)).build();

        return new ResponseEntity<>(
                valid,
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
    public ResponseEntity<Object> isDuplicateNickname(
            @Validated @RequestParam String nickname) {

        // 닉네임 중복확인
        CustomerValidation valid = CustomerValidation.builder()
                .isValid(customerCommandService.isDuplicateNickname(nickname)).build();

        return new ResponseEntity<>(
                valid,
                getSuccessHeader(),
                HttpStatus.OK
        );
    }

    /**
     * 실명 인증용 SMS 번호 발송 확인
     *
     * @param phone
     * @param inputMessage
     * @return
     */
    @PostMapping(USER_PERSONAL_CHECK)
    public ResponseEntity<Object> isValidPersonalNumber(
            @Validated @RequestParam String phone, @RequestParam String inputMessage) {

        // SMS 실명 인증
        CustomerValidation valid = CustomerValidation.builder()
                .isValid(customerCommandService.isValidPersonalNumber(phone, inputMessage)).build();

        return new ResponseEntity<>(
                valid,
                getSuccessHeader(),
                HttpStatus.OK
        );
    }

    /**
     * 일반 로그인
     *
     * @param userId
     * @param password
     * @return
     */
    @GetMapping(USER_LOGIN)
    public ResponseEntity<String> loginCustomer(
            @RequestParam String userId, @RequestParam String password) {

        try {
            // 해당 회원 username 값으로 Member 가져오기
            Optional<Customer> passLoginUserInfo = Optional.of(customerCommandService.loginUser(userId, password));

            // 비번 동일한지 확인(암호화 안한 버전)
            if (passLoginUserInfo.isEmpty()) {
                throw new NotFoundException("일치하는 회원 정보가 없습니다.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(
                getSuccessHeader(),
                HttpStatus.OK
        );
    }

    //TODO Social 로그인 기능 추가해야함


    /**
     * 회원 정보 상세 보기 (마이페이지)
     *
     * @param userId
     * @return
     */
    @GetMapping(USER_EDIT)
    public ResponseEntity<Object> getCustomerInfo(@RequestParam String userId) {

        // 회원정보 가져오기
        Optional<Customer> getCustomerInfo = Optional.of(customerCommandService.getCustomerInfo(userId));

        return new ResponseEntity<>(
                getSuccessHeader(),
                HttpStatus.OK
        );
    }

    @PostMapping(USER_EDIT)
    public ResponseEntity<Object> editCustomerInfo(@Validated @RequestBody CustomerFormDto form) {
        //TODO 회원정보수정 API 작성

        return new ResponseEntity<>(
                getSuccessHeader(),
                HttpStatus.OK
        );
    }

}
