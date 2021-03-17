package com.course_project.controllers;

import com.course_project.data_access.model.wagon.TypePlace;
import com.course_project.data_access.model.wagon.Wagon;
import com.course_project.database.DataSource;
import com.course_project.support.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

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
    private ChoiceBox<String> choiceBoxTypeCar;

    private DataSource dataSource = new DataSource();

    private WagonManager wagonManager = new WagonManager();

    private Long idWagon;

    private WagonCreator wagonCreator;



    @FXML
    void buttonSaveCarAc(ActionEvent event) {

//        setIdWagon();
//        wagonCreator.setIdWagon(idWagon);
//        wagonCreator.setStatus(checkBoxFreightCar.isSelected());
//
//        if (!checkBoxFreightCar.isSelected()) {
//            wagonCreator.setTypePlace(setTypePlace());
//        }
//
//        if (!isEmptyFields() && isIntegerPositiveNumber()) {
//            wagonCreator.createWagon();
//        } else if (checkBoxFreightCar.isSelected() && !Checker.checkEmptyValue(textFieldNameCar.getText())) {
//            wagonCreator.createWagon();
//        }  else {
//            AlertGenerator.error("Коректно заповніть всі поля");
//        }

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


    private TypePlace setTypePlace() {

        TypePlace typePlace = new TypePlace();
        typePlace.setCountVip(Integer.parseInt(textFieldNumberVipSeats.getText()));
        typePlace.setCountLow(Integer.parseInt(textFieldNumberLowerSeats.getText()));
        typePlace.setCountMiddle(Integer.parseInt(textFieldNumberTopSeats.getText()));
        typePlace.setCountSeats(Integer.parseInt(textFieldNumberSittingSeats.getText()));

        return typePlace;
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
    void initialize() {
        assert textFieldNameCar != null : "fx:id=\"textFieldNameCar\" was not injected: check your FXML file 'createCar.fxml'.";
        assert textFieldNumberVipSeats != null : "fx:id=\"textFieldNumberVipSeats\" was not injected: check your FXML file 'createCar.fxml'.";
        assert textFieldNumberTopSeats != null : "fx:id=\"textFieldNumberTopSeats\" was not injected: check your FXML file 'createCar.fxml'.";
        assert textFieldNumberLowerSeats != null : "fx:id=\"textFieldNumberLowerSeats\" was not injected: check your FXML file 'createCar.fxml'.";
        assert textFieldNumberSittingSeats != null : "fx:id=\"textFieldNumberSittingSeats\" was not injected: check your FXML file 'createCar.fxml'.";
        assert buttonSaveCar != null : "fx:id=\"buttonSaveCar\" was not injected: check your FXML file 'createCar.fxml'.";
        inputRestriction(textFieldNameCar);
        inputRestriction(textFieldNumberVipSeats);
        inputRestriction(textFieldNumberTopSeats);
        inputRestriction(textFieldNumberLowerSeats);
        inputRestriction(textFieldNumberSittingSeats);
        textFieldNumberVipSeats.setEditable(false);
        textFieldNumberTopSeats.setEditable(false);
        textFieldNumberLowerSeats.setEditable(false);
        textFieldNumberSittingSeats.setEditable(false);
        choiceBoxTypeCar.getItems().addAll("Лежачий", "Сидячий", "Вантажний");
        choiceBoxTypeCar.setOnAction(actionEvent -> choiceBoxTypeCarAc());

        wagonCreator = new WagonCreator();


    }

    public void choiceBoxTypeCarAc(){
        if (choiceBoxTypeCar.getValue() == "Лежачий"){
            textFieldNumberSittingSeats.undo();
            textFieldNumberVipSeats.setEditable(true);
            textFieldNumberTopSeats.setEditable(true);
            textFieldNumberLowerSeats.setEditable(true);
            textFieldNumberSittingSeats.setEditable(false);
            textFieldNumberVipSeats.setStyle("-fx-background-color: #C4C4C4; -fx-background-radius: 0");
            textFieldNumberTopSeats.setStyle("-fx-background-color: #C4C4C4; -fx-background-radius: 0");
            textFieldNumberLowerSeats.setStyle("-fx-background-color: #C4C4C4; -fx-background-radius: 0");
            textFieldNumberSittingSeats.setStyle("-fx-background-color: #DCDCDC; -fx-background-radius: 0");
        }
        else if (choiceBoxTypeCar.getValue() == "Сидячий"){
            textFieldNumberVipSeats.undo();
            textFieldNumberTopSeats.undo();
            textFieldNumberLowerSeats.undo();
            textFieldNumberVipSeats.setEditable(false);
            textFieldNumberTopSeats.setEditable(false);
            textFieldNumberLowerSeats.setEditable(false);
            textFieldNumberSittingSeats.setEditable(true);
            textFieldNumberVipSeats.setStyle("-fx-background-color: #DCDCDC; -fx-background-radius: 0");
            textFieldNumberTopSeats.setStyle("-fx-background-color: #DCDCDC; -fx-background-radius: 0");
            textFieldNumberLowerSeats.setStyle("-fx-background-color: #DCDCDC; -fx-background-radius: 0");
            textFieldNumberSittingSeats.setStyle("-fx-background-color: #C4C4C4; -fx-background-radius: 0");
        }
        else if (choiceBoxTypeCar.getValue() == "Вантажний"){
            textFieldNumberVipSeats.undo();
            textFieldNumberTopSeats.undo();
            textFieldNumberLowerSeats.undo();
            textFieldNumberSittingSeats.undo();
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
