package com.potatoes.BloodRecoverycustomerv200.domain.repository;

import com.potatoes.BloodRecoverycustomerv200.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
