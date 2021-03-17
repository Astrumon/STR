package com.course_project.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    private Label labelTypeCar;

    @FXML
    void buttonDeleteCarAc(ActionEvent event) {

    }

    @FXML
    void buttonSaveCarAc(ActionEvent event) {

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
        inputRestriction(textFieldNameCar);
        inputRestriction(textFieldNumberVipSeats);
        inputRestriction(textFieldNumberTopSeats);
        inputRestriction(textFieldNumberLowerSeats);
        inputRestriction(textFieldNumberSittingSeats);
        //TypeCarAc();
    }

    /*public void TypeCarAc(){
        if (){
            labelTypeCar.setText("Тип вагона: Лежачий");
            textFieldNumberVipSeats.setEditable(true);
            textFieldNumberTopSeats.setEditable(true);
            textFieldNumberLowerSeats.setEditable(true);
            textFieldNumberSittingSeats.setEditable(false);
            textFieldNumberVipSeats.setStyle("-fx-background-color: #C4C4C4; -fx-background-radius: 0");
            textFieldNumberTopSeats.setStyle("-fx-background-color: #C4C4C4; -fx-background-radius: 0");
            textFieldNumberLowerSeats.setStyle("-fx-background-color: #C4C4C4; -fx-background-radius: 0");
            textFieldNumberSittingSeats.setStyle("-fx-background-color: #DCDCDC; -fx-background-radius: 0");
        }
        else if (){
            labelTypeCar.setText("Тип вагона: Сидячий");
            textFieldNumberVipSeats.setEditable(false);
            textFieldNumberTopSeats.setEditable(false);
            textFieldNumberLowerSeats.setEditable(false);
            textFieldNumberSittingSeats.setEditable(true);
            textFieldNumberVipSeats.setStyle("-fx-background-color: #DCDCDC; -fx-background-radius: 0");
            textFieldNumberTopSeats.setStyle("-fx-background-color: #DCDCDC; -fx-background-radius: 0");
            textFieldNumberLowerSeats.setStyle("-fx-background-color: #DCDCDC; -fx-background-radius: 0");
            textFieldNumberSittingSeats.setStyle("-fx-background-color: #C4C4C4; -fx-background-radius: 0");
        }
        else if (){
            labelTypeCar.setText("Тип вагона: Вантажний");
            textFieldNumberVipSeats.setEditable(false);
            textFieldNumberTopSeats.setEditable(false);
            textFieldNumberLowerSeats.setEditable(false);
            textFieldNumberSittingSeats.setEditable(false);
            textFieldNumberVipSeats.setStyle("-fx-background-color: #DCDCDC; -fx-background-radius: 0");
            textFieldNumberTopSeats.setStyle("-fx-background-color: #DCDCDC; -fx-background-radius: 0");
            textFieldNumberLowerSeats.setStyle("-fx-background-color: #DCDCDC; -fx-background-radius: 0");
            textFieldNumberSittingSeats.setStyle("-fx-background-color: #DCDCDC; -fx-background-radius: 0");
        }
    }*/

    public void inputRestriction(TextField textField) {
        Pattern p = Pattern.compile("(\\d+\\.?\\d*)?");
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!p.matcher(newValue).matches()) textField.setText(oldValue);
        });
    }
}
