package com.potatoes.BloodRecoverycustomerv200.application.commandservices;

import com.potatoes.BloodRecoverycustomerv200.domain.repository.CustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class CustomerCommandServiceTest {

    @InjectMocks
    private CustomerCommandService customerCommandService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    @DisplayName("회원 가입 테스트")
    public void addCustomerTest() {

        // given


        // when


        //then
        //Assertions.assertEquals();
    }





}