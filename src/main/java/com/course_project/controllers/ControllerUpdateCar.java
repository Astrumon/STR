package com.course_project.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class ControllerUpdateCar {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField textFieldNameCar;

    @FXML
    private TextField textFieldNumberVipSeats;

    @FXML
    private TextField textFieldNumberTopSeats;

    @FXML
    private TextField textFieldNumberLowerSeats;

    @FXML
    private TextField textFieldNumberSittingSeats;

    @FXML
    private Button buttonSaveCar;

    @FXML
    private Button buttonDeleteCar;

    @FXML
    private CheckBox checkBoxFreightCar;

    @FXML
    void buttonDeleteCarAc(ActionEvent event) {

    }

    @FXML
    void buttonSaveCarAc(ActionEvent event) {

    }

    @FXML
    void checkBoxFreightCarAc(ActionEvent event) {
        if (checkBoxFreightCar.isSelected()){
            textFieldNumberVipSeats.setEditable(false);
            textFieldNumberTopSeats.setEditable(false);
            textFieldNumberLowerSeats.setEditable(false);
            textFieldNumberSittingSeats.setEditable(false);
        }
        else if (checkBoxFreightCar.isSelected() == false){
            textFieldNumberVipSeats.setEditable(true);
            textFieldNumberTopSeats.setEditable(true);
            textFieldNumberLowerSeats.setEditable(true);
            textFieldNumberSittingSeats.setEditable(true);
        }
    }

    @FXML
    void initialize() {
        assert textFieldNameCar != null : "fx:id=\"textFieldNameCar\" was not injected: check your FXML file 'updateCar.fxml'.";
        assert textFieldNumberVipSeats != null : "fx:id=\"textFieldNumberVipSeats\" was not injected: check your FXML file 'updateCar.fxml'.";
        assert textFieldNumberTopSeats != null : "fx:id=\"textFieldNumberTopSeats\" was not injected: check your FXML file 'updateCar.fxml'.";
        assert textFieldNumberLowerSeats != null : "fx:id=\"textFieldNumberLowerSeats\" was not injected: check your FXML file 'updateCar.fxml'.";
        assert textFieldNumberSittingSeats != null : "fx:id=\"textFieldNumberSittingSeats\" was not injected: check your FXML file 'updateCar.fxml'.";
        assert buttonSaveCar != null : "fx:id=\"buttonSaveCar\" was not injected: check your FXML file 'updateCar.fxml'.";
        assert buttonDeleteCar != null : "fx:id=\"buttonDeleteCar\" was not injected: check your FXML file 'updateCar.fxml'.";
        assert checkBoxFreightCar != null : "fx:id=\"checkBoxFreightCar\" was not injected: check your FXML file 'updateCar.fxml'.";
        inputRestriction(textFieldNameCar);
        inputRestriction(textFieldNumberVipSeats);
        inputRestriction(textFieldNumberTopSeats);
        inputRestriction(textFieldNumberLowerSeats);
        inputRestriction(textFieldNumberSittingSeats);
    }

    public void inputRestriction(TextField textField) {
        Pattern p = Pattern.compile("(\\d+\\.?\\d*)?");
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!p.matcher(newValue).matches()) textField.setText(oldValue);
        });
    }
}
