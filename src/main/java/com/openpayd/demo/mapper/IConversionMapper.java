package com.openpayd.demo.mapper;

import com.openpayd.demo.model.Conversion;
import com.openpayd.demo.model.dto.input.ConversionInputDTO;
import com.openpayd.demo.model.dto.output.ConversionOutputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface IConversionMapper {

    IConversionMapper INSTANCE = Mappers.getMapper(IConversionMapper.class);

    Conversion convert(ConversionInputDTO conversionInputDTO);

    ConversionOutputDTO convert(Conversion conversion);

    List<ConversionOutputDTO> convert(List<Conversion> conversion);
}
