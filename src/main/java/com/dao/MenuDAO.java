package com.dao;

import com.entity.MenuItem;

import java.awt.*;
import java.util.List;

public interface MenuDAO {
    void addDish(MenuItem menu);
    void deleteDish(MenuItem menu);
    void updateDish(MenuItem menu);
    List<MenuItem>getAllDishes();

    List<MenuItem> getDishesByPriceRange(double minPrice, double maxPrice);
    List<MenuItem> getMenuItemsWithDiscount();

    void close();
    List<MenuItem> getMenuItemsWithinWeight(double maxWeight);
    MenuItem getApartmentById(int id);

}
