package com.openpayd.demo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Configuration
public class ApplicationConfig {

    @Value("${ratesapi.url}")
    private String ratesApiUrl;


}
