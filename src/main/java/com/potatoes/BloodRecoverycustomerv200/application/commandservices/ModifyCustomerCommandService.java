package com.potatoes.BloodRecoverycustomerv200.application.commandservices;

import com.potatoes.BloodRecoverycustomerv200.domain.model.aggregates.Customer;
import com.potatoes.BloodRecoverycustomerv200.domain.model.commands.ModifyCustomerCommand;
import com.potatoes.BloodRecoverycustomerv200.domain.repository.CustomerRepository;
import com.potatoes.BloodRecoverycustomerv200.domain.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ModifyCustomerCommandService {

    private final CustomerService customerService;
    private final CustomerRepository customerRepository;

    /**
     * 회원 정보 상세 보기 (마이페이지)
     * @param cid
     * @return
     */
    public Customer getCustomerInfo(String cid) {
        Customer customer = customerService.getCustomerInfoByCID(cid);
        return customer;
    }

    public Customer updateCustomerInfo(ModifyCustomerCommand command) {
        Customer customer = new Customer(command);
        // customerRepository.updateMyInfo(customer);
        // return getMyInfo(customer.getCid());

        // 여기서 member는 변경 전 회원 정보
        Optional<Customer> beforeData = customerRepository.findCustomerByCid(customer.getCid());

        // 검증 2 -> 회원 아이디는 변경 불가
        if (!beforeData.get().getUserId().equals(customer.getUserId())) {
            throw new IllegalStateException("오류가 발생했습니다.");
        } else {
            // 회원 아이디가 동일하다면 회원 정보 변경

        }

        return null;
    }
}
