package com.example.diplom;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class FiltrKlientController implements Initializable {

    @FXML
    private Button clearFiltr;

    @FXML
    private DatePicker dataDoField;

    @FXML
    private DatePicker dataOdField;

    @FXML
    private Button saveButton;

    @FXML
    private Text titleFiltr;

    @FXML
    void clearBtn(MouseEvent event) {
        Stage stage = (Stage)clearFiltr.getScene().getWindow();
        stage.close();
        Controller.loadAllData();

    }

    @FXML
    void saveBtn(MouseEvent event) {
        Stage stage = (Stage)saveButton.getScene().getWindow();
        stage.close();
        Controller.loadAllData();
        Controller.filtrKLient(dataOdField.getValue(), dataDoField.getValue());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataDoField.setEditable(false);
        dataOdField.setEditable(false);
    }
}
