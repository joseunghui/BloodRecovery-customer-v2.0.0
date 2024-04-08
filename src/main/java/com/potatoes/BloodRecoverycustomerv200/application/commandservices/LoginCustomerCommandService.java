package com.potatoes.BloodRecoverycustomerv200.application.commandservices;

import com.potatoes.BloodRecoverycustomerv200.domain.model.aggregates.Customer;
import com.potatoes.BloodRecoverycustomerv200.domain.model.commands.FindCustomerMyInfoCommand;
import com.potatoes.BloodRecoverycustomerv200.domain.model.commands.LoginCustomerCommand;
import com.potatoes.BloodRecoverycustomerv200.domain.repository.CustomerRepository;
import com.potatoes.BloodRecoverycustomerv200.domain.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginCustomerCommandService {

    private final CustomerRepository customerRepository;

    /**
     * 로그인
     * @param command
     * @return
     */
    public String loginUser(LoginCustomerCommand command) {

        Customer customer = new Customer(command);
        Optional<Customer> result = customerRepository.findCustomerByUserId(customer.getUserId());

        // 검증 -> 존재하지 않는 아이디
        if (!result.isPresent()) {
            throw new IllegalStateException("가입된 회원이 아닙니다.");
        } else {
            if (!customer.getPassword().equals(result.get().getPassword()))
                throw new IllegalStateException("비밀번호가 틀렸습니다.");
        }
        return result.get().getCid();
    }

    /**
     * ID 찾기
     * @param command
     * @return
     */
    public Object findCustomerUserId(FindCustomerMyInfoCommand command) {

        Customer customer = new Customer(command);
        Optional<Customer> result = customerRepository.findCustomerByPhone(customer.getPhone());

        if (result.get().getName().equals(customer.getName()))
            return result.get();

        return null;
    }
}
