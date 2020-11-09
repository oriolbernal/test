package com.feedo.application;

import com.feedo.persistence_postgres.repositories.RestaurantRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
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
//@EnableMongoRepositories(basePackageClasses = RestaurantRepository.class)
//@EnableJpaRepositories("com.feedo.persistence_postgres.repositories")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args); // mvn clean install mvn spring-boot:run -pl application
    }

}
