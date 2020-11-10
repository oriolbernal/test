package com.feedo.persistence_postgres.entities;

import com.feedo.domain.models.Restaurant;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.BeanUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@ToString
@Entity
@Table(name = "RESTAURANTS")
public class RestaurantEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
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
