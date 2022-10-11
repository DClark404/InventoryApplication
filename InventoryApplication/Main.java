package InventoryApplication;

import InventoryApplication.Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
/*
Written by David Clark.
Exception control implementation requirements selected:
-From Set 1, "ensuring that a product must always have at least one part."
-From Set 2, "ensuring that a product must have a name, price, and inventory level."
 */

    @Override
    public void start(Stage primaryStage) throws Exception{
        addTestData();
        Parent root = FXMLLoader.load(getClass().getResource("/InventoryApplication/View/MainScreen.fxml"));
        primaryStage.setTitle("Inventory Management Application");
        primaryStage.setScene(new Scene(root, 959, 354));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    //Passing in sample data to test functionality
    void addTestData() {
        //Test Data Parts List
        Part component1 = new InHouse((Inventory.getAllParts().size()+1), "Rotor", 2.99, 37, 10, 80, 19219);
        Inventory.addPart(component1);
        Part component2 = new InHouse((Inventory.getAllParts().size()+1), "Steel Frame", 3.99, 40, 10, 80, 77554);
        Inventory.addPart(component2);
        Part component3 = new InHouse((Inventory.getAllParts().size()+1), "Encased Beam", 176.75, 63, 10, 80, 19219);
        Inventory.addPart(component3);
        Inventory.addPart(new InHouse((Inventory.getAllParts().size()+1), "Modular Frame", 9.99, 15, 10, 50, 505));
        Inventory.addPart(new InHouse((Inventory.getAllParts().size()+1), "Steel Sheet", 450.00, 45, 10, 50, 1676));
        Inventory.addPart(new InHouse((Inventory.getAllParts().size()+1), "Conveyor Gear", 90.00, 37, 10, 50, 10154));
        Inventory.addPart(new InHouse((Inventory.getAllParts().size()+1), "Oil Canister", 99.99, 7, 5, 10, 77554));
        Inventory.addPart(new InHouse((Inventory.getAllParts().size()+1), "Organic Cotton", 8.99, 89, 30, 100, 77524));
        Part component4 = new Outsourced((Inventory.getAllParts().size()+1), "Copper Wire", 7.99, 43, 10, 50, "Tidal Corp");
        Inventory.addPart(component4);
        Part component5 = new Outsourced((Inventory.getAllParts().size()+1), "Solar Cell", 400.50, 30, 10, 50, "United Front");
        Inventory.addPart(component5);
        Part component6 = new Outsourced((Inventory.getAllParts().size()+1), "Alloy Plating", 30.49, 40, 30, 50, "Kindred");
        Inventory.addPart(component6);
        Inventory.addPart(new Outsourced((Inventory.getAllParts().size()+1), "Stitched Fuse", 500.52, 88, 30, 90, "Smithy Co."));
        Inventory.addPart(new Outsourced((Inventory.getAllParts().size()+1), "Plastic Sheet", 9.99, 67, 30, 100, "Hollow Industries"));
        Inventory.addPart(new Outsourced((Inventory.getAllParts().size()+1), "Steel Handle", 15.99, 49, 7, 100, "Coalition Drift"));
        Inventory.addPart(new Outsourced((Inventory.getAllParts().size()+1), "Blazed Copper", 8.20, 100, 30, 110, "Smithy Co."));
        Inventory.addPart(new Outsourced((Inventory.getAllParts().size()+1), "Iron Screw", 0.89, 32, 2, 100, "United Front"));

        //Test Data Products List
        Product product1 = new Product((Inventory.getAllProducts().size()+1), "Electric Motor", 99.50,45, 10, 80);
        product1.addAssociatedPart(component1);
        product1.addAssociatedPart(component3);
        product1.addAssociatedPart(component4);
        Inventory.addProduct(product1);
        Product product2 = new Product((Inventory.getAllProducts().size()+1), "Outlet Beam", 450.99,20, 5, 30);
        product2.addAssociatedPart(component3);
        product2.addAssociatedPart(component4);
        Inventory.addProduct(product2);
        Product product3 = new Product((Inventory.getAllProducts().size()+1), "Armored Frame", 207.33,15, 4, 25);
        product3.addAssociatedPart(component6);
        product3.addAssociatedPart(component2);
        Inventory.addProduct(product3);
        Product product4 = new Product((Inventory.getAllProducts().size()+1), "Reinforced Solar Panel", 699.99,24, 2, 30);
        product4.addAssociatedPart(component3);
        product4.addAssociatedPart(component2);
        product4.addAssociatedPart(component4);
        product4.addAssociatedPart(component5);
        product4.addAssociatedPart(component6);
        Inventory.addProduct(product4);
        Product product5 = new Product((Inventory.getAllProducts().size()+1), "Solar Generator", 45.60,54, 5, 55);
        product5.addAssociatedPart(component5);
        product5.addAssociatedPart(component1);
        Inventory.addProduct(product5);
        Product product6 = new Product((Inventory.getAllProducts().size()+1), "Converter Plinth", 74.99,32, 3, 74);
        product6.addAssociatedPart(component3);
        product6.addAssociatedPart(component4);
        Inventory.addProduct(product6);
    }
}