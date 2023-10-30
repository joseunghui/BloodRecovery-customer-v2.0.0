package com.potatoes.BloodRecoverycustomerv200.application.commandservices;

import com.potatoes.BloodRecoverycustomerv200.application.testutil.TestData;
import com.potatoes.BloodRecoverycustomerv200.domain.model.aggregates.Customer;
import com.potatoes.BloodRecoverycustomerv200.domain.model.commands.CustomerCommand;
import com.potatoes.BloodRecoverycustomerv200.domain.repository.CustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
public class CustomerCommandServiceTest {
    @InjectMocks
    private CustomerCommandService customerCommandService;

    @Mock
    private CustomerRepository customerRepository;

//    @Test
//    @DisplayName("정상적으로 회원을 생성한다.")
//    public void addCustomerCommandServiceTest() {
//
//        //given
//        CustomerCommand cmd = TestData.mockAddCustomerCommand;
//        Customer customer = TestData.mockCustomer;
//
//        given(customerRepository.save(any())).willReturn(customer);
//        given(customerRepository.findByUserId(anyString())).willReturn((Customer) Optional.empty().orElse(null));
//
//        //when
//        Customer actualCustomer = customerCommandService.addNewCustomer(cmd);
//
//        //then
//        assertAll(
//                () -> assertThat(actualCustomer.getUserId()).isEqualTo(customer.getUserId()),
//                () -> assertThat(actualCustomer.getName()).isEqualTo(customer.getName()),
//                () -> assertThat(actualCustomer.getNickname()).isEqualTo(customer.getNickname()),
//                () -> assertThat(actualCustomer.getEmail()).isEqualTo(customer.getEmail()),
//                () -> assertThat(actualCustomer.getPhone()).isEqualTo(customer.getPhone()),
//                () -> assertThat(actualCustomer.getFileNm()).isEqualTo(customer.getFileNm()),
//                () -> assertThat(actualCustomer.getGradeSn()).isEqualTo(customer.getGradeSn()),
//                () -> assertThat(actualCustomer.getUserStatus()).isEqualTo(customer.getUserStatus()),
//                () -> assertThat(actualCustomer.getDate()).isEqualTo(customer.getDate())
//        );
//    }

}