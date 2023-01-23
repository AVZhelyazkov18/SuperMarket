import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class Product {
    private int productNumber;
    private String productName;
    private double productPrice;
    private int productInStock;
    private LocalDate dateOfExpiration;

    private Manufacturer manufacturer;
    private static int nextProductNumber = 1;

    public Product(int productNumber) {
        this.productNumber = productNumber;
        this.productPrice = 0.d;
        this.productName = "Test";
        this.dateOfExpiration = LocalDate.of(1999, 12, 31);
        this.productInStock = 0;
        this.manufacturer = null;
    }

    public Product(int productNumber, String productName, double productPrice, LocalDate dateOfExpiration, int productInStock, Manufacturer manufacturer) {
        this.productNumber = productNumber;
        this.productName = productName;
        this.productPrice = productPrice;
        this.dateOfExpiration = dateOfExpiration;
        this.productInStock = productInStock;
        this.manufacturer = manufacturer;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public  int getProductInStock() {
        return productInStock;
    }

    public  void setProductInStock(int productInStock) {
        this.productInStock = productInStock;
    }

    public LocalDate getDateOfExpiration() {
        return dateOfExpiration;
    }

    public void setDateOfExpiration(LocalDate dateOfExpiration) {
        this.dateOfExpiration = dateOfExpiration;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public static void printArray(List<Product> prods) {
        for (Product prod: prods) {
            System.out.println(prod.productNumber + ": " + prod.productName + " " + prod.productPrice + " " + prod.dateOfExpiration);
        }
    }

    public static void addProduct() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Product Name: ");
        String name = sc.nextLine();
        System.out.println("Enter Product Price: ");
        double price = Double.parseDouble(sc.nextLine());
        System.out.println("Enter Date of expiration (Example: 1999/10/10): ");
        String[] dateString = sc.nextLine().split("/");
        System.out.println("Enter Products In Stock: ");
        Integer inStock = Integer.parseInt(sc.nextLine());
        LocalDate date = LocalDate.of(Integer.parseInt(dateString[0]), Integer.parseInt(dateString[1]), Integer.parseInt(dateString[2]));
        System.out.println("Enter Manufacturer Name (Needs to exist): ");
        Manufacturer manufacturer = Manufacturer.getExistingManufacturerByName(sc.nextLine().trim());
        if (manufacturer == null) {
            System.out.println("Invalid Manufacturer Name. Try Again.");
            return;
        }
        MainClass.products.add(new Product(nextProductNumber++, name, price, date, inStock, manufacturer));
        System.out.println("Product has been added successully.");
    }

    public static List<Product> removeProductsWithNoStock() {
        List<Product> products = MainClass.products;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).productInStock < 1)
                products.remove(i);
        }
        return products;
    }

    public static List<Product> removeExpiredProducts() {
        List<Product> products = MainClass.products;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).dateOfExpiration.isBefore(LocalDate.now()))
                products.remove(i);
        }

        return products;
    }

    public static void printAllHigherThanPrice(double price) {
        List<Product> sortedProducts = new ArrayList<>();
        for (Product prod: MainClass.products) {
            if (prod.productPrice > price) {
                sortedProducts.add(prod);
            }
        }
        Collections.sort(sortedProducts, Comparator.comparingDouble(o -> o.productPrice));

        Product.printArray(sortedProducts);
    }

    public static void printManufacturerOfProduct(String productName) {
        Product product = (Product)MainClass.products.stream().filter(n -> n.productName.equalsIgnoreCase(productName)).toArray()[0];
        if (product == null) {
            System.out.println("Couldn't find a product with the name: " + productName);
            return;
        }

        System.out.println(product.productName + ": " + product.getManufacturer().toString());
    }

    public static List<Product> getProductsOfManufacturer(Manufacturer manufacturer) {
        List<Product> productList = new ArrayList<>();
        for (Product product: MainClass.products) {
            if(product.manufacturer.equals(manufacturer)) {
                productList.add(product);
            }
        }

        return productList;
    }

    public void print() {
        System.out.println(this.productNumber + ": " + this.productName + " " + this.productPrice + " " + this.dateOfExpiration);
    }

    public static void printAllProducts() {
        printArray(MainClass.products);
    }
}
