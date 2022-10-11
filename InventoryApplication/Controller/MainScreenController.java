package InventoryApplication.Controller;


import InventoryApplication.Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainScreenController implements Initializable{

    @FXML
    private TableView<Part> PartsTableView;

    @FXML
    private TableColumn<Part, Integer> PartsIDColumn;

    @FXML
    private TableColumn<Part, String> PartsNameColumn;

    @FXML
    private TableColumn<Part, Integer> PartsInventoryColumn;

    @FXML
    private TableColumn<Part, Double> PartsPricePerUnitColumn;

    @FXML
    private TextField PartsSearchBar;

    @FXML
    private TableView<Product> ProductsTableView;

    @FXML
    private TableColumn<Product, Integer> ProductIDColumn;

    @FXML
    private TableColumn<Product, String> ProductNameColumn;

    @FXML
    private TableColumn<Product, Integer> ProductInventoryColumn;

    @FXML
    private TableColumn<Product, Double> ProductPricePerUnitColumn;

    @FXML
    private TextField ProductsSearchBar;

    private static Part modifiedPart;
    private static Product modifiedProduct;

    public static Part getModifiedPart() {
        return modifiedPart;
    }

    public static void setModifiedPart(Part modifyPart) {
        modifiedPart = modifyPart;
    }

    public static Product getModifiedProduct() {
        return modifiedProduct;
    }

    public static void setModifiedProduct(Product modifyProduct) {
        modifiedProduct = modifyProduct;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        PartsTableView.setItems(Inventory.getAllParts());
        PartsIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartsInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartsPricePerUnitColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        ProductsTableView.setItems(Inventory.getAllProducts());
        ProductIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        ProductNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ProductInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        ProductPricePerUnitColumn.setCellValueFactory(new PropertyValueFactory<>("price"));


    }

    public void openAddParts(MouseEvent mouseEvent) throws IOException {
        Parent addPartsParent = FXMLLoader.load(getClass().getResource("/InventoryApplication/View/AddPartScreen.fxml"));
        Scene addPartsScene = new Scene(addPartsParent);
        Stage window = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(addPartsScene);
        window.show();
    }

    public void openAddProducts(MouseEvent mouseEvent) throws IOException {
        Parent addProductsParent = FXMLLoader.load(getClass().getResource("/InventoryApplication/View/AddProductScreen.fxml"));
        Scene addProductsScene = new Scene(addProductsParent);
        Stage window = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        window.setScene(addProductsScene);
        window.show();
    }


    public void partsSearchClicked(MouseEvent mouseEvent) {
        String SearchText = PartsSearchBar.getText();
                if (SearchText == null) {
                    PartsTableView.setItems(Inventory.getAllParts());
                }
                else {
                    PartsTableView.setItems(Inventory.lookupPart(SearchText));
                }
    }

    public void ProductsSearchClicked(MouseEvent mouseEvent) {
        String SearchText = ProductsSearchBar.getText();
        if (SearchText == null) {
            ProductsTableView.setItems(Inventory.getAllProducts());
        }
        else {
            ProductsTableView.setItems(Inventory.lookupProduct(SearchText));
        }
    }

    public void deletePart(MouseEvent mouseEvent) {
        if (PartsTableView.getSelectionModel().getSelectedItem() != null) {
            Inventory.deletePart(PartsTableView.getSelectionModel().getSelectedItem());
        }
        PartsTableView.setItems(Inventory.getAllParts());
    }

    public void openModifyParts(MouseEvent mouseEvent) throws IOException {
        Part modifyPart = PartsTableView.getSelectionModel().getSelectedItem();
        if (modifyPart == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("No Part Selected!");
            alert.setContentText("Please select a part from the list to open the modify screen.");
            alert.showAndWait();
        }
        else {
            setModifiedPart(modifyPart);
            Parent modifyPartsParent = FXMLLoader.load(getClass().getResource("/InventoryApplication/View/ModifyPartScreen.fxml"));
            Scene modifyPartsScene = new Scene(modifyPartsParent);
            Stage window = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
            window.setScene(modifyPartsScene);
            window.show();

        }

    }

    public void deleteProduct(MouseEvent mouseEvent) {
        if (ProductsTableView.getSelectionModel().getSelectedItem() != null) {
            Inventory.deleteProduct(ProductsTableView.getSelectionModel().getSelectedItem());
        }
        ProductsTableView.setItems(Inventory.getAllProducts());
    }

    public void openModifyProducts(MouseEvent mouseEvent) throws IOException {
        Product modifyProduct = ProductsTableView.getSelectionModel().getSelectedItem();
        if (modifyProduct == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("No Product Selected!");
            alert.setContentText("Please select a product from the list to open the modify screen.");
            alert.showAndWait();
        }
        else {
            setModifiedProduct(modifyProduct);
            Parent modifyProductsParent = FXMLLoader.load(getClass().getResource("/InventoryApplication/View/ModifyProductScreen.fxml"));
            Scene modifyProductsScene = new Scene(modifyProductsParent);
            Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            window.setScene(modifyProductsScene);
            window.show();
        }
    }

    public void exitProgramClose(MouseEvent mouseEvent) {
        System.exit(0);
    }
}
