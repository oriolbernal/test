package com.feedo.persistence_postgres.adapters;

import com.feedo.domain.exceptions.ConflictException;
import com.feedo.domain.exceptions.NotFoundException;
import com.feedo.domain.models.Restaurant;
import com.feedo.domain.ports.RestaurantPersistence;
import com.feedo.persistence_postgres.entities.RestaurantEntity;
import com.feedo.persistence_postgres.repositories.RestaurantRepository;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class RestaurantPersistencePostgres implements RestaurantPersistence {

    private final RestaurantRepository restaurantRepository;

    public RestaurantPersistencePostgres(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Restaurant findByBarcode(Long barcode) {
        return this.restaurantRepository.findByBarcode(barcode)
                .orElseThrow(() -> new NotFoundException("Article barcode: " + barcode))
                .toRestaurant();
    }

    @Override
    public Stream<Restaurant> readAll() {
        return StreamSupport.stream(this.restaurantRepository.findAll().spliterator(), false).map(RestaurantEntity::toRestaurant);
    }

    @Override
    public Restaurant readById(String id) {
        return this.restaurantRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Article id: " + id))
                .toRestaurant();
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        this.restaurantRepository.findByBarcode(restaurant.getBarcode())
                .ifPresent(articleEntity -> {
                    throw new ConflictException("Barcode exist: " + articleEntity.getBarcode());
                });
        return this.restaurantRepository.save(new RestaurantEntity(restaurant))
                .toRestaurant();
    }

    @Override
    public Restaurant update(Restaurant restaurant) {
        RestaurantEntity articleEntity = this.restaurantRepository.findById(restaurant.getId())
                .orElseThrow(() -> new NotFoundException("Article id: " + restaurant.getId()));
        articleEntity.update(restaurant);
        return this.restaurantRepository.save(articleEntity)
                .toRestaurant();
    }

    @Override
    public void deleteById(String id) {
        this.restaurantRepository.deleteById(id);
    }
}
