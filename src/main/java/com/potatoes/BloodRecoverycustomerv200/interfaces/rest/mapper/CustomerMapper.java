package com.potatoes.BloodRecoverycustomerv200.interfaces.rest.mapper;

import com.potatoes.BloodRecoverycustomerv200.config.MapstructConfig;
import com.potatoes.BloodRecoverycustomerv200.config.SHA256Config;
import com.potatoes.BloodRecoverycustomerv200.domain.model.commands.CustomerCommand;
import com.potatoes.BloodRecoverycustomerv200.interfaces.rest.dto.CustomerFormDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;

@Mapper(config = MapstructConfig.class)
public abstract class CustomerMapper {

    // 암호화 Security
    private BCryptPasswordEncoder encoder;
    private SHA256Config sha256Config;


    public abstract CustomerCommand addNewCustomerDtoToCommand(CustomerFormDto form) throws Exception;

    @AfterMapping
    protected void afterMappingToCommand(
            @MappingTarget final CustomerCommand.CustomerCommandBuilder targetBuilder,
            CustomerFormDto dto) throws Exception {

        targetBuilder.cid(getCustomerCIDEncoding(dto.getUserId()));
        targetBuilder.password(getPasswordEncoding(dto.getPassword()));
    }

    // 회원 가입 처리 전에 userId를 sha로 암호화해서 CID 발급
    @Transactional
    @BeforeMapping
    public String getCustomerCIDEncoding(@MappingTarget final String userId) throws Exception {
        return sha256Config.encrypt(userId);
    }

    // 회원 가입 처리 전에 비밀번호 암호화 상태로 DB 저장
    @Transactional
    @BeforeMapping
    public String getPasswordEncoding(@MappingTarget final String password) {
        return encoder.encode(password);
    }



}
