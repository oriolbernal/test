package com.feedo.rest_web;

import com.feedo.domain.models.Restaurant;
import com.feedo.domain.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping()
public class RestaurantResource {

    private final RestaurantService restaurantService;

    public RestaurantResource(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public List<Restaurant> readAll() {
        return this.restaurantService.readAll()
                .collect(Collectors.toList());
    }

    @GetMapping("/hi")
    public String hi() {
        return "Hi my friend!!!";
    }

    @GetMapping("/bye")
    public String bye() {
        return "Bye my friend!!!";
    }

    @GetMapping("/how")
    public String how() {
        return "how my friend!!!";
    }

}
