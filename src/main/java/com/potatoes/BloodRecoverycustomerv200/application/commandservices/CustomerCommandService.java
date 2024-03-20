package com.potatoes.BloodRecoverycustomerv200.application.commandservices;

import com.potatoes.BloodRecoverycustomerv200.domain.model.aggregates.Customer;
import com.potatoes.BloodRecoverycustomerv200.domain.model.commands.CustomerCommand;
import com.potatoes.BloodRecoverycustomerv200.domain.repository.CustomerRepository;
import com.potatoes.BloodRecoverycustomerv200.domain.service.CustomerService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.potatoes.BloodRecoverycustomerv200.infrastructure.twilio.SendSMSTwilio.sendMessage;

@Service
@RequiredArgsConstructor
public class CustomerCommandService {
    private final CustomerService customerService;

    // 암호화 security
    private BCryptPasswordEncoder encoder;

    /**
     * 회원 가입
     *
     * @param command
     * @return
     */
    public Customer addNewCustomer(CustomerCommand command) {
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


    /**
     * 로그인
     *
     * @param userId
     */
    public Customer loginUser(String userId, String password) {
        // 아이디로 회원 정보 가져오기
        Customer customer = customerService.defaultLogin(userId);

        // validation
        if (customer.getUserId().equals(userId)) {
            try {
                // 입력한 비밀번호와 DB 비밀번호가 동일한지 확인
                if (customer.getPassword().equals(encoder.encode(password))) {
                    return customer;
                } else throw new SecurityException("비밀번호가 일치하지 않습니다.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else throw new NullPointerException("가입된 회원이 아닙니다.");
        return null;
    }

    /**
     * 회원 정보 상세 보기 (마이페이지)
     * @param cid
     * @return
     */
    public Customer getCustomerInfo(String cid) {
        Customer customer = customerService.getMyInfo(cid);
        return customer;
    }
}