package InventoryApplication.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();


    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /*Lookup function overloaded for integer ID search here, but course instructor said implementation was
    not necessary in the archived "how to implement ALL Features after introduction to Project Specs" webinar.*/
    public static Part lookupPart(int partId) {
        for (Part part : allParts) {
            if (part.getId() == partId) {
                return part;
            }
        }
        return null;
    }
    public static Product lookupProduct(int productId) {
        for (Product product : allProducts) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> foundParts = FXCollections.observableArrayList();
        boolean partsWereFound = false;
        for (Part part : allParts) {
            if (part.getName().toLowerCase().contains(partName.toLowerCase())) {
                foundParts.add(part);
                partsWereFound = true;
            }
        }
        if (partsWereFound) {
            return foundParts;
        }
        return null;
    }

    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> foundProducts = FXCollections.observableArrayList();
        boolean productsWereFound = false;
        for (Product product : allProducts) {
            if (product.getName().toLowerCase().contains(productName.toLowerCase())) {
                foundProducts.add(product);
                productsWereFound = true;
            }
        }
        if (productsWereFound) {
            return foundProducts;
        }
        return null;
    }

    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);

    }

    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);

    }

    public static boolean deletePart(Part selectedPart) {
        for (Part part : allParts) {
            if (part.getId() == selectedPart.getId()) {
                allParts.remove(part);
                return true;
            }
        }
        return false;
    }

    public static boolean deleteProduct(Product selectedProduct) {
        for (Product product : allProducts) {
            if (product.getId() == selectedProduct.getId()) {
                allProducts.remove(product);
                return true;
            }
        }
        return false;
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
