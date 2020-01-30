package com.openpayd.demo.mapper;

import com.openpayd.demo.model.dto.external.RatePairDTO;
import com.openpayd.demo.model.dto.output.RateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface IRateMapper {

    IRateMapper INSTANCE = Mappers.getMapper(IRateMapper.class);

    @Mapping(expression = "java(ratePair.getRates().entrySet().stream().findFirst().get().getValue())", target = "rate")
    RateDTO rateToRateDto(RatePairDTO ratePair);
}
