package com.potatoes.BloodRecoverycustomerv200.application.commandservices;

import com.potatoes.BloodRecoverycustomerv200.domain.model.aggregates.Customer;
import com.potatoes.BloodRecoverycustomerv200.domain.model.commands.FindCustomerMyInfoCommand;
import com.potatoes.BloodRecoverycustomerv200.domain.model.commands.LoginCustomerCommand;
import com.potatoes.BloodRecoverycustomerv200.domain.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginCustomerCommandService {

    private final CustomerService customerService;

    /**
     * 로그인
     * @param command
     * @return
     */
    public String loginUser(LoginCustomerCommand command) {
        Customer customer = new Customer(command);
        return customerService.defaultLogin(customer).getCid();
    }

    /**
     * ID 찾기
     * @param command
     * @return
     */
    public Object findCustomerUserId(FindCustomerMyInfoCommand command) {
        Customer customer = new Customer(command);
        return customerService.findMyUserId(customer);
    }
}
