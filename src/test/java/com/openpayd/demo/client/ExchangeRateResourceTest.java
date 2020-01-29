package com.openpayd.demo.client;

import com.openpayd.demo.clientproxy.IExternalRateResource;
import com.openpayd.demo.model.dto.external.RatePairDTO;
import com.openpayd.demo.resource.ExchangeRateResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RateResourceTest {

    @Autowired
    private ExchangeRateResource resource;

  @Autowired
  private IExternalRateResource rateResource;
  @Test
  public void shouldGetPairForDate() throws Exception {
   RatePairDTO ratePair= rateResource.getLatestPair(
            "EUR","USD"
            );

  }

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, World")));
    }



}
