package com.potatoes.BloodRecoverycustomerv200.interfaces.rest.mapper;

import com.potatoes.BloodRecoverycustomerv200.config.MapstructConfig;
import com.potatoes.BloodRecoverycustomerv200.domain.model.commands.CustomerCommand;
import com.potatoes.BloodRecoverycustomerv200.interfaces.rest.dto.CustomerFormDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapstructConfig.class)
public abstract class CustomerMapper {

    public abstract CustomerCommand dtoToCommand(CustomerFormDto form);

    @AfterMapping
    protected void afterMappingToCommand(
            @MappingTarget final CustomerCommand.CustomerCommandBuilder targetBuilder,
            CustomerFormDto dto) {
    }

}
