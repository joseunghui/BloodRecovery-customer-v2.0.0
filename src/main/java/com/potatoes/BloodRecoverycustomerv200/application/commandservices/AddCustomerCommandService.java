package com.potatoes.BloodRecoverycustomerv200.application.commandservices;

import com.potatoes.BloodRecoverycustomerv200.domain.model.aggregates.Customer;
import com.potatoes.BloodRecoverycustomerv200.domain.model.commands.AddCustomerCommand;
import com.potatoes.BloodRecoverycustomerv200.domain.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.potatoes.BloodRecoverycustomerv200.infrastructure.twilio.SendSMSTwilio.sendMessage;

@Service
@RequiredArgsConstructor
public class AddCustomerCommandService {
    private final CustomerService customerService;

    // 암호화 security
    private BCryptPasswordEncoder encoder;

    /**
     * 회원 가입
     *
     * @param command
     * @return
     */
    public Customer addNewCustomer(AddCustomerCommand command) {
        Customer customer = new Customer(command);
        customerService.join(customer);
        return customer;
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
        String sentMessage = String.valueOf(sendMessage(phone));

        if (sentMessage.equals(inputMessage)) {
            return true;
        }
        return false;
    }
}