package com.feedo.domain.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@ToString
public class Restaurant {
    private String id;
    private Long barcode;
    private String name;
    private String description;
    private LocalDate registrationDate;
    private BigDecimal price;
    private String provider;

    public Restaurant() {
        this.id = UUID.randomUUID().toString();
        this.registrationDate = LocalDate.now();
    }

    public Restaurant(String name, Long barcode, String description, BigDecimal price, String provider) {
        super();
        this.barcode = barcode;
        this.name = name;
        this.description = description;
        this.price = price;
        this.provider = provider;
    }

}
