package com.openpayd.demo.service.impl;

import com.openpayd.demo.clientproxy.IExternalRateResource;
import com.openpayd.demo.exception.RatePairNotFound;
import com.openpayd.demo.model.dto.external.RatePairDTO;
import com.openpayd.demo.service.IRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RateServiceImpl implements IRateService {

    @Autowired
    private IExternalRateResource rateResource;

    public RatePairDTO getLatestPair(Optional<String> base, Optional<String> symbols) throws RatePairNotFound {
        if (!base.isPresent()) {
            throw new IllegalArgumentException("Base currency is required!");
        }

        if (!symbols.isPresent()) {
            throw new IllegalArgumentException("Symbol currency is required!");
        }
        RatePairDTO ratePair = rateResource.getLatestPair(base.get().toUpperCase(), symbols.get().toUpperCase());
        Optional<RatePairDTO> result = Optional.ofNullable(ratePair);

        if (!result.isPresent()) {
            throw new RatePairNotFound(base.get(), symbols.get());
        }
        return result.get();
    }


}
