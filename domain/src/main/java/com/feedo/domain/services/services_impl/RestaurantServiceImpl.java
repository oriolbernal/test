package com.feedo.domain.services.services_impl;

import com.feedo.domain.models.Restaurant;
import com.feedo.domain.ports.RestaurantPersistence;
import com.feedo.domain.services.RestaurantService;

import java.util.stream.Stream;

public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantPersistence restaurantPersistence;

    public RestaurantServiceImpl(RestaurantPersistence restaurantPersistence) {
        this.restaurantPersistence = restaurantPersistence;
    }

    @Override
    public Stream<Restaurant> readAll() {
        return this.restaurantPersistence.readAll();
    }

    @Override
    public Restaurant readById(String id) {
        return this.restaurantPersistence.readById(id);
    }
}