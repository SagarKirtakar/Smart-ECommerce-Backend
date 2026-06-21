package menu;

import dao.CartDAO;
import model.CartItem;
import model.ViewCartItem;

import java.util.List;
import java.util.Scanner;

public class CartMenu {

    private CartDAO cartDAO = new CartDAO();
    private Scanner sc = new Scanner(System.in);

    public void showMenu() {

        while (true) {

            System.out.println("\n===== Cart Module =====");

            System.out.println("1. Add Product To Cart");
            System.out.println("2. View Cart");
            System.out.println("3. Calculate Total");
            System.out.println("4. Remove Product From Cart");
            System.out.println("5. Back");

            System.out.print("Enter Choice : ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addToCart();
                    break;

                case 2:
                    viewCart();
                    break;

                case 3:
                    calculateTotal();
                    break;

                case 4:
                    removeFromCart();
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    // Add Product To Cart
    public void addToCart() {

        System.out.print("Enter Customer ID : ");
        int customerId = sc.nextInt();

        System.out.print("Enter Product ID : ");
        int productId = sc.nextInt();

        System.out.print("Enter Quantity : ");
        int quantity = sc.nextInt();

        CartItem cartItem =
                new CartItem(customerId, productId, quantity);

        cartDAO.addToCart(cartItem);
    }

    // View Cart
    public void viewCart() {

        System.out.print("Enter Customer ID : ");
        int customerId = sc.nextInt();

        List<ViewCartItem> items =
                cartDAO.viewCart(customerId);

        for (ViewCartItem item : items) {

            System.out.println(item);
        }
    }

    // Calculate Total
    public void calculateTotal() {

        System.out.print("Enter Customer ID : ");
        int customerId = sc.nextInt();

        double total =
                cartDAO.calculateTotal(customerId);

        System.out.println("Total Amount = ₹" + total);
    }

    // Remove Product From Cart
    public void removeFromCart() {

        System.out.print("Enter Cart ID : ");
        int cartId = sc.nextInt();

        cartDAO.removeFromCart(cartId);
    }
}