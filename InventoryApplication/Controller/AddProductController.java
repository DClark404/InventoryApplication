package InventoryApplication.Controller;

import InventoryApplication.Model.Inventory;
import InventoryApplication.Model.Part;
import InventoryApplication.Model.Product;
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

public class AddProductController implements Initializable {

    @FXML
    private TableView<Part> SearchPartsTableView;

    @FXML
    private TableColumn<Part, Integer> SearchPartsIDColumn;

    @FXML
    private TableColumn<Part, String> SearchPartsNameColumn;

    @FXML
    private TableColumn<Part, Integer> SearchPartsInvColumn;

    @FXML
    private TableColumn<Part, Double> SearchPartsPriceColumn;

    @FXML
    private TextField PartsSearchBar;

    @FXML
    private TableView<Part> AssocPartsTableView;

    @FXML
    private TableColumn<Part, Integer> AssocPartsIDColumn;

    @FXML
    private TableColumn<Part, String> AssocPartsNameColumn;

    @FXML
    private TableColumn<Part, Integer> AssocPartsInvColumn;

    @FXML
    private TableColumn<Part, Double> AssocPartsPriceColumn;

    @FXML
    private TextField txtAddProductName;

    @FXML
    private TextField txtAddProductInv;

    @FXML
    private TextField txtAddProductPrice;

    @FXML
    private TextField txtAddProductMax;

    @FXML
    private TextField txtAddProductMin;

    Product newProduct = new Product(1,"1",1,1,1,1);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Parts Search Table Initialization
        SearchPartsTableView.setItems(Inventory.getAllParts());
        SearchPartsIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        SearchPartsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        SearchPartsInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        SearchPartsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Associated Parts Table Initialization
        AssocPartsTableView.setItems(newProduct.getAllAssociatedParts());
        AssocPartsIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        AssocPartsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        AssocPartsInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        AssocPartsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    public void closeAddProducts(MouseEvent mouseEvent) throws IOException {
        Parent mainScreenParent = FXMLLoader.load(getClass().getResource("/InventoryApplication/View/MainScreen.fxml"));
        Scene mainScreenScene = new Scene(mainScreenParent);
        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(mainScreenScene);
        window.show();
    }

    public void addAssocPart(MouseEvent mouseEvent) {
        if(SearchPartsTableView.getSelectionModel().getSelectedItem() != null) {
            newProduct.addAssociatedPart(SearchPartsTableView.getSelectionModel().getSelectedItem());
        }
        AssocPartsTableView.setItems(newProduct.getAllAssociatedParts());
    }

    public void partsSearchClicked(MouseEvent mouseEvent) {
        String SearchText = PartsSearchBar.getText();
        if (SearchText == null) {
            SearchPartsTableView.setItems(Inventory.getAllParts());
        }
        else {
            SearchPartsTableView.setItems(Inventory.lookupPart(SearchText));
        }
    }

    public void saveAddProducts(MouseEvent mouseEvent) {
        if (txtAddProductName.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Invalid Product!");
            alert.setContentText("The name field cannot be blank.");
            alert.showAndWait();
        }
        else {
            try {
                newProduct.setId(Inventory.getAllProducts().size()+1);
                newProduct.setName(txtAddProductName.getText().trim());
                newProduct.setPrice(Double.parseDouble(txtAddProductPrice.getText().trim()));
                newProduct.setStock(Integer.parseInt(txtAddProductInv.getText().trim()));
                newProduct.setMin(Integer.parseInt(txtAddProductMin.getText().trim()));
                newProduct.setMax(Integer.parseInt(txtAddProductMax.getText().trim()));

                if (newProduct.getAllAssociatedParts().size() == 0) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Invalid Product!");
                    alert.setContentText("A product must be associated with at least one part.");
                    alert.showAndWait();
                }
                else {
                    Inventory.addProduct(newProduct);

                    Parent mainScreenParent = FXMLLoader.load(getClass().getResource("/InventoryApplication/View/MainScreen.fxml"));
                    Scene mainScreenScene = new Scene(mainScreenParent);
                    Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                    window.setScene(mainScreenScene);
                    window.show();
                }

            } catch (NumberFormatException | IOException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Form Data!");
                alert.setContentText("Fields cannot be blank, and number fields cannot contain characters.");
                alert.showAndWait();
            }
        }
    }



    public void deleteAssocPart(MouseEvent mouseEvent) {
        if(AssocPartsTableView.getSelectionModel().getSelectedItem() != null) {
            newProduct.deleteAssociatedPart(AssocPartsTableView.getSelectionModel().getSelectedItem());
        }
        AssocPartsTableView.setItems(newProduct.getAllAssociatedParts());
    }
}
