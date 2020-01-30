package com.openpayd.demo.model.dto.output;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ConversionOutputPageDTO {
    public List<ConversionOutputDTO> items;
    public int page;
    public int pageSize;

}
