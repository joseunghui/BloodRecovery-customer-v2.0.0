package com.potatoes.BloodRecoverycustomerv200.domain.repository;

import com.potatoes.BloodRecoverycustomerv200.domain.model.aggregates.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor // 의존관계 주입을 이 방법으로 사용. 코드 깔끔!
public class CustomerRepository {

    private final EntityManager entityManager;

    // 회원 저장 로직
    public void save(Customer customer) {
        entityManager.persist(customer);
    }

    // 회원 조회 로직
    public Customer findOne(Long id) {
        return entityManager.find(Customer.class, id);
    }

    public Customer getMyInfo(String cid) {
        return entityManager.find(Customer.class, cid);
    }

    // 회원 목록 로직
    public List<Customer> findALl() {
        return entityManager.createQuery(
                "select c " +
                        "from Customer c",
                        Customer.class)
                .getResultList();
    }

    // 로그인
    public Customer defaultLogin(String userId) {
        return entityManager.createQuery(
                "select c " +
                        "from Customer c " +
                        "where c.userId = :userId",
                        Customer.class).getSingleResult();
    }

    // 닉네임으로 회원 검색
    public List<Customer> findByUserId(String userId) {
        return entityManager.createQuery("select c from Customer c where c.userId = :userId", Customer.class)
                .setParameter("userId", userId)
                .getResultList();
    }
    
    // 닉네임으로 회원 검색
    public List<Customer> findByNickname(String nickname) {
        return entityManager.createQuery("select c from Customer c where c.nickname = :nickname", Customer.class)
                .setParameter("nickname", nickname)
                .getResultList();
    }
}
