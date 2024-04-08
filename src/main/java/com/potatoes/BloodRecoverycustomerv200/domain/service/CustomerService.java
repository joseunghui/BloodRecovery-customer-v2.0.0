package com.potatoes.BloodRecoverycustomerv200.domain.service;

import com.potatoes.BloodRecoverycustomerv200.domain.model.aggregates.Customer;
import com.potatoes.BloodRecoverycustomerv200.domain.repository.CustomerRepository;
import com.potatoes.BloodRecoverycustomerv200.enums.auth.CustomerAuthStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true) // 조회 기능 사용 시 성능 최적화
@RequiredArgsConstructor // 의존관계 주입을 이 방법으로 사용. 코드 깔끔!
public class CustomerService {

    private final CustomerRepository customerRepository;

    /**
     * CID로 회원 조회
     * @param cid
     * @return
     */
    public Customer getCustomerInfoByCID(String cid) {
        return customerRepository.findCustomerByCid(cid).get();
    }

    /**
     * 유효 회원 여부 조회
     * @param cid
     * @return
     */
    public boolean checkValidCustomerByCid(String cid) {

        Optional<Customer> result = customerRepository.findCustomerByCid(cid);

        if (result.get().getUserStatus().equals(CustomerAuthStatus.CUSTOMER_STATUS_VAL))
            return true;

        return false;
    }

}
