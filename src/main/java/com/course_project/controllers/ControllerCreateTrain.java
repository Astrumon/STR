package com.course_project.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.course_project.data_access.dao.impl.wagon_dao_impl.WagonDaoImpl;
import com.course_project.data_access.model.train.Train;
import com.course_project.data_access.model.train.TrainSet;
import com.course_project.data_access.model.wagon.Wagon;
import com.course_project.support.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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

    @FXML
    private ChoiceBox<String> choiceBoxTypeTrain;

    private TrainManager trainManager;

    private String nameTrain;

    private int typeTrain;
    private Wagon wagon = new Wagon();


    @FXML
    void test(ActionEvent event) {
        System.out.println("TEST");
    }
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
        System.out.println("TYPE: " + typeTrain );
        if (trainManager.getTrains().size() == 0) {
            createFirstTrain(nameTrain, typeTrain);
        }

        int count = 0;
        for (Train train : trainManager.getTrains()) {
            if (!train.getName().equals(nameTrain)) {
                count++;
                if (count == trainManager.getTrains().size()) {
                    if (trainManager.createTrain(nameTrain, typeTrain)) {
                        AlertGenerator.info("Потяг успішно створено");
                    } else {
                        AlertGenerator.error("Виникла помилка при створені потягу");
                    }
                }
            }
        }
    }

    private void createFirstTrain(String nameTrain, int typeTrain) {
        System.out.println("TYPE: " + typeTrain );
        if (trainManager.createTrain(nameTrain, typeTrain)) {
            AlertGenerator.info("Потяг успішно створено");
        } else {
            AlertGenerator.error("Виникла помилка при створені потягу");
        }
    }

    private void addWagon(String nameTrain) {

        WagonManager wagonManager = new WagonManager();

        for (String nameWagon : getWagonsFromList()) {
            Long idWagon = ParseId.getLongId(nameWagon, ControllerTableCar.WAGON_PREFIX_NAME);
            System.out.println("TYPE WAGON = " + wagonManager.getWagon(idWagon).getType());
            Wagon wagon = new Wagon();
            wagon.setIdWagon(idWagon);
            wagon.setType(wagonManager.getWagon(idWagon).getType());

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
        assert choiceBoxTypeTrain != null : "fx:id=\"choiceBoxTypeTrain\" was not injected: check your FXML file 'createTrain.fxml'.";

        trainManager = new TrainManager();
        checkBoxInit();


        loadWagonsInfoToListView();
    }

    public void checkBoxInit() {
        choiceBoxTypeTrain.setValue(wagon.defineType(Wagon.PASSENGER_TYPE));
        choiceBoxTypeTrain.getItems().add(wagon.defineType(Wagon.PASSENGER_TYPE));
        choiceBoxTypeTrain.getItems().add(wagon.defineType(Wagon.CARGO_TYPE));
    }


    private void loadWagonsInfoToListView() {
        WagonManager wagonManager = new WagonManager();


            if (choiceBoxTypeTrain.getValue().equals(wagon.defineType(Train.PASSENGER_TYPE))) {
                typeTrain = Train.PASSENGER_TYPE;
                //System.out.println("TYPE: " + typeTrain );
                for (Wagon wagon : wagonManager.getWagons()) {
                    if (wagon.getTrainName() == null && wagon.getType() == Wagon.PASSENGER_TYPE) {
                        listViewTrain.getItems().addAll(ControllerTableCar.WAGON_PREFIX_NAME + wagon.getIdWagon());
                    }
                }
            }

                choiceBoxTypeTrain.valueProperty().addListener((obc, oldItem, newItem) -> {
                    if (newItem.equals(wagon.defineType(Wagon.PASSENGER_TYPE))) {
                        typeTrain = Train.PASSENGER_TYPE;
                       // System.out.println("TYPE: " + typeTrain );
                        listViewTrain.getItems().clear();
                        for (Wagon wagon : wagonManager.getWagons()) {
                            if (wagon.getTrainName() == null && wagon.getType() == Wagon.PASSENGER_TYPE) {
                                listViewTrain.getItems().addAll(ControllerTableCar.WAGON_PREFIX_NAME + wagon.getIdWagon());
                            }
                        }
                    } else {
                        typeTrain = Train.CARGO_TYPE;
                        //System.out.println("TYPE: " + typeTrain );
                        listViewTrain.getItems().clear();
                        for (Wagon wagon : wagonManager.getWagons()) {
                            if (wagon.getTrainName() == null && wagon.getType() == Wagon.CARGO_TYPE) {
                                listViewTrain.getItems().addAll(ControllerTableCar.WAGON_PREFIX_NAME + wagon.getIdWagon());
                            }
                        }
                    }
                });



        listViewTrain.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }


}
