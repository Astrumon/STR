package com.course_project.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.course_project.data_access.model.train.Train;
import com.course_project.data_access.model.train.TrainSet;
import com.course_project.data_access.model.wagon.Wagon;
import com.course_project.data_access.model.warehouse.WarehouseSet;
import com.course_project.support.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

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

    private String trainName;

    private Train train;

    private TrainManager trainManager = new TrainManager();

    @FXML
    void buttonAddToTrainAc(ActionEvent event) {
        if (isCorrectWarehouseName()) {

            setTrainName();
            addWagon(trainName);
            updateListView();
            updateCountWagons();

        } else {
            AlertGenerator.error("Введіть коректну назву потяга");
        }
    }

    @FXML
    void buttonDeleteTrainAc(ActionEvent event) {

        setTrainName();

        if (trainManager.deleteTrain(trainName)) {
            AlertGenerator.info("Потяг успішно видалено");
        } else {
            AlertGenerator.error("Виникла помилка при видаленні потягу");
        }
    }

    @FXML
    void buttonRemoveFromTrainAc(ActionEvent event) {
        setTrainName();
        deleteWagon(trainName);
        updateListView();
        updateCountWagons();
    }

    @FXML
    void initialize() {
        assert textFieldNameTrain != null : "fx:id=\"textFieldNameTrain\" was not injected: check your FXML file 'updateTrain.fxml'.";
        assert buttonDeleteTrain != null : "fx:id=\"buttonDeleteTrain\" was not injected: check your FXML file 'updateTrain.fxml'.";
        assert lstViewFreeCar != null : "fx:id=\"lstViewFreeCar\" was not injected: check your FXML file 'updateTrain.fxml'.";
        assert lstViewCarConnectedToTheTrain != null : "fx:id=\"lstViewCarConnectedToTheTrain\" was not injected: check your FXML file 'updateTrain.fxml'.";
        assert buttonAddToTrain != null : "fx:id=\"buttonAddToTrain\" was not injected: check your FXML file 'updateTrain.fxml'.";
        assert buttonRemoveFromTrain != null : "fx:id=\"buttonRemoveFromTrain\" was not injected: check your FXML file 'updateTrain.fxml'.";

        loadWagonsInfoToLstView();
        updateCountWagons();
    }



    public void updateCountWagons() {
        setTrainName();
        List<String> list = lstViewCarConnectedToTheTrain.getItems();
        System.out.println("SIZE=" + list.size());
        trainManager.updateCountWagons(trainName, list.size());
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

        WagonManager wagonManager = new WagonManager();
        for (Wagon wagon : wagonManager.getWagons()) {
            if (wagon.getTrainName() == null) {
                lstViewFreeCar.getItems().addAll(ControllerTableCar.WAGON_PREFIX_NAME + wagon.getIdWagon());
            }else if ( wagon.getTrainName().equals(nameTrain)) {
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

    public void updateListView() {
        clearListView();
        loadWagonsInfoToLstView();
    }

    public void clearListView() {
        lstViewCarConnectedToTheTrain.getItems().clear();
        lstViewFreeCar.getItems().clear();
    }

    private void addWagon(String nameWarehouse) {
        for (String nameWagon : getFreeWagonsFromList()) {
            Wagon wagon = new Wagon();
            wagon.setIdWagon(ParseId.getLongId(nameWagon, ControllerTableCar.WAGON_PREFIX_NAME));
            wagon.setType(Wagon.PASSENGER_TYPE);
            if (trainManager.addWagonToTrain(nameWarehouse, wagon, findEmptyPos())) {
                AlertGenerator.info("Вагон успішно додано на склад");
            } else {
                AlertGenerator.error("Виникла помилка при додаванні вагону на склад");
            }
        }

    }
    private int findEmptyPos() {
        int pos = 0;
        for (TrainSet trainSet : trainManager.getTrainSets()) {
            if (trainSet.getIdWagon() == 0) {
                pos = trainSet.getPosWagon();
                break;
            }
        }
        return pos;
    }

    private List<String> getFreeWagonsFromList() {
        return lstViewFreeCar.getSelectionModel().getSelectedItems();
    }

    private List<String> getEmployedWagonsFromList() {
        return lstViewCarConnectedToTheTrain.getSelectionModel().getSelectedItems();
    }

    private void deleteWagon(String nameWarehouse) {
        for (String nameWagon : getEmployedWagonsFromList()) {
            Wagon wagon = new Wagon();
            wagon.setIdWagon(ParseId.getLongId(nameWagon, ControllerTableCar.WAGON_PREFIX_NAME));
            wagon.setType(Wagon.PASSENGER_TYPE);
            if (trainManager.deleteWagonFromTrain(nameWarehouse, wagon)) {
                AlertGenerator.info("Вагон успішно видалено з потягу");
            } else {
                AlertGenerator.error("Виникла помилка при видалені вагону з потягу");
            }
        }

    }


}
