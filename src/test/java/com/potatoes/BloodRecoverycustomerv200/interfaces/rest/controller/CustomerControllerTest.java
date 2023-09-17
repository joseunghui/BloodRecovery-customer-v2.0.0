package com.potatoes.BloodRecoverycustomerv200.interfaces.rest.controller;

import com.potatoes.BloodRecoverycustomerv200.application.commandservices.CustomerCommandService;
import com.potatoes.BloodRecoverycustomerv200.domain.repository.CustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {
    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerCommandService customerCommandService;

    @Mock
    private CustomerRepository customerRepository;


    @Test
    @DisplayName("")
    void addNewCustomerControllerTest() {
    }

    @Test
    void isDuplicateIdControllerTest() {
    }

    @Test
    void isDuplicateNicknameControllerTest() {
    }

    @Test
    void isValidPersonalNumberControllerTest() {
    }

    @Test
    void loginCustomerControllerTest() {
    }

    @Test
    void getCustomerInfoControllerTest() {
    }

    @Test
    void editCustomerInfoControllerTest() {
    }
}