package com.feedo.persistence_postgres.repositories;


import com.feedo.persistence_postgres.entities.RestaurantEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends CrudRepository<RestaurantEntity, String> {
    Optional<RestaurantEntity> findByBarcode(Long barcode);
}
