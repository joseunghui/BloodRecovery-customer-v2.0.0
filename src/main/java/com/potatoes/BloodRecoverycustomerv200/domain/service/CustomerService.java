package com.potatoes.BloodRecoverycustomerv200.domain.service;

import com.potatoes.BloodRecoverycustomerv200.domain.model.aggregates.Customer;
import com.potatoes.BloodRecoverycustomerv200.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // 조회 기능 사용 시 성능 최적화
@RequiredArgsConstructor // 의존관계 주입을 이 방법으로 사용. 코드 깔끔!
public class CustomerService {
    private final CustomerRepository customerRepository;

    /**
     * 회원가입
     * @param customer
     * @return
     */
    @Transactional // 기본적으로 Transaction 안에서 데이터를 변경하는 것은 @Transactional 이 꼭 필요!(기본은 readOnly = false 임)
    public String join(Customer customer) {
        customerRepository.save(customer);
        return customer.getCid();
    }

    // 중복 회원 검사
    @Transactional
    public boolean checkDuplicateCustomerUserId(String userId) {
        return validateDuplicateCustomerUserId(userId);
    }

    @Transactional
    public boolean checkDuplicateCustomerNickname(String nickname) {
        return validateDuplicateCustomerNickname(nickname);
    }

    /**
     * 일반 로그인
     * @param userId
     * @return
     */
    @Transactional
    public Customer defaultLogin(String userId) {
        return customerRepository.defaultLogin(userId);
    }

    @Transactional
    public Customer getMyInfo(String cid) {
        return  customerRepository.getMyInfo(cid);
    }


    /**
     * 중복 회원 검사
     * @param userId
     * @return
     */
    private boolean validateDuplicateCustomerUserId(String userId) {

        List<Customer> findMembersByUserId = customerRepository.findByUserId(userId);
        if (!findMembersByUserId.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원 아이디 입니다.");
        } else return true;
    }

    private boolean validateDuplicateCustomerNickname(String nickname) {
        List<Customer> findMembersByUserNickname = customerRepository.findByNickname(nickname);
        if (!findMembersByUserNickname.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원 닉네임 입니다.");
        } else return true;
    }
}
