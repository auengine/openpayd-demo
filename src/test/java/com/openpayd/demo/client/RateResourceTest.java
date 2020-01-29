package com.openpayd.demo.client;

import com.openpayd.demo.clientproxy.IExternalRateResource;
import com.openpayd.demo.model.dto.external.RatePairDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest()
public class RateResourceTest {

  @Autowired
  private IExternalRateResource rateResource;
  @Test
  public void shouldGetPairForDate() throws Exception {
   RatePairDTO ratePair= rateResource.getLatestPair(
            "EUR","USD"
            );





  }
}
