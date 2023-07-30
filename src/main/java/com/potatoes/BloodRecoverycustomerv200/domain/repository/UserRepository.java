package com.potatoes.BloodRecoverycustomerv200.domain.repository;

import com.potatoes.BloodRecoverycustomerv200.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 로그인
     *
     * @param userId
     * @param password
     */
    void loginUser(String userId, String password);
}
