package com.openpayd.demo.client;



import com.openpayd.demo.model.Conversion;
import com.openpayd.demo.repository.IConversitonRepository;
import com.openpayd.demo.service.IConversionService;
import com.openpayd.demo.service.impl.ConversionServiceImpl;
import com.openpayd.demo.util.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;


import java.time.LocalDate;

import java.util.*;

import static junit.framework.TestCase.assertNotNull;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;


@RunWith(SpringRunner.class)
@SpringBootTest()
public class ConversionServiceTest {

    @MockBean
    private IConversitonRepository repository;

    @Autowired
    private IConversionService conversionService;

    private final double amount = 123.1;
    private final double rate = 0.9;
    final String transactionId = "TRTID";
    private final String base = "USD";
    private final String symbol = "EUR";
    private List<Conversion> conversionList;
    private final int size = 20;
    @Before
    public void setup() {
        conversionList= new ArrayList<Conversion>();
        for(int i=0; i<size; i++){

            Conversion conversion=new Conversion();
            conversion.setAmount(amount);
            conversion.setRate(rate);
            conversion.setConvertion(rate * amount);
            conversion.setTransactionId(transactionId+i);
            conversion.setBase(base);
            conversion.setSymbol(symbol);
            conversion.setConversionTime(LocalDate.now());
            conversionList.add(conversion);
        }
      Mockito.when(repository.findByTransactionId(anyString())).thenReturn(Optional.of(conversionList.get(0)));
      Mockito.when(repository.listByDate(any(LocalDate.class),any(LocalDate.class),any(PageRequest.class))).
              thenReturn(new PageImpl<Conversion>(conversionList));


    }

    @Test
    public void testListConversions_thenConversionListWithTransactionIDShouldBeReturned() {
        List<Conversion> result= conversionService.listConversions(Optional.of(transactionId),Optional.ofNullable(null),0,0);
        assertNotNull(result);
        assertTrue(result.size()==1);
        assertEquals(result.get(0).getTransactionId(),transactionId+0);
    }

    @Test
    public void testListConversions_thenConversionListWithDateShouldBeReturned() {
        List<Conversion> result= conversionService.listConversions(Optional.ofNullable(null),Optional.of(DateUtils.YYYYDASHMMDASHDD(LocalDate.now())),0,size);
        assertNotNull(result);
        assertTrue(result.size()==size);
        assertEquals(result.get(size-1).getTransactionId(),transactionId+(size-1));
    }





}
