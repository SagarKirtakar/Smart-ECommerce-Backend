package menu;

import dao.AnalyticsDAO;

import java.util.Scanner;

public class AnalyticsMenu {

    private AnalyticsDAO analyticsDAO = new AnalyticsDAO();
    private Scanner sc = new Scanner(System.in);

    public void showMenu() {

        while (true) {

            System.out.println("\n===== Analytics Module =====");

            System.out.println("1. Total Revenue");
            System.out.println("2. Total Orders");
            System.out.println("3. Top Selling Products");
            System.out.println("4. Back");

            System.out.print("Enter Choice : ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    analyticsDAO.getTotalRevenue();
                    break;

                case 2:
                    analyticsDAO.getTotalOrders();
                    break;

                case 3:
                    analyticsDAO.getTopSellingProducts();
                    break;

                case 4:
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}

