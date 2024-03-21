package com.potatoes.BloodRecoverycustomerv200.application.commandservices;

import com.potatoes.BloodRecoverycustomerv200.domain.model.aggregates.Customer;
import com.potatoes.BloodRecoverycustomerv200.domain.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginCustomerCommandService {

    private final CustomerService customerService;

    // 암호화 security
    private BCryptPasswordEncoder encoder;

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
}
