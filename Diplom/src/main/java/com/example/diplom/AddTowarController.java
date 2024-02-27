package com.example.diplom;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static com.example.diplom.Controller.connection;
import static com.example.diplom.Controller.preparedStatement;

public class AddTowarController implements Initializable {

    @FXML
    private TextField cenaField;

    @FXML
    private DatePicker dataField;

    @FXML
    private TextField nazwaField;

    @FXML
    private TextField rozmiarField;

    @FXML
    private Button saveButton;

    @FXML
    private TextField typField;


    @FXML
    private TextField iloscField;

    @FXML
    private Text iloscLabel;

    final String[] typ = {"monocrystalline", "polycrystalline"};
    final String[] rozmiar = {"small", "medium","large"};
    @FXML
    private ChoiceBox<String> rozmiarBox;

    @FXML
    private ChoiceBox<String> typBox;
    @FXML
    private HBox iloscBox;

    @FXML
    private VBox vBox;



    private boolean update = false;
    private String query;
    private int towarId;


    @FXML
    void saveBtn(MouseEvent event) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException  {
        if (nazwaField.getText().isEmpty() || typBox.getValue()==null ||
                cenaField.getText().isEmpty() || dataField.getEditor().getText().isEmpty() ||
                rozmiarBox.getValue() == null
        ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Prosze uzupelnic wszystkie pola");
            alert.showAndWait();
            return;
        }
        if (!Controller.isNumeric(cenaField.getText(), "Cena")){
            return;
        }
        if (!update && !Controller.isNumeric(iloscField.getText(), "Ilosc")){
            return;
        }

        for (int i = 1; i <= (!update?Integer.parseInt(iloscField.getText()):1); i++) {
            System.out.println(i);
            if (update == false) {


                query = "insert into Panel(nazwa, typ, cena, dataProdukcji, rozmiar) " +
                        "values ('" + nazwaField.getText() +
                        "', '" + typBox.getValue() +
                        "', '" + cenaField.getText() +
                        "', '" + java.sql.Date.valueOf(dataField.getValue()) +
                        "', '" + rozmiarBox.getValue() +
                        "')";

            } else {
                query = "UPDATE Panel SET "
                        + "nazwa = '" + nazwaField.getText() + "'"
                        + ",typ='" + typBox.getValue() + "'"
                        + ",cena = '" + cenaField.getText() + "'"
                        + ",dataProdukcji='" + java.sql.Date.valueOf(dataField.getValue()) + "'"
                        + ",rozmiar='" + rozmiarBox.getValue() + "'"
                        + "  WHERE idTowar = " + towarId;
            }

            connection = DbConnect.getDbConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            if (!update) {
                query = "insert into Sklad(idTowar) values ((select idTowar from Panel ORDER BY idTowar DESC LIMIT 1));";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.execute();
            }
        }
        Stage stage = (Stage) saveButton.getScene().getWindow();

        stage.close();
        Controller.loadDataSklad();


    }

    void setTextField(int towarId, String nazwa, String typ, Double cena, LocalDate dataProdukcji, String rozmiar){
        update = true;
        this.towarId = towarId;
        nazwaField.setText(nazwa);
        typBox.setValue(typ);
        cenaField.setText(String.valueOf(cena));
        dataField.setValue(dataProdukcji);
        rozmiarBox.setValue(rozmiar);

        vBox.getChildren().remove(iloscBox);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataField.setEditable(false);
        rozmiarBox.getItems().addAll(rozmiar);
        typBox.getItems().addAll(typ);
        if (!update) {
            dataField.setValue(LocalDate.now());
        }
    }
}
