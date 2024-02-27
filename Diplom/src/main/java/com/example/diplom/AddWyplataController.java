package com.example.diplom;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static com.example.diplom.Controller.connection;
import static com.example.diplom.Controller.preparedStatement;

public class AddWyplataController implements Initializable {

    int idPracownik;
    int idWyplata;

    @FXML
    private DatePicker dataField;

    @FXML
    private TextField imieField;


    @FXML
    private TextField kwotaField;

    @FXML
    private TextField nazwiskoField;

    @FXML
    private ChoiceBox<String> pracownikBox;

    @FXML
    private Button saveButton;

    private boolean update = false;

    private String query;

    private List<String> pracowniki = new ArrayList<>();

    String[] pracownikAll = {"1212"};






    @FXML
    void saveBtn(MouseEvent event) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        if ( pracownikBox.getValue() == null || imieField.getText().isEmpty() || nazwiskoField.getText().isEmpty() ||
                kwotaField.getText().isEmpty() || dataField.getEditor().getText().isEmpty()
        ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Prosze uzupelnic wszystkie pola");
            alert.showAndWait();
            return;
        }
        if (!Controller.isNumeric(kwotaField.getText(),"Kwota"))
            return;

        if (update == false) {

            query = "insert into Wyplata(idPracownik, kwota, data) " +
                    "values ('" + pracownikBox.getValue().toString().split(" ")[0] +
                    "', '" + kwotaField.getText() +
                    "', '" + java.sql.Date.valueOf(dataField.getValue()) +
                    "')";

        }else{
            query = "UPDATE Wyplata SET "
                    + "idPracownik = '" + pracownikBox.getValue().toString().split(" ")[0] + "'"
                    + ",kwota='" + kwotaField.getText() + "'"
                    + ",data='" + java.sql.Date.valueOf(dataField.getValue()) + "'"
                    +"  WHERE idWyplata = "+ idWyplata;
        }

        connection = DbConnect.getDbConnection();
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute();
        Stage stage = (Stage) saveButton.getScene().getWindow();

        stage.close();
        Controller.loadAllData();
    }

    public String[] getPracowniki () throws SQLException {
        String sqlQuery = "Select * from Pracownik";


        preparedStatement = connection.prepareStatement(sqlQuery);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            pracowniki.add(
                    resultSet.getString("idPracownik") + " " +
                    resultSet.getString("Imie") + " " +
                    resultSet.getString("Nazwisko") + " " +
                    resultSet.getString("Wynagrodzenie")
            );
        }
        pracownikAll = new String[pracowniki.size()];

        return pracowniki.toArray(new String[pracowniki.size()]);
    }

    @FXML
    void pracownikBox(MouseEvent event) {
        pracownikBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                imieField.setText(pracownikBox.getItems().get((Integer) number2).toString().split(" ")[1]);
                nazwiskoField.setText(pracownikBox.getItems().get((Integer) number2).toString().split(" ")[2]);
                kwotaField.setText(pracownikBox.getItems().get((Integer) number2).toString().split(" ")[3]);
                System.out.println("Changed");
            }
        });

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        dataField.setEditable(false);

        pracowniki =  Controller.listWorkers.stream().map(e->e.getId() + " " + e.getImie() + " " + e.getNazwisko() + " " + e.getWynagrodzenie()).collect(Collectors.toList());

        pracownikBox.getItems().addAll(pracowniki);
        imieField.setEditable(false);
        nazwiskoField.setEditable(false);


        if (update == false) {
            dataField.setValue(LocalDate.now());
        }
    }

    public void setTextField(int idPracownik, int idWyplata, String imie, String nazwisko, double kwota, LocalDate data) {
        this.idPracownik = idPracownik;
        this.idWyplata = idWyplata;
        imieField.setText(imie);
        nazwiskoField.setText(nazwisko);
        kwotaField.setText(String.valueOf(kwota));
        dataField.setValue(data);
        System.out.println(pracowniki.size());
        pracownikBox.setValue(pracowniki.stream().filter(e->e.split(" ")[0].equals(String.valueOf(idPracownik))).findFirst().get());
        update = true;
    }
}
