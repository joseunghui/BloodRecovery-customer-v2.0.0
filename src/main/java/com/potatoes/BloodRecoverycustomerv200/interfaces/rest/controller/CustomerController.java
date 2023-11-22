package com.potatoes.BloodRecoverycustomerv200.interfaces.rest.controller;

import com.potatoes.BloodRecoverycustomerv200.application.commandservices.CustomerCommandService;
import com.potatoes.BloodRecoverycustomerv200.config.SHA256Config;
import com.potatoes.BloodRecoverycustomerv200.domain.model.aggregates.Customer;
import com.potatoes.BloodRecoverycustomerv200.domain.model.commands.CustomerCommand;
import com.potatoes.BloodRecoverycustomerv200.enums.auth.CustomerAuthEnum;
import com.potatoes.BloodRecoverycustomerv200.infrastructure.rest.dto.CustomerValidation;
import com.potatoes.BloodRecoverycustomerv200.infrastructure.twilio.SendSMSTwilio;
import com.potatoes.BloodRecoverycustomerv200.interfaces.rest.dto.CustomerFormDto;
import com.potatoes.BloodRecoverycustomerv200.interfaces.rest.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import static com.potatoes.BloodRecoverycustomerv200.interfaces.rest.constants.CustomerWebUrl.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(CUSTOMER)
public class CustomerController extends BaseController {
    private final CustomerCommandService customerCommandService;

    private final CustomerMapper customerMapper;

    /**
     * 회원 가입
     * 
     * @param form
     * @return
     */
    @PostMapping(CUSTOMER_ADD)
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
    @GetMapping(CUSTOMER_ID_CHECK)
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
    @GetMapping(CUSTOMER_NICK_CHECK)
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
     * 실명 인증을 위한 SMS 전송
     *
     * @param phone
     * @param sign
     * @return
     */
    @PostMapping(CUSTOMER_PERSONAL_CHECK)
    public ResponseEntity<Object> isValidPersonalNumber(
            @Validated @RequestParam String phone,
            @Validated @RequestParam Long sign) {

        if (Long.toString(sign).equals(String.valueOf(CustomerAuthEnum.NEW_CUSTOMER))) {
            // SMS 전송
            SendSMSTwilio.sendMessage(phone);
        } else if (Long.toString(sign).equals(String.valueOf(CustomerAuthEnum.OLD_CUSTOMER))) {
            //TODO 입력한 전화번호가 기존 회원 번호와 일치한지 확인하는 로직 작성해야함

        }

        return new ResponseEntity<>(
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
    @PostMapping(CUSTOMER_PERSONAL_CHECK)
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
    @GetMapping(CUSTOMER_LOGIN)
    public ResponseEntity<String> loginCustomer(
            @RequestParam String userId, @RequestParam String password) {

        try {
            // 해당 회원 username 값으로 Member 가져오기
            Optional<Customer> passLoginUserInfo = Optional.of(customerCommandService.loginUser(userId, password));

            // 비번 동일한지 확인(암호화 안한 버전)
            if (passLoginUserInfo.equals(null)) {
                throw new NullPointerException("일치하는 회원 정보가 없습니다.");
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
    @GetMapping(CUSTOMER_EDIT)
    public ResponseEntity<Object> getCustomerInfo(@RequestParam String userId) {

        // 회원정보 가져오기
        Optional<Customer> getCustomerInfo = Optional.of(customerCommandService.getCustomerInfo(userId));

        return new ResponseEntity<>(
                getSuccessHeader(),
                HttpStatus.OK
        );
    }

    @PostMapping(CUSTOMER_EDIT)
    public ResponseEntity<Object> editCustomerInfo(@Validated @RequestBody CustomerFormDto form) {
        //TODO 회원정보수정 API 작성

        return new ResponseEntity<>(
                getSuccessHeader(),
                HttpStatus.OK
        );
    }

}
