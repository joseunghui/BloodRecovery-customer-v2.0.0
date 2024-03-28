package com.potatoes.BloodRecoverycustomerv200.interfaces.rest.mapper;

import com.potatoes.BloodRecoverycustomerv200.config.MapstructConfig;
import com.potatoes.BloodRecoverycustomerv200.config.SHA256Config;
import com.potatoes.BloodRecoverycustomerv200.domain.model.commands.FindCustomerMyInfoCommand;
import com.potatoes.BloodRecoverycustomerv200.domain.model.commands.LoginCustomerCommand;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Mapper(config = MapstructConfig.class)
public abstract class LoginCustomerMapper {

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "name", ignore = true)

    /**
     * 일반 로그인
     */
    public abstract LoginCustomerCommand loginCustomerParameterToCommand(String userId, String password);

    @AfterMapping
    protected void afterMappingToCommand(
            @MappingTarget final LoginCustomerCommand.LoginCustomerCommandBuilder targetBuilder,
            String userId, String password, BCryptPasswordEncoder encoder) {
        targetBuilder.userId(userId);
        targetBuilder.password(encoder.encode(password));
    }

    /**
     * 유저 아이디 찾기
     * @param name
     * @param phone
     * @return
     */
    public abstract FindCustomerMyInfoCommand findCustomerMyInfoParameterToCommand(String name, String phone);

    @AfterMapping
    protected void afterMappingToCommand(
            @MappingTarget final FindCustomerMyInfoCommand.FindCustomerMyInfoCommandBuilder targetBuilder,
            String name, String phone) {
        targetBuilder.name(name);
        targetBuilder.phone(phone);
    }
}
