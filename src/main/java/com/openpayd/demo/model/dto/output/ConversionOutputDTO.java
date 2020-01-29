package com.openpayd.demo.model.dto.output;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ConversionOutputDTO {
  public   String transactionId;
  public  double convertion;
}
