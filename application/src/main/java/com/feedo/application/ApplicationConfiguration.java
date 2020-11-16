package com.feedo.application;

import com.feedo.domain.ports.RestaurantPersistence;
import com.feedo.domain.services.RestaurantService;
import com.feedo.domain.services.services_impl.RestaurantServiceImpl;
import com.feedo.persistence_postgres.adapters.EbookPersistencePostgres;
import com.feedo.persistence_postgres.repositories.EbookRepository;
import com.feedo.persistence_postgres.repositories.FileDBRepository;
import com.feedo.persistence_postgres.repositories.RestaurantRepository;
import com.feedo.persistence_postgres.adapters.RestaurantPersistencePostgres;
import com.feedo.rest_web.RestaurantResource;
import com.obernalpo.ebook.ports.FilePersistence;
import com.obernalpo.ebook.services.FileStorageService;
import com.obernalpo.ebook.services.services_impl.FileStorageServiceImpl;
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
        return new RestaurantPersistencePostgres(restaurantRepository);
    }

    @Bean
    public FileStorageService fileStorageService(FilePersistence filePersistence) {
        return new FileStorageServiceImpl(filePersistence);
    }

    @Bean
    public FilePersistence filePersistence(EbookRepository ebookRepository, FileDBRepository fileDBRepository) {
        return new EbookPersistencePostgres(ebookRepository, fileDBRepository);
    }

}
