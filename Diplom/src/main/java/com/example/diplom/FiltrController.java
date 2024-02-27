package com.example.diplom;

import javafx.fxml.FXML;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class FiltrController implements Initializable {
    @FXML
    private Slider sliderDo;

    @FXML
    private Text moneyField;

    @FXML
    private Slider sliderOd;

    @FXML
    private Text textDo;

    @FXML
    private Text textOd;

    @FXML
    private DatePicker dataDoField;

    @FXML
    private DatePicker dataOdField;

    @FXML
    private Button saveButton;

    @FXML
    private Button clearFiltr;

    @FXML
    private Text titleFiltr;

    String type = "";

     void setTitleFiltr(String text) {
        titleFiltr.setText(text);
    }

    void setType(String type) {
         this.type = type;
    }

    @FXML
    void saveBtn(MouseEvent event) {
        Stage stage = (Stage)saveButton.getScene().getWindow();
        stage.close();
        Controller.loadAllData();
        Controller.filtr(Double.parseDouble(textOd.getText().split(" ")[1]),Double.parseDouble(textDo.getText().split(" ")[1]),dataOdField.getValue(),dataDoField.getValue(),type);
    }

    @FXML
    void clearBtn(MouseEvent event) {
        Stage stage = (Stage)clearFiltr.getScene().getWindow();
        stage.close();
        Controller.loadAllData();
    }

    public void setParameters(String text, double max) {
        this.moneyField.setText(text);
        sliderOd.setMax(max);
        sliderDo.setMax(max);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataDoField.setEditable(false);
        dataOdField.setEditable(false);

        sliderOd.valueProperty().addListener((ObservableValue<? extends Number> num, Number oldVal, Number newVal)->{
            int value = newVal.intValue();
            textOd.setText("Od " + value);
            sliderDo.setMin(sliderOd.getValue());
            sliderDo.setValue(sliderOd.getMax());
        });
        sliderDo.valueProperty().addListener((ObservableValue<? extends Number> num, Number oldVal, Number newVal)->{
            int value = newVal.intValue();
            textDo.setText("Do " + value);
            if (sliderDo.getMin() == 10000){
                sliderDo.setValue(sliderDo.getMax());
            }
        });

        dataDoField.setValue(LocalDate.now());
    }

}
