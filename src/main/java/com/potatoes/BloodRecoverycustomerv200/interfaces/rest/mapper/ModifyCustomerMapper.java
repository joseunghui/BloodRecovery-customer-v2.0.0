package com.potatoes.BloodRecoverycustomerv200.interfaces.rest.mapper;

import com.potatoes.BloodRecoverycustomerv200.config.MapstructConfig;
import com.potatoes.BloodRecoverycustomerv200.domain.model.commands.AddCustomerCommand;
import com.potatoes.BloodRecoverycustomerv200.domain.model.commands.ModifyCustomerCommand;
import com.potatoes.BloodRecoverycustomerv200.interfaces.rest.dto.AddCustomerFormDto;
import com.potatoes.BloodRecoverycustomerv200.interfaces.rest.dto.ModifyCustomerFormDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapstructConfig.class)
public abstract class ModifyCustomerMapper {

    public abstract ModifyCustomerCommand modifyCustomerToCommand(String cid, ModifyCustomerFormDto form);

    @AfterMapping
    protected void afterMappingToCommand(
            @MappingTarget final AddCustomerCommand.AddCustomerCommandBuilder targetBuilder,
            String cid, AddCustomerFormDto dto) throws Exception {
        targetBuilder.cid(cid);
    }
}
