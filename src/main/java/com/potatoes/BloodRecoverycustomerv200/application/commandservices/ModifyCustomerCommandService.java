package com.potatoes.BloodRecoverycustomerv200.application.commandservices;

import com.potatoes.BloodRecoverycustomerv200.domain.model.aggregates.Customer;
import com.potatoes.BloodRecoverycustomerv200.domain.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModifyCustomerCommandService {

    private final CustomerService customerService;

    // 암호화 security
    private BCryptPasswordEncoder encoder;


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
