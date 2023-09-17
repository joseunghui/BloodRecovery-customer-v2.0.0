package com.potatoes.BloodRecoverycustomerv200.interfaces.rest.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {

    @InjectMocks
    private CustomerMapper customerMapper;

    private BCryptPasswordEncoder encoder;

    @Test
    @DisplayName("")
    void addNewCustomerDtoToCommandMapperTest() {
    }

    @Test
    void afterMappingToCommandMapperTest() {
    }

    @Test
    void getPasswordEncodingMapperTest() {
    }
}