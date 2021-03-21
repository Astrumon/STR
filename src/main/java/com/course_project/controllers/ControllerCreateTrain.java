package com.course_project.controllers;

import com.course_project.data_access.model.train.Train;
import com.course_project.data_access.model.wagon.Wagon;
import com.course_project.support.*;
import com.course_project.support.creator.TrainCreator;
import com.course_project.support.manager.TrainManager;
import com.course_project.support.manager.WagonManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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
    private ListView<String> listViewTrain;

    @FXML
    private ChoiceBox<String> choiceBoxTypeTrain;

    private int typeTrain;

    private Wagon wagon = new Wagon();

    private TrainCreator trainCreator;

    private String trainName;


    @FXML
    void buttonSaveTrainAc(ActionEvent event) {
        setTrainName();
        trainCreator.setNameTrain(trainName);
        trainCreator.setTypeTrain(typeTrain);
        trainCreator.setWagonsFromList(getWagonsFromList());

        if (isCorrectTrainName()) {
            String nameTrain = textFieldNameTrain.getText();
            trainCreator.createTrain(nameTrain);
            trainCreator.addWagon(nameTrain);
        } else {
            AlertGenerator.error("Введіть коректну назву потяга");
        }
    }

    private void setTrainName() {
        if (isCorrectTrainName()) {
            trainName = textFieldNameTrain.getText();
        } 
    }


    private boolean isCorrectTrainName() {
        return !Checker.checkEmptyValue(textFieldNameTrain.getText())
                && Checker.checkStringValue(textFieldNameTrain.getText());
    }


    private List<String> getWagonsFromList() {
        return listViewTrain.getSelectionModel().getSelectedItems();
    }

    @FXML
    void initialize() {
        assert textFieldNameTrain != null : "fx:id=\"textFieldNameTrain\" was not injected: check your FXML file 'createTrain.fxml'.";
        assert buttonSaveTrain != null : "fx:id=\"buttonSaveTrain\" was not injected: check your FXML file 'createTrain.fxml'.";
        assert listViewTrain != null : "fx:id=\"listViewTrain\" was not injected: check your FXML file 'createTrain.fxml'.";
        assert choiceBoxTypeTrain != null : "fx:id=\"choiceBoxTypeTrain\" was not injected: check your FXML file 'createTrain.fxml'.";

        checkBoxInit();
        loadWagonsInfoToListView();

        trainCreator = new TrainCreator();
    }

    public void checkBoxInit() {
        choiceBoxTypeTrain.setValue(wagon.defineType(Wagon.PASSENGER_TYPE));
        choiceBoxTypeTrain.getItems().add(wagon.defineType(Wagon.PASSENGER_TYPE));
        choiceBoxTypeTrain.getItems().add(wagon.defineType(Wagon.CARGO_TYPE));
    }


    private void loadWagonsInfoToListView() {

        if (isPassengerType()) {
            showWagons(Train.PASSENGER_TYPE);
        }

        choiceBoxTypeTrain.valueProperty().addListener((obc, oldItem, newItem) -> {
            if (newItem.equals(wagon.defineType(Wagon.PASSENGER_TYPE))) {
                showWagons(Train.PASSENGER_TYPE);
            } else {
                showWagons(Train.CARGO_TYPE);
            }
        });

        listViewTrain.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    private boolean isPassengerType() {
        return choiceBoxTypeTrain.getValue().equals(wagon.defineType(Train.PASSENGER_TYPE));
    }

    private void showWagons(int type) {
        WagonManager wagonManager = new WagonManager();
        System.out.println("TYPE WAGON: " + type);
        listViewTrain.getItems().clear();
        typeTrain = type;
        for (Wagon wagon : wagonManager.getWagons()) {
            if (wagon.getTrainName() == null && wagon.checkType(wagon.getType()) == type) {
                listViewTrain.getItems().addAll(ControllerTableCar.WAGON_PREFIX_NAME + wagon.getIdWagon());
            }
        }
    }


}
