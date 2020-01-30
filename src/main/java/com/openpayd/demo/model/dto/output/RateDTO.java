package com.openpayd.demo.model.dto.output;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RateDTO {
    public double rate;
}
