package com.potatoes.BloodRecoverycustomerv200.domain.repository;

import com.potatoes.BloodRecoverycustomerv200.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 로그인 (유저 아이디로 유저 찾기)
     *
     * @param userId
     */
    User findByUserId(String userId);
}
