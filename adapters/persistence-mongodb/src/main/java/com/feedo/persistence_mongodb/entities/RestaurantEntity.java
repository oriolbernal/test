package com.feedo.persistence_mongodb.entities;

import com.feedo.domain.models.Restaurant;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@ToString
@Document
public class RestaurantEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private Long barcode;
    private String name;
    private String description;
    private LocalDate registrationDate;
    private BigDecimal price;
    private String provider;

    public RestaurantEntity() {
    }

    public RestaurantEntity(Restaurant restaurant) {
        this.update(restaurant);
    }

    public void update(Restaurant restaurant) {
        BeanUtils.copyProperties(restaurant, this);
    }

    public Restaurant toRestaurant() {
        Restaurant restaurant = new Restaurant();
        BeanUtils.copyProperties(this, restaurant);
        return restaurant;
    }
}
