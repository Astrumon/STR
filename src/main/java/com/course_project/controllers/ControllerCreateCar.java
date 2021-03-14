package com.course_project.controllers;

import com.course_project.data_access.model.wagon.TypePlace;
import com.course_project.data_access.model.wagon.Wagon;
import com.course_project.database.DataSource;
import com.course_project.support.AlertGenerator;
import com.course_project.support.Checkable;
import com.course_project.support.Checker;
import com.course_project.support.WagonManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerCreateCar implements Checkable {

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

    private DataSource dataSource = new DataSource();

    private WagonManager wagonManager = new WagonManager();

    private Long idWagon;

    @FXML
    void buttonDeleteCarAc(ActionEvent event) {

        setIdWagon();

        if (wagonManager.deleteWagon(idWagon)) {
            AlertGenerator.info("Вагон успішно видалений");
        } else {
            AlertGenerator.error("Виникла помилка при видаленні вагону");
        }

    }

    private boolean isCorrectWagonNumber() {
        return !Checker.checkEmptyValue(textFieldNameCar.getText())
                && Checker.checkPositiveIntValue(textFieldNameCar.getText());
    }

    public boolean isEmptyFields() {
        return Checker.checkEmptyValue(textFieldNumberLowerSeats.getText())
                && Checker.checkEmptyValue(textFieldNumberSittingSeats.getText())
                && Checker.checkEmptyValue(textFieldNumberTopSeats.getText())
                && Checker.checkEmptyValue(textFieldNumberVipSeats.getText())
                && Checker.checkEmptyValue(textFieldNameCar.getText());
    }

    public boolean isIntegerPositiveNumber() {
        return Checker.checkPositiveIntValue(textFieldNumberLowerSeats.getText())
                && Checker.checkPositiveIntValue(textFieldNumberSittingSeats.getText())
                && Checker.checkPositiveIntValue(textFieldNumberTopSeats.getText())
                && Checker.checkPositiveIntValue(textFieldNumberVipSeats.getText());
    }


    @FXML
    void buttonSaveCarAc(ActionEvent event) {

        setIdWagon();

        int count = 0;

        if (!isEmptyFields() && isIntegerPositiveNumber()) {

            if (wagonManager.getWagons().size() == 0) {
                create(idWagon);
                return;
            } else {
                for (Wagon wagon : wagonManager.getWagons()) {
                    if (idWagon.equals(wagon.getIdWagon())) {
                        update(idWagon);
                        break;
                    }

                    if (!idWagon.equals(wagon.getIdWagon())) {
                        count++;
                        if (count == wagonManager.getWagons().size()) {
                            create(idWagon);
                        }
                    }
                }
            }
        } else {
            AlertGenerator.error("Коректно заповніть всі поля");
        }

    }

    private void setIdWagon() {
        if (isCorrectWagonNumber()) {
            idWagon = Long.parseLong(textFieldNameCar.getText());
        } else {
            AlertGenerator.error("Введіть коректний номер вагону");
        }
    }

    private void update(Long idWagon) {

        if (wagonManager.updateWagon(idWagon, setTypePlace())) {
            AlertGenerator.info("Інформація о вагоні оновлена");
        }

    }

    private void create(Long idWagon) {
        if (wagonManager.createWagon(idWagon, setTypePlace())) {
            AlertGenerator.info("Вагон успішно створений");
        }
    }

    private TypePlace setTypePlace() {

        TypePlace typePlace = new TypePlace();
        typePlace.setCountVip(Integer.parseInt(textFieldNumberVipSeats.getText()));
        typePlace.setCountLow(Integer.parseInt(textFieldNumberLowerSeats.getText()));
        typePlace.setCountMiddle(Integer.parseInt(textFieldNumberTopSeats.getText()));
        typePlace.setCountSeats(Integer.parseInt(textFieldNumberSittingSeats.getText()));

        return typePlace;
    }

    @FXML
    void initialize() {
        assert textFieldNameCar != null : "fx:id=\"textFieldNameCar\" was not injected: check your FXML file 'createCar.fxml'.";
        assert textFieldNumberVipSeats != null : "fx:id=\"textFieldNumberVipSeats\" was not injected: check your FXML file 'createCar.fxml'.";
        assert textFieldNumberTopSeats != null : "fx:id=\"textFieldNumberTopSeats\" was not injected: check your FXML file 'createCar.fxml'.";
        assert textFieldNumberLowerSeats != null : "fx:id=\"textFieldNumberLowerSeats\" was not injected: check your FXML file 'createCar.fxml'.";
        assert textFieldNumberSittingSeats != null : "fx:id=\"textFieldNumberSittingSeats\" was not injected: check your FXML file 'createCar.fxml'.";
        assert buttonSaveCar != null : "fx:id=\"buttonSaveCar\" was not injected: check your FXML file 'createCar.fxml'.";
        assert buttonDeleteCar != null : "fx:id=\"buttonDeleteCar\" was not injected: check your FXML file 'createCar.fxml'.";


    }
}
