package com.feedo.persistence_mongodb.repositories;

import com.feedo.persistence_mongodb.entities.RestaurantEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RestaurantRepository extends MongoRepository<RestaurantEntity, String> {
    Optional<RestaurantEntity> findByBarcode(Long barcode);
}
