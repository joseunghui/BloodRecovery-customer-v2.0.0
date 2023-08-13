package com.potatoes.BloodRecoverycustomerv200.domain.repository;

import com.potatoes.BloodRecoverycustomerv200.domain.model.aggregates.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /**
     * 로그인 (유저 아이디로 유저 찾기)
     *
     * @param userId
     */
    Customer findByUserId(String userId);

    /**
     * 닉네임 중복 확인
     *
     * @param nickname
     * @return
     */
    Customer findByNickname(String nickname);
}
