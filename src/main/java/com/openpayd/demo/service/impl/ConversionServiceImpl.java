package com.openpayd.demo.service.impl;

import com.openpayd.demo.exception.ConversionNotFound;
import com.openpayd.demo.exception.IllegalInputParameter;
import com.openpayd.demo.exception.RatePairNotFound;
import com.openpayd.demo.mapper.IConversionMapper;
import com.openpayd.demo.model.Conversion;
import com.openpayd.demo.model.dto.external.RatePairDTO;
import com.openpayd.demo.model.dto.input.ConversionInputDTO;
import com.openpayd.demo.repository.IConversitonRepository;
import com.openpayd.demo.service.IConversionService;
import com.openpayd.demo.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ConversionServiceImpl implements IConversionService {

    @Autowired
    private RateServiceImpl rateService;

    @Autowired
    private IConversitonRepository repository;

    @Autowired
    IConversionMapper conversionMapper;


    public Conversion calculate(ConversionInputDTO input) throws RatePairNotFound {
        RatePairDTO ratePair = rateService.getLatestPair(Optional.ofNullable(input.getBase()), Optional.ofNullable(input.getSymbol()));
        double rate = ratePair.getRates().entrySet().stream().findFirst().get().getValue();

        Conversion modelObject = conversionMapper.convert(input);
        modelObject.setRate(rate);
        modelObject.setConvertion(rate * input.getAmount());
        modelObject.setTransactionId(UUID.randomUUID().toString());
        modelObject.setConversionTime(LocalDate.now());
        Conversion persistedModelObject = repository.save(modelObject);
        return persistedModelObject;
    }


    public List<Conversion> listConversions(Optional<String> transactionId,Optional<String> date,int pageNo,int pageSize) throws ConversionNotFound {

        if(transactionId.isPresent()){
           return   listConversions(transactionId.get());
        }
        else if(date.isPresent()){
            return   listConversions(pageNo,pageSize, DateUtils.YYYYDASHMMDASHDD(date.get()));
        }
        throw new IllegalInputParameter("Transaction id or date reqired!");

    }

    private List<Conversion> listConversions(int pageNo,int pageSize,LocalDate date) throws ConversionNotFound {
        PageRequest paging = new PageRequest(pageNo, pageSize, Sort.Direction.DESC, "conversionTime");
        Page<Conversion> pagedResult = repository.listByDate(date.minusDays(1),date.plusDays(1),paging);
        return   pagedResult.getContent();

    }

    private List<Conversion> listConversions(String transactionId) throws ConversionNotFound {
        Optional<Conversion> conversion = repository.findByTransactionId(transactionId);
        if(!conversion.isPresent())
        {
          throw  new ConversionNotFound("transaction id not found!");
        }
        return  Arrays.asList(conversion.get());

    }



}
