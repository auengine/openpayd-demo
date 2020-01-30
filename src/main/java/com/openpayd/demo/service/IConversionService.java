package com.openpayd.demo.service;

import com.openpayd.demo.exception.ConversionNotFound;
import com.openpayd.demo.exception.RatePairNotFound;
import com.openpayd.demo.model.Conversion;
import com.openpayd.demo.model.dto.input.ConversionInputDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IConversionService {

    public Conversion calculate(ConversionInputDTO input) throws RatePairNotFound;

    public List<Conversion> listConversions(Optional<String> transactionId, Optional<String> date, int pageNo, int pageSize) throws ConversionNotFound;

}
