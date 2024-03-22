package com.potatoes.BloodRecoverycustomerv200.application.commandservices;

import com.potatoes.BloodRecoverycustomerv200.domain.model.aggregates.Customer;
import com.potatoes.BloodRecoverycustomerv200.domain.model.commands.ModifyCustomerCommand;
import com.potatoes.BloodRecoverycustomerv200.domain.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModifyCustomerCommandService {

    private final CustomerService customerService;

    /**
     * 회원 정보 상세 보기 (마이페이지)
     * @param cid
     * @return
     */
    public Customer getCustomerInfo(String cid) {
        Customer customer = customerService.getMyInfo(cid);
        return customer;
    }

    public Customer updateCustomerInfo(ModifyCustomerCommand command) {
        Customer customer = new Customer(command);
        return customerService.updateMyInfo(customer);
    }
}
