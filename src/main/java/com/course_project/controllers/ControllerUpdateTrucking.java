package com.course_project.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.course_project.data_access.model.Cargo;
import com.course_project.support.creator.CargoCreator;
import com.course_project.support.manager.CargoManager;
import com.course_project.support.updater.CargoUpdater;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ControllerUpdateTrucking {

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

    @FXML
    private Button buttonDeleteTrucking;

    private Cargo cargo;
    private CargoUpdater cargoUpdater;
    private CargoCreator cargoCreator;

    @FXML
    void buttonDeleteTruckingAc(ActionEvent event) {
        cargoUpdater.delete(cargo);
    }

    @FXML
    void buttonSaveTruckingAc(ActionEvent event) {
        Cargo cargo = getFilledCargo();
        if (cargo != null) {
            cargoUpdater.update(cargo);
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
        cargo.setId(this.cargo.getId());


        return cargo;
    }


    @FXML
    void initialize() {
        assert textFieldPointOfDeparture != null : "fx:id=\"textFieldPointOfDeparture\" was not injected: check your FXML file 'updateTrucking.fxml'.";
        assert textFieldPointOfArrival != null : "fx:id=\"textFieldPointOfArrival\" was not injected: check your FXML file 'updateTrucking.fxml'.";
        assert datePickerDateOfDeparture != null : "fx:id=\"datePickerDateOfDeparture\" was not injected: check your FXML file 'updateTrucking.fxml'.";
        assert datePickerDateOfArrival != null : "fx:id=\"datePickerDateOfArrival\" was not injected: check your FXML file 'updateTrucking.fxml'.";
        assert textFieldEmail != null : "fx:id=\"textFieldEmail\" was not injected: check your FXML file 'updateTrucking.fxml'.";
        assert textAreaCargoDescription != null : "fx:id=\"textAreaCargoDescription\" was not injected: check your FXML file 'updateTrucking.fxml'.";
        assert buttonSaveTrucking != null : "fx:id=\"buttonSaveTrucking\" was not injected: check your FXML file 'updateTrucking.fxml'.";
        assert buttonDeleteTrucking != null : "fx:id=\"buttonDeleteTrucking\" was not injected: check your FXML file 'updateTrucking.fxml'.";
        cargo = CargoManager.transfer;
        cargoUpdater = new CargoUpdater();
        cargoCreator = new CargoCreator();
        initData();
    }

    public void initData() {
        textFieldEmail.setText(cargo.getEmail());
        textAreaCargoDescription.setText(cargo.getText());
        textFieldPointOfDeparture.setText(cargo.getFromTown());
        textFieldPointOfArrival.setText(cargo.getToTown());
        datePickerDateOfDeparture.setValue(LocalDate.parse(cargo.getDateSend()));
        datePickerDateOfArrival.setValue(LocalDate.parse(cargo.getDateArrive()));
    }
}
