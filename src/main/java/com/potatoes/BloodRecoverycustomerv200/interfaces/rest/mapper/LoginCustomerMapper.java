package com.potatoes.BloodRecoverycustomerv200.interfaces.rest.mapper;

import com.potatoes.BloodRecoverycustomerv200.config.MapstructConfig;
import com.potatoes.BloodRecoverycustomerv200.config.SHA256Config;
import com.potatoes.BloodRecoverycustomerv200.domain.model.commands.LoginCustomerCommand;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Mapper(config = MapstructConfig.class)
public abstract class LoginCustomerMapper {

    @Mapping(target = "password", ignore = true)

    public abstract LoginCustomerCommand loginCustomerParameterToCommand(String userId, String password);

    @AfterMapping
    protected void afterMappingToCommand(
            @MappingTarget final LoginCustomerCommand.LoginCustomerCommandBuilder targetBuilder,
            String userId, String password, BCryptPasswordEncoder encoder) {
        targetBuilder.password(encoder.encode(password));
    }
}
