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
     * 회원가입
     * @param customer
     * @return
     */
    public String join(Customer customer) {
        customerRepository.findCustomerByCid(customer.getCid()).ifPresent( m -> {
            throw new IllegalStateException("이미 존재하는 회원 입니다.");
        });
        return customerRepository.save(customer).getCid();
    }

    // 중복 회원 검사


    public boolean checkDuplicateCustomerUserId(String userId) {
        customerRepository.findCustomerByUserId(userId).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원 입니다.");
        });
        return true;
    }

    public boolean checkDuplicateCustomerNickname(String nickname) {
        customerRepository.findCustomerByNickname(nickname).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원 입니다.");
        });
        return true;
    }

    public String checkDuplicateCustomerPhone(String phone) {
        return customerRepository.findCustomerByPhone(phone).get().getName();
    }

    /**
     * 일반 로그인
     * @param customer
     * @return
     */
    public Customer defaultLogin(Customer customer) {

        Optional<Customer> result = customerRepository.findCustomerByUserId(customer.getUserId());
        
        // 검증 -> 존재하지 않는 아이디
        if (!result.isPresent()) {
            throw new IllegalStateException("가입된 회원이 아닙니다.");
        } else {
            if (!customer.getPassword().equals(result.get().getPassword()))
                throw new IllegalStateException("비밀번호가 틀렸습니다.");
        }
        return result.get();
    }

    public Customer getMyInfo(String cid) {
        return customerRepository.findCustomerByCid(cid).get();
    }

    public Customer updateMyInfo(Customer customer) {

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


    public boolean checkValidCustomerByCid(String cid) {

        Optional<Customer> result = customerRepository.findCustomerByCid(cid);

        if (result.get().getUserStatus().equals(CustomerAuthStatus.CUSTOMER_STATUS_VAL))
            return true;

        return false;
    }

    public Object findMyUserId(Customer customer) {

        Optional<Customer> result = customerRepository.findCustomerByPhone(customer.getPhone());

        if (result.get().getName().equals(customer.getName()))
            return result.get();

        return null;
    }
}
