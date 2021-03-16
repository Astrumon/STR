package com.course_project.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.course_project.data_access.model.train.Train;
import com.course_project.data_access.model.train.TrainSet;
import com.course_project.data_access.model.wagon.Wagon;
import com.course_project.support.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

public class ControllerCreateTrain {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField textFieldNameTrain;

    @FXML
    private Button buttonSaveTrain;

    @FXML
    private Button buttonDeleteTrain;

    @FXML
    private ListView<String> listViewTrain;

    private TrainManager trainManager;

    private String nameTrain;



    @FXML
    void buttonDeleteTrainAc(ActionEvent event) {
        setTrainName();
        if (trainManager.deleteTrain(nameTrain)) {
            AlertGenerator.info("Потяг успішно видалено");
        } else {
            AlertGenerator.info("Виникла помилка при видаленні потягу");
        }
    }

    private boolean isCorrectTrainName() {
        return !Checker.checkEmptyValue(textFieldNameTrain.getText())
                && Checker.checkStringValue(textFieldNameTrain.getText());
    }

    private void setTrainName() {
        if (isCorrectTrainName()) {
            nameTrain = textFieldNameTrain.getText();
        } else {
            AlertGenerator.error("Введіть коректну назву потяга");
        }
    }

    @FXML
    void buttonSaveTrainAc(ActionEvent event) {

        if (isCorrectTrainName()) {
            String nameTrain = textFieldNameTrain.getText();
            createTrain(nameTrain);
            addWagon(nameTrain);
        } else {
            AlertGenerator.error("Введіть коректну назву потяга");
        }
    }

    private void createTrain(String nameTrain) {
        if (trainManager.getTrains().size() == 0) {
            createFirstTrain(nameTrain);
        }

        int count = 0;
        for (Train train : trainManager.getTrains()) {
            if (!train.getName().equals(nameTrain)) {
                count++;
                if (count == trainManager.getTrains().size()) {
                    if (trainManager.createTrain(nameTrain)) {
                        AlertGenerator.info("Потяг успішно створено");
                    } else {
                        AlertGenerator.error("Виникла помилка при створені потягу");
                    }
                }
            }
        }
    }

    private void createFirstTrain(String nameTrain) {
        if (trainManager.createTrain(nameTrain)) {
            AlertGenerator.info("Потяг успішно створено");
        } else {
            AlertGenerator.error("Виникла помилка при створені потягу");
        }
    }

    private void addWagon(String nameTrain) {


        for (String nameWagon : getWagonsFromList()) {
            Wagon wagon = new Wagon();
            wagon.setIdWagon(ParseId.getLongId(nameWagon, ControllerTableCar.WAGON_PREFIX_NAME));
            wagon.setType(Wagon.PASSENGER_TYPE);
            if (trainManager.addWagonToTrain(nameTrain, wagon, findEmptyPos())) {
                AlertGenerator.info("Вагон успішно приєднано до потягу");
            } else {
                AlertGenerator.error("Виникла помилка при приєднанні вагону до потягу");
            }
        }
    }

    private List<String> getWagonsFromList() {
        return listViewTrain.getSelectionModel().getSelectedItems();
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


    @FXML
    void initialize() {
        assert textFieldNameTrain != null : "fx:id=\"textFieldNameTrain\" was not injected: check your FXML file 'createTrain.fxml'.";
        assert buttonSaveTrain != null : "fx:id=\"buttonSaveTrain\" was not injected: check your FXML file 'createTrain.fxml'.";
        assert buttonDeleteTrain != null : "fx:id=\"buttonDeleteTrain\" was not injected: check your FXML file 'createTrain.fxml'.";
        assert listViewTrain != null : "fx:id=\"listViewTrain\" was not injected: check your FXML file 'createTrain.fxml'.";

        trainManager = new TrainManager();

        loadWagonsInfoToListView();
    }

    private void loadWagonsInfoToListView() {
        WagonManager wagonManager = new WagonManager();

        for (Wagon wagon : wagonManager.getWagons()) {
            if (wagon.getTrainName() == null) {
                listViewTrain.getItems().addAll(ControllerTableCar.WAGON_PREFIX_NAME + wagon.getIdWagon());
            }
        }
        listViewTrain.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}
