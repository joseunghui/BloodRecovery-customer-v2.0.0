package com.potatoes.BloodRecoverycustomerv200.application.commandservices;

import com.potatoes.BloodRecoverycustomerv200.domain.model.aggregates.Customer;
import com.potatoes.BloodRecoverycustomerv200.domain.model.commands.CustomerCommand;
import com.potatoes.BloodRecoverycustomerv200.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerCommandService {
    private final CustomerRepository customerRepository;

    /**
     * 회원 가입
     *
     * @param command
     * @return
     */
    public Customer addUser(CustomerCommand command) {

        Customer customer = new Customer(command);
        customerRepository.save(customer);
        return customer;
    }

    /**
     * ID 중복 확인
     *
     * @param userId
     * @return
     */
    public boolean isDuplicateId(String userId) {
        Customer customer = customerRepository.findByUserId(userId);
        if (customer.getUserId().isEmpty()) {
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
        Customer customer = customerRepository.findByNickname(nickname);
        if (customer.getNickname().isEmpty()) {
            return true;
        }
        return false;
    }



    /**
     * 로그인
     *
     * @param userId
     */
    public Customer loginUser(String userId) {
        Customer customer = customerRepository.findByUserId(userId);

        // validation
        if (customer.getUserId().equals(null)) {
            try {


                throw new Exception(userId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return customer;
    }

}