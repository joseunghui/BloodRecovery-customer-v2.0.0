package com.potatoes.BloodRecoverycustomerv200.interfaces.rest.mapper;

import com.potatoes.BloodRecoverycustomerv200.config.MapstructConfig;
import com.potatoes.BloodRecoverycustomerv200.domain.model.commands.AddUserCommand;
import com.potatoes.BloodRecoverycustomerv200.interfaces.rest.dto.AddUserFormDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapstructConfig.class)
public abstract class AddUserMapper{

    public abstract AddUserCommand dtoToCommand(AddUserFormDto form);

    @AfterMapping
    protected void afterMappingToCommand(
            @MappingTarget final AddUserCommand.AddUserCommandBuilder targetBuilder,
            AddUserFormDto dto) {
    }

}
