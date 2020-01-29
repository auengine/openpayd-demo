package com.openpayd.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.*;

import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
  @Entity
  @JsonIgnoreProperties(ignoreUnknown = true)
  public class Conversion {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_CONVERSION")
    @SequenceGenerator(sequenceName = "S_CONVERSION", allocationSize = 1, name = "S_CONVERSION")
    private Long id;

    @NotBlank
    @Column(nullable = false,unique = true)
    private String transactionId;

    @NotBlank(message = "base.required")
    @Size(min = 2,max = 3)
    @Column(nullable = false)
    private String base;

    @NotBlank(message = "symbol.required")
    @Size(min = 2,max = 3)
    @Column(nullable = false)
    private String symbol;

    @Column(nullable = false)
    private double rate;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private double convertion;

    @Column(nullable = false)
    private LocalDate conversionTime;
}
