package com.openpayd.demo.model.dto.output;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ConversionOutputDTO {
    public String transactionId;
    public double convertion;
}
