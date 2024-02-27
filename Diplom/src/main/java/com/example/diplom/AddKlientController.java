package com.example.diplom;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static com.example.diplom.Controller.connection;
import static com.example.diplom.Controller.preparedStatement;

public class AddKlientController implements Initializable {
    @FXML
    private DatePicker dataField;

    @FXML
    private TextField imieField;

    @FXML
    private TextField nazwiskoField;

    @FXML
    private Button saveButton;

    @FXML
    private TextField telefonField;

    private boolean update = false;
    private String query;
    private int klientId;

    @FXML
    void saveBtn(MouseEvent event) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (imieField.getText().isEmpty() || nazwiskoField.getText().isEmpty() ||
                telefonField.getText().isEmpty() || dataField.getEditor().getText().isEmpty()
        ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Prosze uzupelnic wszystkie pola");
            alert.showAndWait();
            return;
        }

        if (update == false) {

            query = "insert into Klient(Imie, Nazwisko, Telefon, Data_Rejestracji) " +
                    "values ('" + imieField.getText() +
                    "', '" + nazwiskoField.getText() +
                    "', '" + telefonField.getText() +
                    "', '" + java.sql.Date.valueOf(dataField.getValue()) +
                    "')";

        }else{
            query = "UPDATE Klient SET "
                    + "Imie = '" + imieField.getText() + "'"
                    + ",Nazwisko='" + nazwiskoField.getText() + "'"
                    + ",Telefon = '" + telefonField.getText() + "'"
                    + ",Data_Rejestracji='" + java.sql.Date.valueOf(dataField.getValue()) + "'"
                    +"  WHERE idKlient = "+ klientId;
        }

        connection = DbConnect.getDbConnection();
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute();
        Stage stage = (Stage) saveButton.getScene().getWindow();

        stage.close();
        Controller.loadAllData();
    }

    void setTextField(int klientId, String imie, String nazwisko, String telefon, LocalDate dataRejestracji){
        update = true;
        this.klientId = klientId;
        imieField.setText(imie);
        nazwiskoField.setText(nazwisko);
        telefonField.setText(telefon);
        dataField.setValue(dataRejestracji);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataField.setEditable(false);
    }
}
