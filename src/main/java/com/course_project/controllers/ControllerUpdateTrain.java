package com.course_project.controllers;

import com.course_project.data_access.model.wagon.Wagon;
import com.course_project.support.AlertGenerator;
import com.course_project.support.Checker;
import com.course_project.support.manager.TrainManager;
import com.course_project.support.updater.TrainUpdater;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Данный класс реализует логику контроллера графического интерфейса экрана изменения конкретного поезда
 * Содержит обработку нажатий на кнопки "Зберегти", "Приєднати до потягу", "Від'єднати від потягу", "Видалити"
 * С помощью класса TrainUpdater вызывается логика изменения информации про поезд
 */
public class ControllerUpdateTrain {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField textFieldNameTrain;

    @FXML
    private Button buttonDeleteTrain;

    @FXML
    private ListView<String> lstViewFreeCar;

    @FXML
    private ListView<String> lstViewCarConnectedToTheTrain;

    @FXML
    private Button buttonAddToTrain;

    @FXML
    private Button buttonRemoveFromTrain;

    @FXML
    private GridPane updateTrainPane;

    private String trainName;

    private TrainUpdater trainUpdater;

    @FXML
    void buttonAddToTrainAc(ActionEvent event) {
        setTrainName();
        trainUpdater.addWagon(trainName, getFreeWagonsFromList());
        updateListView();
        trainUpdater.updateCountWagons(trainName, lstViewCarConnectedToTheTrain.getItems().size());
    }

    @FXML
    void buttonDeleteTrainAc(ActionEvent event) {

        setTrainName();
        if (trainUpdater.delete(trainName)) {
            AlertGenerator.info("Потяг успішно видалено");
        } else {
            AlertGenerator.error("Виникла помилка при видаленні потягу");
        }
    }

    @FXML
    void buttonRemoveFromTrainAc(ActionEvent event) {
        setTrainName();
        trainUpdater.deleteWagon(trainName, getEmployedWagonsFromList());
        updateListView();
        trainUpdater.updateCountWagons(trainName, lstViewCarConnectedToTheTrain.getItems().size());
    }

    @FXML
    void initialize() {
        assert textFieldNameTrain != null : "fx:id=\"textFieldNameTrain\" was not injected: check your FXML file 'updateTrain.fxml'.";
        assert buttonDeleteTrain != null : "fx:id=\"buttonDeleteTrain\" was not injected: check your FXML file 'updateTrain.fxml'.";
        assert lstViewFreeCar != null : "fx:id=\"lstViewFreeCar\" was not injected: check your FXML file 'updateTrain.fxml'.";
        assert lstViewCarConnectedToTheTrain != null : "fx:id=\"lstViewCarConnectedToTheTrain\" was not injected: check your FXML file 'updateTrain.fxml'.";
        assert buttonAddToTrain != null : "fx:id=\"buttonAddToTrain\" was not injected: check your FXML file 'updateTrain.fxml'.";
        assert buttonRemoveFromTrain != null : "fx:id=\"buttonRemoveFromTrain\" was not injected: check your FXML file 'updateTrain.fxml'.";
        trainUpdater = new TrainUpdater();

        loadWagonsInfoToLstView();
        setTrainName();
        trainUpdater.updateCountWagons(trainName, lstViewCarConnectedToTheTrain.getItems().size());

    }

    private void setTrainName() {
        if (isCorrectWarehouseName()) {
            trainName = textFieldNameTrain.getText();
        } else {
            AlertGenerator.error("Введіть коректну назву потягу");
        }
    }


    private void loadWagonsInfoToLstView() {
        String nameTrain = TrainManager.transfer.getName();
        textFieldNameTrain.setText(nameTrain);

        int typeTrain = trainUpdater.getTrainManager().getTrain(nameTrain).getType();

        for (Wagon wagon : trainUpdater.getWagonManager().getWagons()) {
            if (wagon.getTrainName() == null) {
                if (wagon.checkType(wagon.getType()) == typeTrain) {
                    lstViewFreeCar.getItems().addAll(ControllerTableCar.WAGON_PREFIX_NAME + wagon.getIdWagon());
                } else {
                    continue;
                }
            } else if (wagon.getTrainName().equals(nameTrain)) {
                lstViewCarConnectedToTheTrain.getItems().addAll(ControllerTableCar.WAGON_PREFIX_NAME + wagon.getIdWagon());
            }

        }
        lstViewCarConnectedToTheTrain.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lstViewFreeCar.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    private boolean isCorrectWarehouseName() {
        return !Checker.checkEmptyValue(textFieldNameTrain.getText())
                && Checker.checkStringValue(textFieldNameTrain.getText());
    }

    private void updateListView() {
        clearListView();
        loadWagonsInfoToLstView();
    }

    private void clearListView() {
        lstViewCarConnectedToTheTrain.getItems().clear();
        lstViewFreeCar.getItems().clear();
    }


    private List<String> getFreeWagonsFromList() {
        return lstViewFreeCar.getSelectionModel().getSelectedItems();
    }

    private List<String> getEmployedWagonsFromList() {
        return lstViewCarConnectedToTheTrain.getSelectionModel().getSelectedItems();
    }
}
