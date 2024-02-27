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

public class AddPracownikController implements Initializable {


    String query;

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @FXML
    private Button saveButton;

    @FXML
    private TextField imieField;

    @FXML
    private TextField nazwiskoField;

    @FXML
    private DatePicker pracownikData;

    @FXML
    private TextField pracownikPensja;

    @FXML
    private TextField pracownikTelefon;

    boolean edit = false;

    int workerId;

    public AddPracownikController() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    }

    @FXML
    void saveBtn(MouseEvent event) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException  {
        if (imieField.getText().isEmpty() || nazwiskoField.getText().isEmpty() ||
            pracownikPensja.getText().isEmpty() || pracownikTelefon.getText().isEmpty() ||
            pracownikData.getEditor().getText().isEmpty()
        ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Prosze uzupelnic wszystkie pola");
//            alert.setContentText("Dane zostały pomyślnie usunięte");

            alert.showAndWait();
            return;
        }

        if (!Controller.isNumeric(pracownikPensja.getText(),"Pensja"))
            return;
        if (!Controller.isPhoneNumber(pracownikTelefon.getText(),"Telefon"))
            return;

        if (edit == false) {

            query = "insert into Pracownik(Imie, Nazwisko,Wynagrodzenie, Data_Przyjecia, Telefon) values" +
                    " ('" + imieField.getText() + "', '"
                    + nazwiskoField.getText() + "',"
                    + pracownikPensja.getText()
                    + ",'" + java.sql.Date.valueOf(pracownikData.getValue()) + "'"
                    + ",'" + pracownikTelefon.getText() + "')";

        }else{
            query = "UPDATE Pracownik SET "
                    + "Imie = '" + imieField.getText() + "'"
                    + ",Nazwisko='" + nazwiskoField.getText() + "'"
                    + ",Wynagrodzenie = '" + pracownikPensja.getText() + "'"
                    + ",Data_Przyjecia='" + java.sql.Date.valueOf(pracownikData.getValue()) + "'"
                    + ",Telefon='" + pracownikTelefon.getText() + "'"
                    +"  WHERE idPracownik = "+ workerId;
        }

        connection = DbConnect.getDbConnection();
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute();
        Stage stage = (Stage) saveButton.getScene().getWindow();


        stage.close();
        Controller.loadDataPracownik();


    }

    void setTextField(int workerId, String imie, String nazwisko, double pensja, LocalDate date, String telefon){
        edit = true;
        this.workerId = workerId;
        imieField.setText(imie);
        nazwiskoField.setText(nazwisko);
        pracownikData.setValue(date);
        pracownikPensja.setText(String.valueOf(pensja));
        pracownikTelefon.setText(telefon);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pracownikData.setEditable(false);
        if (!edit) {
            pracownikData.setValue(LocalDate.now());
        }


    }
}
