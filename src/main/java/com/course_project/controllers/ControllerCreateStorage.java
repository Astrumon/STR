package com.course_project.controllers;

import com.course_project.data_access.model.wagon.Wagon;
import com.course_project.support.*;
import com.course_project.support.creator.WarehouseCreator;
import com.course_project.support.manager.WagonManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class ControllerCreateStorage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField textFieldNameStorage;

    @FXML
    private Button buttonSaveStorage;

    @FXML
    private Button buttonDeleteStorage;

    @FXML
    private ListView<String> listViewCar;

    @FXML
    private TableView<Wagon> tableCar;


    private String warehouseName;

    private WarehouseCreator warehouseCreator;


    @FXML
    void buttonSaveStorageAc(ActionEvent event) {
        setWarehouseName();
            warehouseCreator.setWagonsFromList(getWagonsFromList());
            warehouseCreator.setNameWarehouse(warehouseName);
        if (isCorrectWarehouseName()) {
            setWarehouseName();
            warehouseCreator.createWarehouse(warehouseName);
            warehouseCreator.addWagon(warehouseName);
        } else {
            AlertGenerator.error("Введіть коректну назву складу");
        }
    }

    private List<String> getWagonsFromList() {
        return listViewCar.getSelectionModel().getSelectedItems();
    }

    private boolean isCorrectWarehouseName() {
        return !Checker.checkEmptyValue(textFieldNameStorage.getText())
                && Checker.checkStringValue(textFieldNameStorage.getText());
    }

    private void setWarehouseName() {
        if (isCorrectWarehouseName()) {
            warehouseName = textFieldNameStorage.getText();
        } else {
            AlertGenerator.error("Введіть коректну назву складу");
        }
    }


    @FXML
    void initialize() {
        assert textFieldNameStorage != null : "fx:id=\"textFieldNameStorage\" was not injected: check your FXML file 'createStorage.fxml'.";
        assert buttonSaveStorage != null : "fx:id=\"buttonSaveStorage\" was not injected: check your FXML file 'createStorage.fxml'.";
        assert buttonDeleteStorage != null : "fx:id=\"buttonDeleteStorage\" was not injected: check your FXML file 'createStorage.fxml'.";
        assert listViewCar != null : "fx:id=\"listViewCar\" was not injected: check your FXML file 'createStorage.fxml'.";

        warehouseCreator = new WarehouseCreator();

        loadWagonsInfoToListView();
    }

    private void loadWagonsInfoToListView() {
        WagonManager wagonManager = new WagonManager();
        for (Wagon wagon : wagonManager.getWagons()) {
            if (wagon.getNameWarehouse() == null) {
                listViewCar.getItems().addAll(ControllerTableCar.WAGON_PREFIX_NAME + wagon.getIdWagon());
            }
        }
        listViewCar.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }





}
