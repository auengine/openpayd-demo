package com.openpayd.demo.client;


import com.openpayd.demo.model.dto.input.ConversionInputDTO;
import com.openpayd.demo.model.dto.output.ConversionOutputDTO;
import com.openpayd.demo.model.dto.output.ConversionOutputPageDTO;
import com.openpayd.demo.resource.ConversionResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConversionResourceTest {

    private static final String postUrl = "/api/conversion/convert";
    private static final String listUrl = "/api/conversion/list";
    private final double amount = 123.1;
    private final String base = "USD";
    private final String symbol = "EUR";
    @Autowired
    private ConversionResource resource;

    @Test
    public void shouldConvert() throws Exception {
        Response response = resource.convert(sampleInput());
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }


    @Test
    public void shouldListByTransactionıd() throws Exception {
        ConversionInputDTO input=sampleInput();
        Response response = resource.convert(input);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        Response result = resource.conversionList(0,1,((ConversionOutputDTO)response.getEntity()).getTransactionId(),null);
        assertEquals(Response.Status.OK.getStatusCode(), result.getStatus());
        assertEquals(((ConversionOutputDTO)response.getEntity()).getTransactionId(),
                ((ConversionOutputPageDTO)result.getEntity()).getItems().get(0).getTransactionId());
    }

    public ConversionInputDTO sampleInput() {
        ConversionInputDTO conversionInputDTO = new ConversionInputDTO();
        conversionInputDTO.setAmount(amount);
        conversionInputDTO.setBase(base);
        conversionInputDTO.setSymbol(symbol);
        return conversionInputDTO;
    }

}
