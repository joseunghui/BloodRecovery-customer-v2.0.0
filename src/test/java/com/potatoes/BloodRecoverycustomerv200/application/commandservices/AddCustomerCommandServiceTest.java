package com.potatoes.BloodRecoverycustomerv200.application.commandservices;

import com.potatoes.BloodRecoverycustomerv200.domain.repository.CustomerRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
public class AddCustomerCommandServiceTest {
    @InjectMocks
    private AddCustomerCommandService addCustomerCommandService;

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