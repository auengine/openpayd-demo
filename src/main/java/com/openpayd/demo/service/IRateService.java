package com.openpayd.demo.service;

import com.openpayd.demo.exception.RatePairNotFound;
import com.openpayd.demo.model.dto.external.RatePairDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface IRateService {

    public RatePairDTO getLatestPair(Optional<String> base, Optional<String> symbols) throws RatePairNotFound;


}
