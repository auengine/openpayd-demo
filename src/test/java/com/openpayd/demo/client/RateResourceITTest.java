package com.openpayd.demo.client;


import com.openpayd.demo.model.dto.output.RateDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RateResourceITTest {


    private static final String getUrl = "/api/rates";
    private final String base = "USD";
    private final String dash = "/";
    private final String symbol = "EUR";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldGetCurrencyRate() throws Exception {

        UriComponentsBuilder builder = UriComponentsBuilder.fromPath(getUrl);
        builder.path(dash + base).path(dash + symbol);

        ResponseEntity<RateDTO> result = restTemplate.
                exchange(builder.build().toUriString(), HttpMethod.GET, null, RateDTO.class);
        assertEquals(Response.Status.OK.getStatusCode(), result.getStatusCodeValue());
        assertNotNull(result.getBody());
    }


    @Test
    public void shouldFail_GetCurrencyRate() throws Exception {

        UriComponentsBuilder builder = UriComponentsBuilder.fromPath(getUrl);
        builder.path(dash + base).path(dash + symbol);

        ResponseEntity<RateDTO> result = restTemplate.
                exchange(builder.build().toUriString(), HttpMethod.GET, null, RateDTO.class);
        assertEquals(Response.Status.OK.getStatusCode(), result.getStatusCodeValue());
        assertNotNull(result.getBody());
    }

}
