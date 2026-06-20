package menu;

import dao.ProductDAO;
import exception.ProductNotFoundException;
import model.Product;

import java.util.Scanner;

public class ProductMenu {

    private ProductDAO productDAO = new ProductDAO();
    private Scanner sc = new Scanner(System.in);

    public void showMenu() {

        while (true) {

            System.out.println("\n===== Product Module =====");

            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Search Product");
            System.out.println("4. Update Product");
            System.out.println("5. Delete Product");
            System.out.println("6. Back");

            System.out.print("Enter Choice : ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addProduct();
                    break;

                case 2:
                    viewProducts();
                    break;

                case 3:
                    searchProduct();
                    break;

                case 4:
                    updateProduct();
                    break;

                case 5:
                    deleteProduct();
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    public void addProduct() {

        System.out.print("Enter Name : ");
        String name = sc.next();

        System.out.print("Enter Category : ");
        String category = sc.next();

        System.out.print("Enter Price : ");
        double price = sc.nextDouble();

        System.out.print("Enter Stock : ");
        int stock = sc.nextInt();

        Product product =
                new Product(name, category, price, stock);

        productDAO.addProduct(product);
    }

    public void viewProducts() {

        for (Product product : productDAO.getAllProducts()) {

            System.out.println(product);
        }
    }

    public void searchProduct() {

        System.out.print("Enter Product ID : ");

        int productId = sc.nextInt();

        try {

            Product product =
                    productDAO.getProductById(productId);

            System.out.println(product);

        } catch (ProductNotFoundException e) {

            System.out.println(e.getMessage());
        }
    }

    public void updateProduct() {

        System.out.print("Enter Product ID : ");
        int productId = sc.nextInt();

        System.out.print("Enter New Name : ");
        String name = sc.next();

        System.out.print("Enter New Category : ");
        String category = sc.next();

        System.out.print("Enter New Price : ");
        double price = sc.nextDouble();

        System.out.print("Enter New Stock : ");
        int stock = sc.nextInt();

        Product product =
                new Product(name, productId, category, price, stock);

        productDAO.updateProduct(product);
    }

    public void deleteProduct() {

        System.out.print("Enter Product ID : ");

        int productId = sc.nextInt();

        productDAO.deleteProduct(productId);
    }
}