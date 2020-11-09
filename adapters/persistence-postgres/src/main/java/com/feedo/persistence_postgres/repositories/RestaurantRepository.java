package com.feedo.persistence_postgres.repositories;


import com.feedo.persistence_postgres.entities.RestaurantEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<RestaurantEntity, String> {
    Optional<RestaurantEntity> findByBarcode(Long barcode);
}
