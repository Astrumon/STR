package com.course_project.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import com.course_project.data_access.model.wagon.TypePlace;
import com.course_project.data_access.model.wagon.Wagon;
import com.course_project.support.*;
import com.course_project.support.manager.WagonManager;
import com.course_project.support.updater.WagonUpdater;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import javafx.scene.control.*;


public class ControllerUpdateCar {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private GridPane gridPane;

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

    private WagonUpdater wagonUpdater = new WagonUpdater();

    private Long idWagon;

    @FXML
    void buttonDeleteCarAc(ActionEvent event) {

        setIdWagon();
        wagonUpdater.updateWagonsCountWarehouse(idWagon);
        wagonUpdater.updateWagonsCountTrain(idWagon);
        if (wagonUpdater.delete(idWagon)) {
            AlertGenerator.info("Вагон успішно видалений");
        } else {
            AlertGenerator.error("Виникла помилка при видаленні вагону");
        }
    }


    private void setIdWagon() {
        if (isCorrectWagonNumber()) {
            idWagon = Long.parseLong(textFieldNameCar.getText());
        } else {
            AlertGenerator.error("Введіть коректний номер вагону");
        }
    }

    private boolean isCorrectWagonNumber() {
        return !Checker.checkEmptyValue(textFieldNameCar.getText())
                && Checker.checkPositiveIntValue(textFieldNameCar.getText());
    }

    @FXML
    void buttonSaveCarAc(ActionEvent event) {
        setIdWagon();
        TypePlace typePlace = wagonUpdater.getTypePlace(idWagon);
        wagonUpdater.updateWagon(idWagon, getCountSeatsByTypePlace(typePlace));
    }


    private TypePlace getCountSeatsByTypePlace(TypePlace typePlace) {
        if (WagonManager.transfer.getType() == Wagon.SEATING_TYPE) {
            typePlace.setCountSeats(Integer.parseInt(textFieldNumberSittingSeats.getText()));
            return typePlace;
        }

        if (WagonManager.transfer.getType() == Wagon.LYING_TYPE) {
            typePlace.setCountLow(Integer.parseInt(textFieldNumberLowerSeats.getText()));
            typePlace.setCountMiddle(Integer.parseInt(textFieldNumberTopSeats.getText()));
            typePlace.setCountVip(Integer.parseInt(textFieldNumberVipSeats.getText()));
            return typePlace;
        }
        return typePlace;
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


        idWagon = WagonManager.transfer.getIdWagon();
        textFieldNameCar.setText(idWagon.toString());
        TypeCarAc();
    }

    public void TypeCarAc(){
        if (WagonManager.transfer.getType() == Wagon.LYING_TYPE){
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
        else if (WagonManager.transfer.getType() == Wagon.SEATING_TYPE){
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
        else if (WagonManager.transfer.getType() == Wagon.CARGO_TYPE){
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
    }

    public void inputRestriction(TextField textField) {
        Pattern p = Pattern.compile("(\\d+\\.?\\d*)?");
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!p.matcher(newValue).matches()) textField.setText(oldValue);
        });
    }
}
