package com.openpayd.demo.client;


import com.openpayd.demo.resource.ExchangeRateResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ExchangeRateResourceTest {

  @Autowired
  private ExchangeRateResource rateResource;


    @Test
    public void contexLoads() throws Exception {
        assertThat(rateResource).isNotNull();
    }


    @Test
    public void shouldReturnDefaultMessage() throws Exception {

    }



}
