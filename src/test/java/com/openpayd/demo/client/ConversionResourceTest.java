package com.openpayd.demo.client;


import com.openpayd.demo.resource.RateResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest()
public class RateResourceTest {

    @Autowired
    private RateResource resource;


  @Test
  public void shouldGetPairForDate() throws Exception {
   Response response= resource.getCurrencyRate(
            "EUR","USD"
            );
      assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

  }

}
