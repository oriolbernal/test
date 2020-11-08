package com.feedo.domain.services;

import com.feedo.domain.models.Restaurant;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public interface RestaurantService {
    Stream<Restaurant> readAll();

    Restaurant readById(final String id);
}