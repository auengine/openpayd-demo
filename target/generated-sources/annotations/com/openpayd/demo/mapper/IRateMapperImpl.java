package com.openpayd.demo.mapper;

import com.openpayd.demo.model.dto.external.RatePairDTO;
import com.openpayd.demo.model.dto.output.RateDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-01-30T15:29:56+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_20 (Oracle Corporation)"
)
@Component
public class IRateMapperImpl implements IRateMapper {

    @Override
    public RateDTO rateToRateDto(RatePairDTO ratePair) {
        if ( ratePair == null ) {
            return null;
        }

        RateDTO rateDTO = new RateDTO();

        rateDTO.setRate( ratePair.getRates().entrySet().stream().findFirst().get().getValue() );

        return rateDTO;
    }
}
