package com.potatoes.BloodRecoverycustomerv200.interfaces.rest.mapper;

import com.potatoes.BloodRecoverycustomerv200.config.MapstructConfig;
import com.potatoes.BloodRecoverycustomerv200.config.SHA256Config;
import com.potatoes.BloodRecoverycustomerv200.domain.model.commands.AddCustomerCommand;
import com.potatoes.BloodRecoverycustomerv200.interfaces.rest.dto.AddCustomerFormDto;
import org.mapstruct.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Mapper(config = MapstructConfig.class)
public abstract class AddCustomerMapper {

    @Mapping(target = "password", ignore = true)

    public abstract AddCustomerCommand addNewCustomerDtoToCommand(AddCustomerFormDto form) throws Exception;

    @AfterMapping
    protected void afterMappingToCommand(
            @MappingTarget final AddCustomerCommand.AddCustomerCommandBuilder targetBuilder,
            AddCustomerFormDto dto, BCryptPasswordEncoder encoder, SHA256Config sha256Config) throws Exception {
        targetBuilder.cid(sha256Config.encrypt(dto.getUserId()));
        targetBuilder.password(encoder.encode(dto.getPassword()));
    }

}
