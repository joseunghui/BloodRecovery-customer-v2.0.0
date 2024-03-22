package com.potatoes.BloodRecoverycustomerv200.application.commandservices;

import com.potatoes.BloodRecoverycustomerv200.domain.model.aggregates.Customer;
import com.potatoes.BloodRecoverycustomerv200.domain.model.commands.AddCustomerCommand;
import com.potatoes.BloodRecoverycustomerv200.domain.service.CustomerService;
import com.potatoes.BloodRecoverycustomerv200.infrastructure.rest.dto.CustomerPhoneNumberValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.potatoes.BloodRecoverycustomerv200.infrastructure.twilio.SendSMSTwilio.sendMessage;

@Service
@RequiredArgsConstructor
public class AddCustomerCommandService {
    private final CustomerService customerService;

    /**
     * 회원 가입
     *
     * @param command
     * @return
     */
    public String addNewCustomer(AddCustomerCommand command) {
        Customer customer = new Customer(command);
        return customerService.join(customer);
    }

    /**
     * ID 중복 확인
     *
     * @param userId
     * @return
     */
    public boolean isDuplicateId(String userId) {
        if (customerService.checkDuplicateCustomerUserId(userId)) {
            return true;
        }
        return false;
    }

    /**
     * 닉네임 중복 확인
     *
     * @param nickname
     * @return
     */
    public boolean isDuplicateNickname(String nickname) {
        if (customerService.checkDuplicateCustomerNickname(nickname)) {
            return true;
        }
        return false;
    }

    /**
     * 실명 인증용 SMS 번호 발송 확인
     *
     * @param phone
     * @param inputMessage
     * @return
     */
    public boolean isValidPersonalNumber(String phone, String inputMessage) {
        CustomerPhoneNumberValidation validation = CustomerPhoneNumberValidation.builder().build();

        if (validation.getPhone().equals(phone) && validation.getMessage().equals(inputMessage))
            return true;

        return false;
    }
}