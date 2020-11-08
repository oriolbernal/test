package com.feedo.domain.ports;

import com.feedo.domain.models.Restaurant;

public interface RestaurantPersistence extends GenericPersistence<Restaurant, String> {
    Restaurant findByBarcode(Long barcode);
}
