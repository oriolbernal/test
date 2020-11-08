package com.feedo.application;

import com.feedo.persistence_mongodb.repositories.RestaurantRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(
/*        scanBasePackageClasses = {
                DatabaseSeeder.class,
                RestaurantResource.class,
                RestaurantService.class,
                RestaurantPersistenceImpl.class,
                RestaurantRepository.class
        },*/
        exclude = {ErrorMvcAutoConfiguration.class}
)
@EnableMongoRepositories(basePackageClasses = RestaurantRepository.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args); // mvn clean install mvn spring-boot:run -pl application
    }

}
