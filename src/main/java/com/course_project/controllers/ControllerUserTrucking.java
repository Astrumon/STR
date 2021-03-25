package com.course_project.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.course_project.data_access.model.Cargo;
import com.course_project.support.AlertGenerator;
import com.course_project.support.Checker;
import com.course_project.support.creator.CargoCreator;
import com.course_project.support.manager.CargoManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Данный класс реализует логику контроллера графического интерфейса экрана для создания записей грузоперевозок
 * Содержит обработку нажатия на кнопку: "Зберегти"
 * С помощью класса CargoCreator вызывается логика создания грузоперевозок
 */
public class ControllerUserTrucking {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField textFieldPointOfDeparture;

    @FXML
    private TextField textFieldPointOfArrival;

    @FXML
    private DatePicker datePickerDateOfDeparture;

    @FXML
    private DatePicker datePickerDateOfArrival;

    @FXML
    private TextField textFieldEmail;

    @FXML
    private TextArea textAreaCargoDescription;

    @FXML
    private Button buttonSaveTrucking;

    private CargoCreator cargoCreator;

    @FXML
    void buttonSaveTruckingAc(ActionEvent event) {
        Cargo cargo = getFilledCargo();
        if (cargo != null) {
            cargoCreator.createCargo(cargo);
        }
    }

    public Cargo getFilledCargo() {
        Cargo cargo = new Cargo();

        if (cargoCreator.isValidPoint(textFieldPointOfDeparture.getText())) {
            cargo.setFromTown(textFieldPointOfDeparture.getText());
        } else {
            return null;
        }

        if (cargoCreator.isValidPoint(textFieldPointOfArrival.getText())) {
            cargo.setToTown(textFieldPointOfArrival.getText());
        } else {
            return null;
        }

        if (cargoCreator.isValidDate(datePickerDateOfDeparture.getValue().toString())) {
            cargo.setDateSend(datePickerDateOfDeparture.getValue().toString());
        } else {
            return null;
        }

        if (cargoCreator.isValidDate(datePickerDateOfArrival.getValue().toString())) {
            cargo.setDateArrive(datePickerDateOfArrival.getValue().toString());
        } else {
            return null;
        }

        if (cargoCreator.isValidEmail(textFieldEmail.getText())) {
            cargo.setEmail(textFieldEmail.getText());
        } else {
            return null;
        }

        cargo.setText(textAreaCargoDescription.getText());

        return cargo;
    }

    @FXML
    void initialize() {
        assert textFieldPointOfDeparture != null : "fx:id=\"textFieldPointOfDeparture\" was not injected: check your FXML file 'userTrucking.fxml'.";
        assert textFieldPointOfArrival != null : "fx:id=\"textFieldPointOfArrival\" was not injected: check your FXML file 'userTrucking.fxml'.";
        assert datePickerDateOfDeparture != null : "fx:id=\"datePickerDateOfDeparture\" was not injected: check your FXML file 'userTrucking.fxml'.";
        assert datePickerDateOfArrival != null : "fx:id=\"datePickerDateOfArrival\" was not injected: check your FXML file 'userTrucking.fxml'.";
        assert textFieldEmail != null : "fx:id=\"textFieldEmail\" was not injected: check your FXML file 'userTrucking.fxml'.";
        assert textAreaCargoDescription != null : "fx:id=\"textAreaCargoDescription\" was not injected: check your FXML file 'userTrucking.fxml'.";
        assert buttonSaveTrucking != null : "fx:id=\"buttonSaveTrucking\" was not injected: check your FXML file 'userTrucking.fxml'.";
        cargoCreator = new CargoCreator();
    }
}
