package com.example.diplom;

import com.example.diplom.Models.Worker;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterController {

    Worker user;

    @FXML
    private Button anulujBtn;

    @FXML
    private Text titleFiltr;

    @FXML
    private Button zmienBtn;

    @FXML
    private TextField newHaslo;
    @FXML
    private TextField stareHaslo;

    static String sqlQuery;


    static Connection connection = null;

    static PreparedStatement preparedStatement = null;
    static ResultSet resultSet = null;

    @FXML
    void clearBtn(MouseEvent event) throws SQLException {

    }

    @FXML
    void saveBtn(MouseEvent event) throws SQLException {
        sqlQuery = "UPDATE Uzytkownik set " +
                    "haslo = '" + newHaslo.getText() + "'" +
                    " where idPracownik = " + user.getId() + " AND " +
                    " haslo = '" + stareHaslo.getText() + "'";
        preparedStatement = connection.prepareStatement(sqlQuery);
        resultSet = preparedStatement.executeQuery();
        Stage stage1 = (Stage)newHaslo.getScene().getWindow();
        stage1.close();
    }

}
