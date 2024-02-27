package com.example.diplom;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button clearFiltr;

    @FXML
    private Button saveButton;

    @FXML
    private Text titleFiltr;

    @FXML
    void clearBtn(MouseEvent event) {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("registerWindow.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void saveBtn(MouseEvent event) {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainWindow.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 800, 500);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setMinWidth(800);
        stage.setMinHeight(600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        Stage stage1 = (Stage)saveButton.getScene().getWindow();
        stage1.close();
    }

}
