package com.feedo.application;

import com.feedo.domain.models.Restaurant;
import com.feedo.persistence_postgres.entities.RestaurantEntity;
import com.feedo.persistence_postgres.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Profile("dev")
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
                new RestaurantEntity(new Restaurant("wok2wok", 840004L, "welcome to Amsterdam", BigDecimal.ONE, "AMSTERDAM S.L.")),
                new RestaurantEntity(new Restaurant("bar test", 840007L, "test", BigDecimal.ONE, "test S.L."))
        );
        this.restaurantRepository.saveAll(articles);
    }
}
