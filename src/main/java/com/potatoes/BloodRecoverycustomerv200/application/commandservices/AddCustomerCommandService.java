package com.potatoes.BloodRecoverycustomerv200.application.commandservices;

import com.potatoes.BloodRecoverycustomerv200.domain.model.aggregates.Customer;
import com.potatoes.BloodRecoverycustomerv200.domain.model.commands.AddCustomerCommand;
import com.potatoes.BloodRecoverycustomerv200.domain.repository.CustomerRepository;
import com.potatoes.BloodRecoverycustomerv200.domain.service.CustomerService;
import com.potatoes.BloodRecoverycustomerv200.interfaces.rest.dto.TwilioMessageFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddCustomerCommandService {

    private final CustomerRepository customerRepository;

    /**
     * 회원 가입
     * @param command
     * @return
     */
    public String addNewCustomer(AddCustomerCommand command) {

        Customer customer = new Customer(command);

        customerRepository.findCustomerByCid(customer.getCid()).ifPresent( m -> {
            throw new IllegalStateException("이미 존재하는 회원 입니다.");
        });
        return customerRepository.save(customer).getCid();
    }

    /**
     * ID 중복 확인
     *
     * @param userId
     * @return
     */
    public boolean isDuplicateId(String userId) {

        customerRepository.findCustomerByUserId(userId).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원 입니다.");
        });
        return true;
    }

    /**
     * 닉네임 중복 확인
     *
     * @param nickname
     * @return
     */
    public boolean isDuplicateNickname(String nickname) {

        customerRepository.findCustomerByNickname(nickname).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원 입니다.");
        });
        return true;
    }

    public String isExistCustomerPhoneNumber(String phone) {
        return customerRepository.findCustomerByPhone(phone).get().getName();
    }

    /**
     * 실명 인증용 SMS 번호 발송 확인
     *
     * @param phone
     * @param inputMessage
     * @return
     */
    public boolean isValidPersonalNumber(String phone, String inputMessage) {
        TwilioMessageFormDto dto = TwilioMessageFormDto.builder().build();

        if (dto.getUserPhoneNumber().equals(phone) && dto.getUserPhoneValidCode().equals(inputMessage))
            return true;

        return false;
    }
}