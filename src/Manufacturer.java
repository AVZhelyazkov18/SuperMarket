import com.sun.tools.javac.Main;

import java.util.List;

public class Manufacturer {
    private int firmNumber;
    private String firmName;
    private String firmAddress;
    private String firmPhoneNumber;

    private int nextFirmNumber = 1;

    public Manufacturer(String firmName, String firmAddress, String firmPhoneNumber) {
        this.firmNumber = nextFirmNumber++;
        this.firmName = firmName;
        this.firmAddress = firmAddress;
        this.firmPhoneNumber = firmPhoneNumber;
    }

    public Manufacturer() {
        System.out.println("Manufacturer requires data.");
        System.out.println("No Data Has Been Added");
    }

    public static void printArray(List<Manufacturer> mans) {
        for (Manufacturer man : mans) {
            System.out.println(man.firmNumber + ": " + man.firmName + " " + man.firmPhoneNumber + " " + man.firmAddress);
        }
    }

    public static void print(Manufacturer man) {
        System.out.println(man.firmNumber + ": " + man.firmName + " " + man.firmPhoneNumber + " " + man.firmAddress);
    }

    @Override
    public String toString() {
        return this.firmName + " " + this.firmPhoneNumber + " " + this.firmAddress;
    }

    public static Manufacturer getExistingManufacturerByName(String searchingName) {
        for (Manufacturer man : MainClass.manufacturers) {
            if (man.firmName.equalsIgnoreCase(searchingName)) {
                return man;
            }
        }
        return null;
    }

    public static int getExistingManufacturerIndexByName(String searchingName) {
        for (int i = 0; i < MainClass.manufacturers.size(); i++) {
            if (MainClass.manufacturers.get(i).firmName.equalsIgnoreCase(searchingName)) {
                return i;
            }
        }
        return -1;
    }

    public static void showManufacturerProductsWithLessThanInput(String manufacturerName, double inputPrice) {
        Manufacturer manufacturer = Manufacturer.getExistingManufacturerByName(manufacturerName);
        if (manufacturer == null) {
            System.out.println("No manufacturer with name " + manufacturerName + " exists.");
            return;
        }

        List<Product> productList = Product.getProductsOfManufacturer(manufacturer);
        for (Product prod : productList) {
            if (prod.getProductPrice() < inputPrice) {
                prod.print();
            }
        }
    }

    public static void changeManufacturerNumber(String manufacturerName, String newNumber) {
        Manufacturer manufacturer = Manufacturer.getExistingManufacturerByName(manufacturerName);

        if (manufacturer == null) {
            System.out.println("Manufacturer with name " + manufacturerName + " doesn't exist.");
            return;
        }

        manufacturer.firmPhoneNumber = newNumber;

        Integer manufacturerIndex = Manufacturer.getExistingManufacturerIndexByName(manufacturerName);

        MainClass.manufacturers.set(manufacturerIndex, manufacturer);
        System.out.println("Successfully changed " + manufacturerName + "'s number.");
    }
}
