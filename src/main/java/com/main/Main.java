package com.main;

import com.service.MenuService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MenuService menuService = new MenuService();
        Scanner sc = new Scanner(System.in);
        try {
            while (true) {
                System.out.println("1: Add dish");
                System.out.println("2: Delete dish");
                System.out.println("3: Update dish");
                System.out.println("4: View all dishes");
                System.out.println("5: Filter dishes by price range");
                System.out.println("6: Filter dishes with discount");
                System.out.println("7: Filter dishes within weight");
                System.out.println("8: Auto-fill menu");
                System.out.println("9: Exit");
                System.out.print("-> ");

                String choice = sc.nextLine();
                try {
                    switch (choice) {
                        case "1":
                            menuService.addDish(sc);
                            break;
                        case "2":
                            menuService.deleteDish(sc);
                            break;
                        case "3":
                            menuService.updateDish(sc);
                            break;
                        case "4":
                            menuService.viewAllDishes();
                            break;
                        case "5":
                            menuService.getDishesByPriceRange(sc);
                            break;
                        case "6":
                            menuService.getMenuItemsWithDiscount();
                            break;
                        case "7":
                            menuService.getMenuItemsWithinWeight(sc);
                            break;
                        case "8":
                            System.out.print("Enter number of dishes to add: ");
                            int n = Integer.parseInt(sc.nextLine());
                            menuService.autoFillMenu(n);
                            break;
                        case "9":
                            return;
                        default:
                            System.out.println("Invalid option, please try again.");
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        } finally {
            sc.close();
            menuService.close();
        }
    }
}
