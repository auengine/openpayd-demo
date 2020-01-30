package com.openpayd.demo.clientproxy;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientProxyConfig {

    @Value("${ratesapi.url}")
    private String ratesApiUrl;
/*
    @Bean
    public IExternalRateResource getRatesClient() {
        ResteasyClient client = new ResteasyClientBuilder().
                defaultProxy("proxy.borsa.local", 8080, "http")
                .build();
        ResteasyWebTarget target = client.target(ratesApiUrl);
        IExternalRateResource proxy = target.proxy(IExternalRateResource.class);
        return proxy;
    }*/


  @Bean
  public IExternalRateResource getRatesClient() {
    ResteasyClient client = new ResteasyClientBuilder().build();
    ResteasyWebTarget target = client.target(ratesApiUrl);
      IExternalRateResource proxy = target.proxy(IExternalRateResource.class);
    return proxy;
  }

}
