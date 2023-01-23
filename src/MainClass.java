import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainClass {
    public static List<Manufacturer> manufacturers;

    public static List<Product> products;

    private static void message() {
        System.out.println("1. Add Product");
        System.out.println("2. Show All Products");
        System.out.println("3. Clear All Empty Stocked Products");
        System.out.println("4. Clear All Expired Stocks");
        System.out.println("5. Show All Stocks With Price Over Input's");
        System.out.println("6. Get Product Manufacturer");
        System.out.println("7. Get All Manufacturer Products With Price Less Than Input's");
        System.out.println("8. Change Manufacturer Phone Number");
        System.out.println("0. Exit");
    }

    public static void main(String[] args) {
        System.out.println("Welcome to CashMarket App.");
        Scanner sc = new Scanner(System.in);
        manufacturers = new ArrayList<>();
        products = new ArrayList<>();

        manufacturers.add(new Manufacturer("PepsiCo", "Washington Street John W. 23", "+1 484 345 234"));
        manufacturers.add(new Manufacturer("Devin", "Devin Street 21", "+359 885 457 758"));
        manufacturers.add(new Manufacturer("Chio", "Sofia Czar Boris III 45", "+359 345 351 345"));

        String input = null;

        while(true) {
            MainClass.message();
            input = sc.nextLine();
            switch(input.trim()) {
                case "1":
                    Product.addProduct();
                    break;
                case "2":
                    Product.printAllProducts();
                    break;
                case "3":
                    Product.removeProductsWithNoStock();
                    break;
                case "4":
                    Product.removeExpiredProducts();
                    break;
                case "5":
                    System.out.println("Enter Input for Price: ");
                    Product.printAllHigherThanPrice(Double.parseDouble(sc.nextLine()));
                    break;
                case "6":
                    System.out.println("Enter Product Name: ");
                    Product.printManufacturerOfProduct(sc.nextLine());
                    break;
                case "7":
                    System.out.println("Enter Manufacturer Name On First Line and Price Input On Second");
                    Manufacturer.showManufacturerProductsWithLessThanInput(sc.nextLine(), Double.parseDouble(sc.nextLine()));
                    break;
                case "8":
                    System.out.println("Enter Manufacturer Name On First Line and New Number On Second");
                    Manufacturer.changeManufacturerNumber(sc.nextLine(), sc.nextLine());
                    break;
            }
            if (input.equalsIgnoreCase("0")) {
                break;
            }
        }
        System.out.println("Thanks for using the Market app.");
    }
}