package com.potatoes.BloodRecoverycustomerv200.interfaces.rest.controller;

import com.potatoes.BloodRecoverycustomerv200.application.commandservices.AddCustomerCommandService;
import com.potatoes.BloodRecoverycustomerv200.application.commandservices.LoginCustomerCommandService;
import com.potatoes.BloodRecoverycustomerv200.application.commandservices.ModifyCustomerCommandService;
import com.potatoes.BloodRecoverycustomerv200.domain.model.aggregates.Customer;
import com.potatoes.BloodRecoverycustomerv200.domain.model.commands.AddCustomerCommand;
import com.potatoes.BloodRecoverycustomerv200.enums.auth.CustomerAuthEnum;
import com.potatoes.BloodRecoverycustomerv200.infrastructure.rest.dto.CustomerValidation;
import com.potatoes.BloodRecoverycustomerv200.infrastructure.twilio.SendSMSTwilio;
import com.potatoes.BloodRecoverycustomerv200.interfaces.rest.dto.AddCustomerFormDto;
import com.potatoes.BloodRecoverycustomerv200.interfaces.rest.dto.ModifyCustomerFormDto;
import com.potatoes.BloodRecoverycustomerv200.interfaces.rest.mapper.AddCustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.potatoes.BloodRecoverycustomerv200.interfaces.rest.constants.CustomerWebUrl.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(CUSTOMER)
public class CustomerController extends BaseController {
    private final AddCustomerCommandService addCustomerCommandService;
    private final LoginCustomerCommandService loginCustomerCommandService;
    private final ModifyCustomerCommandService modifyCustomerCommandService;

    private final AddCustomerMapper addCustomerMapper;

    /**
     * 회원 가입
     * 
     * @param form
     * @return
     */
    @PostMapping(CUSTOMER_ADD)
    public ResponseEntity<Object> addNewCustomer(
            @Validated @RequestBody AddCustomerFormDto form) throws Exception {

        // 회원가입 (신규 등록)
        AddCustomerCommand command = addCustomerMapper.addNewCustomerDtoToCommand(form);
        addCustomerCommandService.addNewCustomer(command);

        return new ResponseEntity<>(
                getSuccessHeader(command.getCid()),
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
                .isValid(addCustomerCommandService.isDuplicateId(userId)).build();

        return new ResponseEntity<>(
                valid,
                getSuccessHeader(null),
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
                .isValid(addCustomerCommandService.isDuplicateNickname(nickname)).build();

        return new ResponseEntity<>(
                valid,
                getSuccessHeader(null),
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
    @PostMapping(CUSTOMER_BEFORE_PERSONAL_CHECK)
    public ResponseEntity<Object> isValidPersonalNumber(
            @RequestHeader("cid") String cid,
            @Validated @RequestParam String phone,
            @Validated @RequestParam Long sign) {

        if (Long.toString(sign).equals(String.valueOf(CustomerAuthEnum.NEW_CUSTOMER))) {
            // SMS 전송
            String msg = SendSMSTwilio.sendMessage(phone);
        } else if (Long.toString(sign).equals(String.valueOf(CustomerAuthEnum.OLD_CUSTOMER))) {
            //TODO 입력한 전화번호가 기존 회원 번호와 일치한지 확인하는 로직 작성해야함

        }

        return new ResponseEntity<>(
                getSuccessHeader(null),
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
                .isValid(addCustomerCommandService.isValidPersonalNumber(phone, inputMessage)).build();

        return new ResponseEntity<>(
                valid,
                getSuccessHeader(null),
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

        String getCustomerCidValue = "";
        try {
            // 해당 회원 username 값으로 Member 가져오기
            Optional<Customer> passLoginUserInfo = Optional.of(loginCustomerCommandService.loginUser(userId, password));
            getCustomerCidValue = passLoginUserInfo.get().getCid();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(
                getSuccessHeader(getCustomerCidValue),
                HttpStatus.OK
        );
    }

    //TODO Social 로그인 기능 추가해야함


    /**
     * 회원 정보 상세 보기 (마이페이지)
     * @param cid
     * @return
     */
    @GetMapping(CUSTOMER_EDIT)
    public ResponseEntity<Object> getCustomerInfo(@RequestHeader("cid") String cid, Model model) {

        Optional<Customer> getMyInfo = Optional.of(modifyCustomerCommandService.getCustomerInfo(cid));
        model.addAttribute("data", getMyInfo);

        return new ResponseEntity<>(
                getSuccessHeader(cid),
                HttpStatus.OK
        );
    }

    @PostMapping(CUSTOMER_EDIT)
    public ResponseEntity<Object> editCustomerInfo(@Validated @RequestBody ModifyCustomerFormDto form) {
        //TODO 회원정보수정 API 작성

        return new ResponseEntity<>(
                getSuccessHeader(null),
                HttpStatus.OK
        );
    }

    ResponseEntity<?> setReturnEntity(HttpStatus status, Optional<?> value) {
        return new ResponseEntity<>(
                getSuccessHeader(String.valueOf(value)),
                status.is2xxSuccessful() ? HttpStatus.OK : HttpStatus.resolve(status.value())
        );
    }

}
