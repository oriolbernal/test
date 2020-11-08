package com.feedo.application;

import com.feedo.domain.models.Restaurant;
import com.feedo.persistence_mongodb.entities.RestaurantEntity;
import com.feedo.persistence_mongodb.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseSeeder {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public DatabaseSeeder(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
        this.seedDatabase();
    }

    private void seedDatabase() {
        List<RestaurantEntity> articles = Arrays.asList(
                new RestaurantEntity(new Restaurant("Bar Manolo", 840002L, "Manolo salutes you", BigDecimal.ONE, "manolo's provider")),
                new RestaurantEntity(new Restaurant("100 birritas", 840003L, "beer>water", BigDecimal.ONE, "lefe")),
                new RestaurantEntity(new Restaurant("wok2wok", 840004L, "welcome to Amsterdam", BigDecimal.ONE, "AMSTERDAM S.L."))
        );
        this.restaurantRepository.saveAll(articles);
    }
}
