package com.example.diplom;

import com.example.diplom.Models.Sklad;
import com.example.diplom.Models.Zamowienie;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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

import static com.example.diplom.Controller.*;

public class AddZamowienieController implements Initializable {


    @FXML
    private Text cenaText;

    @FXML
    private Text dateText;

    @FXML
    private TextField iloscField;

    @FXML
    private ChoiceBox<String> klientBox;

    @FXML
    private ComboBox<String> panelBox;

    @FXML
    private ChoiceBox<String> pracownikBox;

    @FXML
    private Button saveButton;

    @FXML
    private VBox vBox;

    List<String> klientList = new ArrayList<>();
    List<String> pracownikiList = new ArrayList<>();
    List<String> panelList = new ArrayList<>();

    List<String> list = new ArrayList<>();


    int[] ilosc;
    private boolean update;
    private String query;
    private String query2;
    double suma;
    private int idZamowienie;
    private ResultSet resultSet;

    public void execureQuery(String query) {
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setCenaText() {


        suma = 0;
        try {
            Controller.loadDataSklad();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < ilosc.length; i++) {
            int finalI = i;
            if (ilosc[i] != 0) {
                suma += (Controller.listSklad.stream()
                        .filter(e -> e.getNazwa().equals(panelList.get(finalI).toString().split(" ")[0]))
                        .findFirst()
                        .get()
                        .getCena()) * ilosc[i];
            }
        }
        cenaText.setText(suma + " zl");
    }

    @FXML
    void saveBtn(MouseEvent event) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        if (pracownikBox.getValue() == null ||
                klientBox.getValue() == null ||
                cenaText.getText().split(" ")[0].startsWith("0")
        ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Prosze uzupelnic wszystkie pola");
            alert.showAndWait();
            return;
        } else {
            connection = DbConnect.getDbConnection();

            if (update == false) {
                query = "insert into Zamowienie(idKlient, idPracownik, data, numer, suma) " +
                        "values (" + klientBox.getValue().toString().split(" ")[0] + "" +
                        ", " + pracownikBox.getValue().toString().split(" ")[0] + "" +
                        ", '" + java.sql.Date.valueOf(LocalDate.now()) + "'" +
                        ", '" + "No" + Math.random() * 20000 + "'" +
                        ", " + cenaText.getText().split(" ")[0] +
                        ")";
                execureQuery(query);

            } else {
                query = "UPDATE Zamowienie SET "
                        + "idPracownik = " + pracownikBox.getValue().toString().split(" ")[0]
                        + ",idKlient= " + klientBox.getValue().toString().split(" ")[0]
                        + ", suma = " + cenaText.getText().split(" ")[0]
                        + "  WHERE idZamowienie = " + idZamowienie;
                execureQuery(query);

                query2 = "Delete from TowarZamowienie " +
                                " where idZamowienie = " + idZamowienie;
                execureQuery(query2);
            }
            String idZamowienie = update?this.idZamowienie+"":"(select idZamowienie from Zamowienie ORDER BY idZamowienie DESC LIMIT 1)";
            for (int i = 0; i < ilosc.length; i++) {
                if (ilosc[i] != 0) {
                    query = "select idTowar from Panel where nazwa like '" + panelList.get(i).toString().split(" ")[0] + "' LIMIT " + ilosc[i];
                    preparedStatement = connection.prepareStatement(query);
                    resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()){
                        int idTowar = resultSet.getInt("idTowar");
                        query2 = "insert into TowarZamowienie(idZamowienie, idTowar, cena) " +
                                "values (" +
                                " " + idZamowienie +
                                ", " + idTowar +
                                ", (select cena from Panel where idTowar = " + idTowar + ")" +
                                ")";
                        execureQuery(query2);
                    }
                }
            }
            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();
            Controller.loadAllData();
        }
    }


        @FXML
        void ilosc (KeyEvent event){
            if (iloscField.getText().isEmpty()) {
                ilosc[panelBox.getSelectionModel().getSelectedIndex()] = 0;
                return;
            }
            if (Controller.isNumeric(iloscField.getText(), "Ilosc")) {
                System.out.println("ss");
                ilosc[panelBox.getSelectionModel().getSelectedIndex()] = Integer.parseInt(iloscField.getText());
                setCenaText();
            }
        }

        @FXML
        void panelBox (MouseEvent event){
            panelBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                    iloscField.setText(String.valueOf(ilosc[(int) number2]));
                    setCenaText();
                }
            });
        }

        @Override
        public void initialize (URL url, ResourceBundle resourceBundle){
            dateText.setText(String.valueOf(LocalDate.now()));
            klientList = Controller.listKlient.stream().map(e -> e.getId() + " " + e.getImie() + " " + e.getNazwisko()).collect(Collectors.toList());
            pracownikiList = Controller.listWorkers.stream().map(e -> e.getId() + " " + e.getImie() + " " + e.getNazwisko()).collect(Collectors.toList());
            try {
                panelList = Controller.groupSklad().stream().map(e -> e.getNazwa() + " " + e.getTyp() + " " + e.getRozmiar() + " " + e.getCena()/*ilosc*/).collect(Collectors.toList());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            klientBox.getItems().addAll(klientList);
            panelBox.getItems().addAll(panelList);
            ilosc = new int[panelList.size()];
            pracownikBox.getItems().addAll(pracownikiList);
        }

    public void setTextField(Zamowienie zamowienie) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        pracownikBox.setValue(pracownikiList.stream().filter(e->e.split(" ")[0].equals(String.valueOf(zamowienie.getIdPracownik()))).findFirst().get());
        klientBox.setValue(klientList.stream().filter(e->e.split(" ")[0].equals(String.valueOf(zamowienie.getIdKlient()))).findFirst().get());
        this.idZamowienie = zamowienie.getIdZamowienie();
        cenaText.setText(zamowienie.getSuma()+"");
        dateText.setText(String.valueOf(zamowienie.getData()));
        String query3 = "select * from TowarZamowienie where idZamowienie = " + idZamowienie;
        connection = DbConnect.getDbConnection();

        preparedStatement = connection.prepareStatement(query3);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            list.add(resultSet.getString("idTowar") + " " + resultSet.getString("ilosc"));
        }

        List<String> nazwy = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < listSklad.size(); j++) {
                if (list.get(i).split(" ")[0].equals(listSklad.get(j).getIdPanel()+"")) {
                    nazwy.add(listSklad.get(j).getNazwa() + " " + list.get(i).split(" ")[1]);
                }
            }
        }
        for (int i = 0; i < panelList.size(); i++) {
            for (int j = 0; j < nazwy.size(); j++) {
                if (panelList.get(i).split(" ")[0].equals(nazwy.get(j).split(" ")[0])) {
                    ilosc[i] = Integer.parseInt(nazwy.get(j).split(" ")[1]);
                }

            }
        }

        update = true;
    }

}
