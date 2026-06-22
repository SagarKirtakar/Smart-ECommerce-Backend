package main;

import exception.ProductNotFoundException;
import menu.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ProductMenu productMenu = new ProductMenu();

        CustomerMenu customerMenu = new CustomerMenu();

        CartMenu cartMenu = new CartMenu();

        OrderMenu orderMenu = new OrderMenu();

        AnalyticsMenu analyticsMenu = new AnalyticsMenu();

        while (true) {

            System.out.println("\n====== Smart E-Commerce Backend ======");

            System.out.println("1. Product Module");
            System.out.println("2. Customer Module");
            System.out.println("3. Cart Module");
            System.out.println("4. Order Module");
            System.out.println("5. Analytics Module");
            System.out.println("6. Exit");

            System.out.print("Enter Choice : ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    productMenu.showMenu();
                    break;

                case 2: customerMenu.showMenu();
                    break;

                case 3: cartMenu.showMenu();
                    break;

                case 4: orderMenu.showMenu();
                    break;

                case 5: analyticsMenu.showMenu();
                    break;

                case 6:
                    System.out.println("Thank You!");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }


}