package com.course_project.controllers;

import com.course_project.data_access.model.wagon.Wagon;
import com.course_project.data_access.model.warehouse.Warehouse;
import com.course_project.data_access.model.warehouse.WarehouseSet;
import com.course_project.support.*;
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

    private WarehouseManager warehouseManager;

    private String warehouseName;

    @FXML
    void buttonDeleteStorageAc(ActionEvent event) {

        setWarehouseName();

        if (warehouseManager.deleteWarehouse(warehouseName)) {
            AlertGenerator.info("Склад успішно видалено");
        } else {
            AlertGenerator.error("Виникла помилка при видаленні вагону");
        }
    }


    private int findEmptyPos() {
        int pos = 0;
        for (WarehouseSet warehouseSet : warehouseManager.getWarehouseSets()) {
            if (warehouseSet.getIdWagon() == 0) {
                pos = warehouseSet.getPosition();
                break;
            }
        }
        return pos;
    }


    private void createWarehouse(String nameWarehouse) {

        if (warehouseManager.getWarehouses().size() == 0) {
            createFirstWarehouse(nameWarehouse);
        }

        int count = 0;
        for (Warehouse warehouse : warehouseManager.getWarehouses()) {
            if (!warehouse.getName().equals(nameWarehouse)) {
                count++;
                if (count == warehouseManager.getWarehouses().size()) {
                    if (warehouseManager.createWarehouse(nameWarehouse)) {
                        AlertGenerator.info("Склад успішно створено");
                    } else {
                        AlertGenerator.error("Виникла помилка при створені складу");
                    }
                }
            }
        }
    }

    private void createFirstWarehouse(String nameWarehouse) {
        if (warehouseManager.createWarehouse(nameWarehouse)) {
            AlertGenerator.info("Склад успішно створено");
        } else {
            AlertGenerator.error("Виникла помилка при створені складу");
        }
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

    private void addWagon(String nameWarehouse) {
        for (String nameWagon : getWagonsFromList()) {
            Wagon wagon = new Wagon();
            wagon.setIdWagon(ParseId.getLongId(nameWagon, ControllerTableCar.WAGON_PREFIX_NAME));
            wagon.setType(Wagon.PASSENGER_TYPE);
            if (warehouseManager.addWagonToWarehouse(nameWarehouse, wagon, findEmptyPos())) {
                AlertGenerator.info("Вагон успішно додано на склад");
            } else {
                AlertGenerator.error("Виникла помилка при додаванні вагону на склад");
            }
        }
    }

    private List<String> getWagonsFromList() {
        return listViewCar.getSelectionModel().getSelectedItems();
    }

    @FXML
    void buttonSaveStorageAc(ActionEvent event) {

        if (isCorrectWarehouseName()) {
            setWarehouseName();
            createWarehouse(warehouseName);
            addWagon(warehouseName);
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

        warehouseManager = new WarehouseManager();

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
