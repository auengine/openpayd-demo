package com.openpayd.demo.client;


import com.openpayd.demo.clientproxy.IExternalRateResource;
import com.openpayd.demo.model.dto.external.RatePairDTO;
import com.openpayd.demo.service.IRateService;
import com.openpayd.demo.util.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RateServiceTest {

    private final double rate = 0.9;
    private final String base = "USD";
    private final String symbol = "EUR";

    @MockBean
    private IExternalRateResource rateClient;

    @Autowired
    private IRateService rateService;

    @Before
    public void setup() {

        RatePairDTO ratePairDTO = new RatePairDTO();
        ratePairDTO.setBase(base);
        ratePairDTO.setDate(DateUtils.now());
        Map<String, Double> rates = new HashMap<String, Double>();
        rates.put(symbol, rate);
        ratePairDTO.setRates(rates);

        Mockito.when(rateClient.getLatestPair(anyString(),anyString())).thenReturn(ratePairDTO);
    }


    @Test
    public void testListConversions_thenConversionListWithTransactionIDShouldBeReturned() {
        RatePairDTO result = rateService.getLatestPair(Optional.of(base), Optional.ofNullable(symbol));
        assertNotNull(result);
        assertEquals(result.getBase(), base);
    }

}
