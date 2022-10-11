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



public class ModifyPartController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtModifyPartID.setText(Integer.toString(MainScreenController.getModifiedPart().getId()));
        txtModifyPartName.setText(MainScreenController.getModifiedPart().getName());
        txtModifyPartPriceCost.setText(Double.toString(MainScreenController.getModifiedPart().getPrice()));
        txtModifyPartInv.setText(Integer.toString(MainScreenController.getModifiedPart().getStock()));
        txtModifyPartMin.setText(Integer.toString(MainScreenController.getModifiedPart().getMin()));
        txtModifyPartMax.setText(Integer.toString(MainScreenController.getModifiedPart().getMax()));
        if (MainScreenController.getModifiedPart() instanceof InHouse) {
            outsourced = false;
            modifyInHouseRadio.setSelected(true);
            labelModifyPartDynamicLabel.setText("Machine ID:");
            txtModifyPartDynamicField.setText(Integer.toString(((InHouse) MainScreenController.getModifiedPart()).getMachineId()));


        } else {
            outsourced = true;
            modifyOutsourcedRadio.setSelected(true);
            labelModifyPartDynamicLabel.setText("Company Name:");
            txtModifyPartDynamicField.setText(((Outsourced) MainScreenController.getModifiedPart()).getCompanyName());

        }
    }

    @FXML
    private RadioButton modifyInHouseRadio;
    @FXML
    private RadioButton modifyOutsourcedRadio;
    @FXML
    private TextField txtModifyPartID;
    @FXML
    private TextField txtModifyPartName;
    @FXML
    private TextField txtModifyPartInv;
    @FXML
    private TextField txtModifyPartPriceCost;
    @FXML
    private TextField txtModifyPartMax;
    @FXML
    private TextField txtModifyPartMin;
    @FXML
    private TextField txtModifyPartDynamicField;
    @FXML
    private Label labelModifyPartDynamicLabel;

    private boolean outsourced;




    public void ModifyPartInHouseRB(MouseEvent mouseEvent) {
        outsourced = false;
        labelModifyPartDynamicLabel.setText("Machine ID:");
        txtModifyPartDynamicField.setPromptText("Machine ID");

    }

    public void ModifyPartOutsourcedRB(MouseEvent mouseEvent) {
        outsourced = true;
        labelModifyPartDynamicLabel.setText("Company Name:");
        txtModifyPartDynamicField.setPromptText("Company Name");

    }

    public void saveModifyParts(MouseEvent mouseEvent) {
        String ID = txtModifyPartID.getText();
        String Name = txtModifyPartName.getText().trim();
        String Price = txtModifyPartPriceCost.getText().trim();
        String Stock = txtModifyPartInv.getText().trim();
        String Min = txtModifyPartMin.getText().trim();
        String Max = txtModifyPartMax.getText().trim();
        String dynamic = txtModifyPartDynamicField.getText().trim();
        try {
            if (!outsourced) {
                InHouse newPart = new InHouse(1, "1", 1, 1, 1, 1, 1);

                newPart.setId(Integer.parseInt(ID));
                newPart.setName(Name);
                newPart.setPrice(Double.parseDouble(Price));
                newPart.setStock(Integer.parseInt(Stock));
                newPart.setMin(Integer.parseInt(Min));
                newPart.setMax(Integer.parseInt(Max));
                newPart.setMachineId(Integer.parseInt(dynamic));

                Inventory.updatePart(newPart.getId()-1, newPart);
            } else {
                Outsourced newPart = new Outsourced(1, "1", 1, 1, 1, 1, "1");

                newPart.setId(Integer.parseInt(ID));
                newPart.setName(Name);
                newPart.setPrice(Double.parseDouble(Price));
                newPart.setStock(Integer.parseInt(Stock));
                newPart.setMin(Integer.parseInt(Min));
                newPart.setMax(Integer.parseInt(Max));
                newPart.setCompanyName(dynamic);

                Inventory.updatePart(newPart.getId()-1, newPart);
            }
            Parent mainScreenParent = FXMLLoader.load(getClass().getResource("/InventoryApplication/View/MainScreen.fxml"));
            Scene mainScreenScene = new Scene(mainScreenParent);
            Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            window.setScene(mainScreenScene);
            window.show();
        } catch (NumberFormatException | IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Form Data!");
            alert.setContentText("Fields cannot be blank, and number fields cannot contain characters.");
            alert.showAndWait();
        }
    }

    public void closeModifyParts(MouseEvent mouseEvent) throws IOException {
        Parent mainScreenParent = FXMLLoader.load(getClass().getResource("/InventoryApplication/View/MainScreen.fxml"));
        Scene mainScreenScene = new Scene(mainScreenParent);
        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(mainScreenScene);
        window.show();
    }
}
