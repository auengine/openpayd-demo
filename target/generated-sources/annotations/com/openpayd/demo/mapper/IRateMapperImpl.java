package com.openpayd.demo.mapper;

import com.openpayd.demo.model.dto.output.RatePairDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-01-28T19:56:02+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_20 (Oracle Corporation)"
)
@Component
public class IRateMapperImpl implements IRateMapper {

    @Override
    public RatePairDTO rateToRateDto(com.openpayd.demo.model.dto.external.RatePairDTO ratePair) {
        if ( ratePair == null ) {
            return null;
        }

        RatePairDTO ratePairDTO = new RatePairDTO();

        ratePairDTO.setRate( ratePair.getRates().entrySet().stream().findFirst().get().getValue() );

        return ratePairDTO;
    }
}
