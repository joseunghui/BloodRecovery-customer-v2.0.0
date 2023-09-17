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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class CustomerCommandServiceTest {
    @InjectMocks
    private CustomerCommandService customerCommandService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    @DisplayName("정상적으로 회원을 생성한다.")
    public void addCustomerCommandServiceTest() {

        //given
        CustomerCommand cmd = TestData.mockAddCustomerCommand;
        Customer customer = TestData.mockCustomer;

        given(customerRepository.findByUserId(anyString())).willReturn(customer);

        //when
        Customer actualCustomer = customerCommandService.addNewCustomer(cmd);

        //then
        assertAll(
                () -> assertThat(actualCustomer.getUserId()).isEqualTo(customer.getUserId()),
                () -> assertThat(actualCustomer.getName()).isEqualTo(customer.getName()),
                () -> assertThat(actualCustomer.getNickname()).isEqualTo(customer.getNickname()),
                () -> assertThat(actualCustomer.getBldTp()).isEqualTo(customer.getBldTp()),
                () -> assertThat(actualCustomer.getAddress()).isEqualTo(customer.getAddress()),
                () -> assertThat(actualCustomer.getEmail()).isEqualTo(customer.getEmail()),
                () -> assertThat(actualCustomer.getPhone()).isEqualTo(customer.getPhone()),
                () -> assertThat(actualCustomer.getFileNm()).isEqualTo(customer.getFileNm()),
                () -> assertThat(actualCustomer.getGradeSn()).isEqualTo(customer.getGradeSn()),
                () -> assertThat(actualCustomer.getUserStatus()).isEqualTo(customer.getUserStatus()),
                () -> assertThat(actualCustomer.getDate()).isEqualTo(customer.getDate())
        );
    }

    @Test
    @DisplayName("중복된 회원이 아님을 확인한다.")
    void isDuplicateIdCommandService() {
    }

    @Test
    @DisplayName("중복된 닉네임이 아님을 확인한다.")
    void isDuplicateNicknameCommandService() {
    }

    @Test
    @DisplayName("정상적으로 실명 인증을 완료한다.")
    void isValidPersonalNumberCommandService() {
    }

    @Test
    @DisplayName("정상적으로 로그인을 성공한다.")
    void loginUserCommandService() {
    }

    @Test
    @DisplayName("정상적으로 회원 정보를 가져온다.")
    void getCustomerInfoCommandService() {
    }
}