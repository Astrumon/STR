package com.course_project.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.course_project.data_access.model.wagon.Wagon;
import com.course_project.data_access.model.warehouse.Warehouse;
import com.course_project.data_access.model.warehouse.WarehouseSet;
import com.course_project.support.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ControllerUpdateStorage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField textFieldNameStorage;

    @FXML
    private Button buttonDeleteStorage;

    @FXML
    private ListView<String> lstViewFreeCar;

    @FXML
    private ListView<String> lstViewCarInTheStorage;

    @FXML
    private Button buttonAddToStorage;

    @FXML
    private Button buttonRemoveFromStorage;

    @FXML
    private GridPane updateStoragePane;

    private String warehouseName;




    private Warehouse warehouse;

    private WarehouseManager warehouseManager = new WarehouseManager();

    @FXML
    void buttonDeleteStorageAc(ActionEvent event) {
        setWarehouseName();

        if (warehouseManager.deleteWarehouse(warehouseName)) {
            AlertGenerator.info("Склад успішно видалено");
        } else {
            AlertGenerator.error("Виникла помилка при видаленні вагону");
        }
    }

    @FXML
    void buttonDeleteWagonFromStorage() {
        setWarehouseName();
        deleteWagon(warehouseName);
        updateListView();
        updateCountWagons();
    }

    @FXML
    void buttonSaveStorageAc(ActionEvent event) {
        if (isCorrectWarehouseName()) {

            setWarehouseName();
            addWagon(warehouseName);
            updateListView();
            updateCountWagons();

        } else {
            AlertGenerator.error("Введіть коректну назву складу");
        }
    }

    public void updateListView() {
        clearListView();
        loadWagonsInfoToLstView();
    }

    public void clearListView() {
        lstViewCarInTheStorage.getItems().clear();
        lstViewFreeCar.getItems().clear();
    }




    @FXML
    void initialize() {
        assert textFieldNameStorage != null : "fx:id=\"textFieldNameStorage\" was not injected: check your FXML file 'updateStorage.fxml'.";
        assert buttonDeleteStorage != null : "fx:id=\"buttonDeleteStorage\" was not injected: check your FXML file 'updateStorage.fxml'.";
        assert lstViewFreeCar != null : "fx:id=\"lstViewFreeCar\" was not injected: check your FXML file 'updateStorage.fxml'.";
        assert lstViewCarInTheStorage != null : "fx:id=\"lstViewCarInTheStorage\" was not injected: check your FXML file 'updateStorage.fxml'.";
        assert buttonAddToStorage != null : "fx:id=\"buttonAddToStorage\" was not injected: check your FXML file 'updateStorage.fxml'.";
        assert buttonRemoveFromStorage != null : "fx:id=\"buttonRemoveFromStorage\" was not injected: check your FXML file 'updateStorage.fxml'.";

        loadWagonsInfoToLstView();
        updateCountWagons();



       // System.out.println(TransferWarehouse.warehouse);
    }

    private void setWarehouseName() {
        if (isCorrectWarehouseName()) {
            warehouseName = textFieldNameStorage.getText();
        } else {
            AlertGenerator.error("Введіть коректну назву складу");
        }
    }

    private void addWagon(String nameWarehouse) {
        for (String nameWagon : getFreeWagonsFromList()) {
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

    private void deleteWagon(String nameWarehouse) {
        for (String nameWagon : getEmployedWagonsFromList()) {
            Wagon wagon = new Wagon();
            wagon.setIdWagon(ParseId.getLongId(nameWagon, ControllerTableCar.WAGON_PREFIX_NAME));
            wagon.setType(Wagon.PASSENGER_TYPE);
            if (warehouseManager.deleteWagonFromWarehouse(nameWarehouse, wagon)) {
                AlertGenerator.info("Вагон успішно додано на склад");
            } else {
                AlertGenerator.error("Виникла помилка при додаванні вагону на склад");
            }
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

    private List<String> getFreeWagonsFromList() {
        return lstViewFreeCar.getSelectionModel().getSelectedItems();
    }

    private List<String> getEmployedWagonsFromList() {


        return lstViewCarInTheStorage.getSelectionModel().getSelectedItems();
    }

    public void updateCountWagons() {
        setWarehouseName();
        List<String> list = lstViewCarInTheStorage.getItems();
        System.out.println("SIZE=" + list.size());
        warehouseManager.updateCountWagons(warehouseName, list.size());
    }



    private void loadWagonsInfoToLstView() {
        String nameWarehouse = WarehouseManager.transfer.getName();
        textFieldNameStorage.setText(nameWarehouse);

        WagonManager wagonManager = new WagonManager();
        for (Wagon wagon : wagonManager.getWagons()) {
            if (wagon.getNameWarehouse() == null) {
                lstViewFreeCar.getItems().addAll(ControllerTableCar.WAGON_PREFIX_NAME + wagon.getIdWagon());
            }else if ( wagon.getNameWarehouse().equals(nameWarehouse)) {
                lstViewCarInTheStorage.getItems().addAll(ControllerTableCar.WAGON_PREFIX_NAME + wagon.getIdWagon());
            }

        }
        lstViewCarInTheStorage.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lstViewFreeCar.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    private boolean isCorrectWarehouseName() {
        return !Checker.checkEmptyValue(textFieldNameStorage.getText())
                && Checker.checkStringValue(textFieldNameStorage.getText());
    }
}
