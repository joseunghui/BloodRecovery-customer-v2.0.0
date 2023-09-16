package com.potatoes.BloodRecoverycustomerv200.interfaces.rest.mapper;

import com.potatoes.BloodRecoverycustomerv200.config.MapstructConfig;
import com.potatoes.BloodRecoverycustomerv200.domain.model.commands.CustomerCommand;
import com.potatoes.BloodRecoverycustomerv200.interfaces.rest.dto.CustomerFormDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.transaction.Transactional;

@Mapper(config = MapstructConfig.class)
public abstract class CustomerMapper {

    // 암호화 Security
    private BCryptPasswordEncoder encoder;

    public abstract CustomerCommand addNewCustomerDtoToCommand(CustomerFormDto form);

    @AfterMapping
    protected void afterMappingToCommand(
            @MappingTarget final CustomerCommand.CustomerCommandBuilder targetBuilder,
            CustomerFormDto dto) {
    }

    // 회원 가입 처리 전에 비밀번호 암호화 상태로 DB 저장
    @Transactional
    @BeforeMapping
    public String getPasswordEncoding(@MappingTarget final CustomerFormDto dto) {
        return encoder.encode(dto.getPassword());
    }



}
