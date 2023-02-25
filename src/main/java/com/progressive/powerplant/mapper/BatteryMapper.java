package com.progressive.powerplant.mapper;

import com.progressive.powerplant.dto.BatteryRequest;
import com.progressive.powerplant.model.Battery;
import com.progressive.powerplant.repository.BatteryDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BatteryMapper {
    Battery battery(BatteryDocument entity);

    BatteryDocument entity(Battery battery);

    @Mapping(source = "postCode", target = "postCode.code")
    Battery battery(BatteryRequest request);

    @Mapping(source = "postCode.code", target = "postCode")
    BatteryRequest request(Battery request);
}
