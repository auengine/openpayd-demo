package com.openpayd.demo.model.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RatePairDTO {
  public   String base;
  public  Date date;
  public  Map<String,Double> rates;
}
