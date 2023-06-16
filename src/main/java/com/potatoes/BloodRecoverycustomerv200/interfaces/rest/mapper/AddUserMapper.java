package com.potatoes.BloodRecoverycustomerv200.interfaces.rest.mapper;

import com.potatoes.BloodRecoverycustomerv200.domain.model.commands.AddUserCommand;
import com.potatoes.BloodRecoverycustomerv200.interfaces.rest.dto.AddUserFormDto;

public abstract class AddUserMapper {

    public abstract AddUserCommand dtoToCommand(AddUserFormDto form);
}
