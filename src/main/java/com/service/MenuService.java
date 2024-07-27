package com.service;

import com.dao.MenuDAO;
import com.dao.MenuDAOImpl;
import com.entity.MenuItem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MenuService {
    private final MenuDAO menuDAO;

    public MenuService() {
        this.menuDAO = new MenuDAOImpl();
    }
    public void addDish(Scanner sc) {
        try {
            System.out.print("Enter dish name: ");
            String name = sc.nextLine();
            System.out.print("Enter price: ");
            double price = Double.parseDouble(sc.nextLine());
            System.out.print("Enter weight: ");
            double weight = Double.parseDouble(sc.nextLine());
            System.out.print("Is there a discount (true/false): ");
            boolean discount = Boolean.parseBoolean(sc.nextLine().toLowerCase());
            MenuItem menuItem = new MenuItem(name, price, weight, discount);
            menuDAO.addDish(menuItem);
        } catch (Exception e) {
            System.out.println("Error adding dish: " + e.getMessage());
        }
    }
    public void deleteDish(Scanner sc) {
        try {
            System.out.print("Enter dish ID to delete: ");
            int id = Integer.parseInt(sc.nextLine());
            MenuItem menuItem = menuDAO.getApartmentById(id);
            if (menuItem != null) {
                menuDAO.deleteDish(menuItem);
            } else {
                System.out.println("Dish not found!");
            }
        } catch (Exception e) {
            System.out.println("Error deleting dish: " + e.getMessage());
        }
    }
    public void updateDish(Scanner sc) {
        try {
            System.out.print("Enter dish ID to update: ");
            int id = Integer.parseInt(sc.nextLine());
            MenuItem menuItem = menuDAO.getApartmentById(id);
            if (menuItem != null) {
                System.out.print("Enter new dish name: ");
                menuItem.setName(sc.nextLine());
                System.out.print("Enter new price: ");
                menuItem.setPrice(Double.parseDouble(sc.nextLine()));
                System.out.print("Enter new weight: ");
                menuItem.setWeight(Double.parseDouble(sc.nextLine()));
                System.out.print("Is there a discount (true/false): ");
                menuItem.setDiscount(Boolean.parseBoolean(sc.nextLine().toLowerCase()));
                menuDAO.updateDish(menuItem);
            } else {
                System.out.println("Dish not found!");
            }
        } catch (Exception e) {
            System.out.println("Error updating dish: " + e.getMessage());
        }
    }
    public void viewAllDishes() {
        try {
            List<MenuItem> menuItems = menuDAO.getAllDishes();
            menuItems.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error viewing dishes: " + e.getMessage());
        }
    }
    public void getDishesByPriceRange(Scanner sc) {
        try {
            System.out.print("Enter minimum price: ");
            double minPrice = Double.parseDouble(sc.nextLine());
            System.out.print("Enter maximum price: ");
            double maxPrice = Double.parseDouble(sc.nextLine());
            List<MenuItem> menuItems = menuDAO.getDishesByPriceRange(minPrice, maxPrice);
            menuItems.forEach(menuItem -> System.out.println(menuItem.getId() + ": " + menuItem.getName() + " - " + menuItem.getPrice() + " - " + menuItem.getWeight() + "kg - " + (menuItem.getDiscount() ? "Discount available" : "No discount")));
        } catch (Exception e) {
            System.out.println("Error filtering dishes by price range: " + e.getMessage());
        }
    }
    public void getMenuItemsWithDiscount() {
        try {
            List<MenuItem> menuItems = menuDAO.getMenuItemsWithDiscount();
            menuItems.forEach(menuItem -> System.out.println(menuItem.getId() + ": " + menuItem.getName() + " - " + menuItem.getPrice() + " - " + menuItem.getWeight() + "kg - " + (menuItem.getDiscount() ? "Discount available" : "No discount")));
        } catch (Exception e) {
            System.out.println("Error filtering dishes with discount: " + e.getMessage());
        }
    }

    public void getMenuItemsWithinWeight(Scanner sc) {
        try {
            System.out.print("Enter maximum weight: ");
            double maxWeight = Double.parseDouble(sc.nextLine());
            List<MenuItem> menuItems = menuDAO.getMenuItemsWithinWeight(maxWeight);
            menuItems.forEach(menuItem -> System.out.println(menuItem.getId() + ": " + menuItem.getName() + " - " + menuItem.getPrice() + " - " + menuItem.getWeight() + "kg - " + (menuItem.getDiscount() ? "Discount available" : "No discount")));
        } catch (Exception e) {
            System.out.println("Error filtering dishes by weight: " + e.getMessage());
        }
    }

    public void close() {
        try {
            menuDAO.close();
        } catch (Exception e) {
            System.out.println("Error closing MenuDAO: " + e.getMessage());
        }
    }
    public void autoFillMenu(int n) {
        try {
            Random random = new Random();
            String[] dishNames = {"Pasta", "Burger", "Salad", "Soup", "Steak", "Pizza", "Sandwich", "Sushi", "Dessert", "Drink"};
            for (int i = 0; i < n; i++) {
                String name = dishNames[random.nextInt(dishNames.length)];
                double price = 5 + (100 - 5) * random.nextDouble();
                double weight = 0.1 + (2 - 0.1) * random.nextDouble();
                BigDecimal priceBD = new BigDecimal(price).setScale(2, RoundingMode.HALF_UP);
                BigDecimal weightBD = new BigDecimal(weight).setScale(2, RoundingMode.HALF_UP);

                boolean discount = random.nextBoolean();
                MenuItem menuItem = new MenuItem(name, priceBD.doubleValue(), weightBD.doubleValue(), discount);
                menuDAO.addDish(menuItem);
            }
            System.out.println(n + " dishes added to the menu.");
        } catch (Exception e) {
            System.out.println("Error auto-filling menu: " + e.getMessage());
        }
    }
}
