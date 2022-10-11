package InventoryApplication.Controller;

import InventoryApplication.Model.InHouse;
import InventoryApplication.Model.Inventory;
import InventoryApplication.Model.Outsourced;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AddPartController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private TextField txtAddPartName;
    @FXML
    private TextField txtAddPartInv;
    @FXML
    private TextField txtAddPartPriceCost;
    @FXML
    private TextField txtAddPartMax;
    @FXML
    private TextField txtAddPartMin;
    @FXML
    private TextField txtAddPartDynamicField;
    @FXML
    private Label labelAddPartDynamicLabel;

    private boolean outsourced = false;

    public void AddPartInHouseRB(MouseEvent mouseEvent) {
        outsourced = false;
        labelAddPartDynamicLabel.setText("Machine ID:");
        txtAddPartDynamicField.setPromptText("Machine ID");

    }

    public void AddPartOutsourcedRB(MouseEvent mouseEvent) {
        outsourced = true;
        labelAddPartDynamicLabel.setText("Company Name:");
        txtAddPartDynamicField.setPromptText("Company Name");


    }

    public void closeAddParts(MouseEvent mouseEvent) throws IOException {
        Parent mainScreenParent = FXMLLoader.load(getClass().getResource("/InventoryApplication/View/MainScreen.fxml"));
        Scene mainScreenScene = new Scene(mainScreenParent);
        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(mainScreenScene);
        window.show();
    }

    public void saveAddParts(MouseEvent mouseEvent) throws IOException {
        int ID = Inventory.getAllParts().size()+1;
        String Name = txtAddPartName.getText().trim();
        String Price = txtAddPartPriceCost.getText().trim();
        String Stock = txtAddPartInv.getText().trim();
        String Min = txtAddPartMin.getText().trim();
        String Max = txtAddPartMax.getText().trim();
        String dynamic = txtAddPartDynamicField.getText().trim();
        try {
            if (!outsourced) {
                InHouse newPart = new InHouse(1, "1", 1, 1, 1, 1, 1);

                newPart.setId(ID);
                newPart.setName(Name);
                newPart.setPrice(Double.parseDouble(Price));
                newPart.setStock(Integer.parseInt(Stock));
                newPart.setMin(Integer.parseInt(Min));
                newPart.setMax(Integer.parseInt(Max));
                newPart.setMachineId(Integer.parseInt(dynamic));

                Inventory.addPart(newPart);
            } else {
                Outsourced newPart = new Outsourced(1, "1", 1, 1, 1, 1, "1");

                newPart.setId(ID);
                newPart.setName(Name);
                newPart.setPrice(Double.parseDouble(Price));
                newPart.setStock(Integer.parseInt(Stock));
                newPart.setMin(Integer.parseInt(Min));
                newPart.setMax(Integer.parseInt(Max));
                newPart.setCompanyName(dynamic);

                Inventory.addPart(newPart);
            }
            Parent mainScreenParent = FXMLLoader.load(getClass().getResource("/InventoryApplication/View/MainScreen.fxml"));
            Scene mainScreenScene = new Scene(mainScreenParent);
            Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            window.setScene(mainScreenScene);
            window.show();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Form Data!");
            alert.setContentText("Fields cannot be blank, and number fields cannot contain characters.");
            alert.showAndWait();
        }
    }
}
