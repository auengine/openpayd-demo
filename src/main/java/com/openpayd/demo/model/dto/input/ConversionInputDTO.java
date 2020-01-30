package com.openpayd.demo.model.dto.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ConversionInputDTO {
  @Size(min = 2 ,max = 3)
  public   String base;
  @Size(min = 2 ,max = 3)
  public   String symbol;
  @Min(value = 0)
  public  double amount;

}
