package com.feedo.application;

import com.feedo.domain.ports.RestaurantPersistence;
import com.feedo.domain.services.RestaurantService;
import com.feedo.domain.services.services_impl.RestaurantServiceImpl;
import com.feedo.persistence_mongodb.adapters.RestaurantPersistenceMongodb;
import com.feedo.persistence_mongodb.repositories.RestaurantRepository;
import com.feedo.rest_web.RestaurantResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {
        RestaurantResource.class
})
public class ApplicationConfiguration {

    @Bean
    public RestaurantService restaurantService(RestaurantPersistence restaurantPersistence) {
        return new RestaurantServiceImpl(restaurantPersistence);
    }

    @Bean
    public RestaurantPersistence restaurantPersistence(RestaurantRepository restaurantRepository) {
        return new RestaurantPersistenceMongodb(restaurantRepository);
    }

}
