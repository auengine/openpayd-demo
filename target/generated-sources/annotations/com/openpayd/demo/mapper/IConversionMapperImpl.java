package com.openpayd.demo.mapper;

import com.openpayd.demo.model.Conversion;
import com.openpayd.demo.model.dto.input.ConversionInputDTO;
import com.openpayd.demo.model.dto.output.ConversionOutputDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-01-28T19:56:02+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_20 (Oracle Corporation)"
)
@Component
public class IConversionMapperImpl implements IConversionMapper {

    @Override
    public Conversion convert(ConversionInputDTO conversionInputDTO) {
        if ( conversionInputDTO == null ) {
            return null;
        }

        Conversion conversion = new Conversion();

        conversion.setBase( conversionInputDTO.getBase() );
        conversion.setSymbol( conversionInputDTO.getSymbol() );
        conversion.setAmount( conversionInputDTO.getAmount() );

        return conversion;
    }

    @Override
    public ConversionOutputDTO convert(Conversion conversion) {
        if ( conversion == null ) {
            return null;
        }

        ConversionOutputDTO conversionOutputDTO = new ConversionOutputDTO();

        conversionOutputDTO.setTransactionId( conversion.getTransactionId() );
        conversionOutputDTO.setConvertion( conversion.getConvertion() );

        return conversionOutputDTO;
    }

    @Override
    public List<ConversionOutputDTO> convert(List<Conversion> conversion) {
        if ( conversion == null ) {
            return null;
        }

        List<ConversionOutputDTO> list = new ArrayList<ConversionOutputDTO>( conversion.size() );
        for ( Conversion conversion1 : conversion ) {
            list.add( convert( conversion1 ) );
        }

        return list;
    }
}
