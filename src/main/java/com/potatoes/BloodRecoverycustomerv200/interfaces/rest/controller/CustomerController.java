package com.potatoes.BloodRecoverycustomerv200.interfaces.rest.controller;

import com.potatoes.BloodRecoverycustomerv200.application.commandservices.AddCustomerCommandService;
import com.potatoes.BloodRecoverycustomerv200.application.commandservices.LoginCustomerCommandService;
import com.potatoes.BloodRecoverycustomerv200.application.commandservices.ModifyCustomerCommandService;
import com.potatoes.BloodRecoverycustomerv200.domain.model.aggregates.Customer;
import com.potatoes.BloodRecoverycustomerv200.domain.model.commands.AddCustomerCommand;
import com.potatoes.BloodRecoverycustomerv200.domain.model.commands.FindCustomerMyInfoCommand;
import com.potatoes.BloodRecoverycustomerv200.domain.model.commands.LoginCustomerCommand;
import com.potatoes.BloodRecoverycustomerv200.domain.model.commands.ModifyCustomerCommand;
import com.potatoes.BloodRecoverycustomerv200.domain.service.CustomerService;
import com.potatoes.BloodRecoverycustomerv200.enums.auth.CustomerAuthEnum;
import com.potatoes.BloodRecoverycustomerv200.infrastructure.rest.dto.CustomerValidation;
import com.potatoes.BloodRecoverycustomerv200.infrastructure.twilio.SendSMSTwilio;
import com.potatoes.BloodRecoverycustomerv200.interfaces.rest.dto.AddCustomerFormDto;
import com.potatoes.BloodRecoverycustomerv200.interfaces.rest.dto.ModifyCustomerFormDto;
import com.potatoes.BloodRecoverycustomerv200.interfaces.rest.mapper.AddCustomerMapper;
import com.potatoes.BloodRecoverycustomerv200.interfaces.rest.mapper.LoginCustomerMapper;
import com.potatoes.BloodRecoverycustomerv200.interfaces.rest.mapper.ModifyCustomerMapper;
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
    private final CustomerService customerService;
    private final AddCustomerMapper addCustomerMapper;
    private final LoginCustomerMapper loginCustomerMapper;
    private final ModifyCustomerMapper modifyCustomerMapper;

    /**
     * 회원 가입
     * @param form
     * @return
     */
    @PostMapping(CUSTOMER_ADD)
    public ResponseEntity<Object> addNewCustomer(
            @Validated @RequestBody AddCustomerFormDto form) throws Exception {
        AddCustomerCommand command = addCustomerMapper.addNewCustomerDtoToCommand(form);

        return new ResponseEntity<>(
                getSuccessHeader(addCustomerCommandService.addNewCustomer(command)),
                HttpStatus.OK
        );
    }

    /**
     * ID 중복 확인
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
     * @param phone
     * @param sign
     * @return
     */
    @PostMapping(CUSTOMER_BEFORE_PERSONAL_CHECK)
    public ResponseEntity<Object> isValidPersonalNumber(
            @Validated @RequestParam String userId,
            @Validated @RequestParam String name,
            @Validated @RequestParam String phone,
            @Validated @RequestParam Long sign) {
        if (Long.toString(sign).equals(String.valueOf(CustomerAuthEnum.NEW_CUSTOMER))) {

        } else if (Long.toString(sign).equals(String.valueOf(CustomerAuthEnum.OLD_CUSTOMER))) {
            if (addCustomerCommandService.isExistCustomerPhoneNumber(phone).equals(name))
                SendSMSTwilio.sendMessage(phone);
        }

        // SMS 전송
        SendSMSTwilio.sendMessage(phone);

        return new ResponseEntity<>(
                getSuccessHeader(null),
                HttpStatus.OK
        );
    }


    /**
     * 실명 인증용 SMS 번호 발송 확인
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
     * @param userId
     * @param password
     * @return
     */
    @GetMapping(CUSTOMER_LOGIN)
    public ResponseEntity<String> loginCustomer(
            @RequestParam String userId, @RequestParam String password) {
        LoginCustomerCommand command = loginCustomerMapper.loginCustomerParameterToCommand(userId, password);

        return new ResponseEntity<>(
                getSuccessHeader(loginCustomerCommandService.loginUser(command)),
                HttpStatus.OK
        );
    }
    //TODO Social 로그인 기능 추가해야함


    /**
     * ID 찾기
     * @param name
     * @param phone
     * @return
     */
    @PostMapping(CUSTOMER_FIND)
    public ResponseEntity<String> findCustomerUserId(
            @RequestParam String name, @RequestParam String phone, Model model) {

        FindCustomerMyInfoCommand command = loginCustomerMapper.findCustomerMyInfoParameterToCommand(name, phone);

        Optional<Customer> findMyId = (Optional<Customer>)loginCustomerCommandService.findCustomerUserId(command);
        model.addAttribute("userId", findMyId.get().getUserId());

        return new ResponseEntity<>(
                getSuccessHeader(null),
                HttpStatus.OK
        );
    }



    /**
     * 회원 정보 상세 보기 (마이페이지)
     * @param cid
     * @return
     */
    @GetMapping(CUSTOMER_EDIT)
    public ResponseEntity<Object> getCustomerInfo(
            @RequestHeader("cid") String cid,
            Model model) {
        Optional<Customer> getMyInfo = Optional.of(modifyCustomerCommandService.getCustomerInfo(cid));
        model.addAttribute("data", getMyInfo);

        return new ResponseEntity<>(
                getSuccessHeader(cid),
                HttpStatus.OK
        );
    }

    /**
     * 회원 정보 수정 (마이페이지)
     * @param cid
     * @param form
     * @param model
     * @return
     */
    @PostMapping(CUSTOMER_EDIT)
    public ResponseEntity<Object> editCustomerInfo(
            @RequestHeader("cid") String cid,
            @Validated @RequestBody ModifyCustomerFormDto form,
            Model model) {
        ModifyCustomerCommand command = modifyCustomerMapper.modifyCustomerToCommand(cid, form);
        Optional<Customer> updateMyInfo = Optional.of(modifyCustomerCommandService.updateCustomerInfo(command));
        model.addAttribute("data", updateMyInfo);

        return new ResponseEntity<>(
                getSuccessHeader(cid),
                HttpStatus.OK
        );
    }

    /**
     * CID로 정상 회원 여부 조회 (정상 : true, 탈퇴 : false)
     * @param cid
     * @return
     */
    @GetMapping(CUSTOMER_VALID)
    public ResponseEntity<Object> customerStatusValidation(
            @RequestHeader("cid") String cid) {

        CustomerValidation valid = CustomerValidation.builder()
                .isValid(customerService.checkValidCustomerByCid(cid)).build();

        return new ResponseEntity<>(
                valid,
                getSuccessHeader(cid),
                HttpStatus.OK
        );
    }


}
