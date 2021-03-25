package com.course_project.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.course_project.data_access.model.wagon.Wagon;
import com.course_project.data_access.model.warehouse.Warehouse;
import com.course_project.data_access.model.warehouse.WarehouseSet;
import com.course_project.support.*;
import com.course_project.support.manager.WagonManager;
import com.course_project.support.manager.WarehouseManager;
import com.course_project.support.updater.WarehouseUpdater;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Данный класс реализует логику контроллера графического интерфейса экрана изменения конкретного склада
 * Содержит обработку нажатий на кнопки "Зберегти", "Додати на склад", "Видалити з складу"
 * С помощью класса WarehouseUpdater вызывается логика изменения информации про склад
 */
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

    private WarehouseUpdater warehouseUpdater;

    @FXML
    void buttonDeleteStorageAc(ActionEvent event) {
        setWarehouseName();
        if (warehouseUpdater.delete(warehouseName)) {
            AlertGenerator.info("Склад успішно видалено");
        } else {
            AlertGenerator.error("Виникла помилка при видаленні вагону");
        }
    }

    @FXML
    void buttonDeleteWagonFromStorage() {
        setWarehouseName();
       warehouseUpdater.deleteWagon(warehouseName, getEmployedWagonsFromList());
        updateListView();
        warehouseUpdater.updateCountWagons(warehouseName, lstViewCarInTheStorage.getItems().size());
    }

    @FXML
    void buttonSaveStorageAc(ActionEvent event) {
        if (isCorrectWarehouseName()) {

            setWarehouseName();
            warehouseUpdater.addWagon(warehouseName, getFreeWagonsFromList());
            updateListView();
            warehouseUpdater.updateCountWagons(warehouseName, lstViewCarInTheStorage.getItems().size());

        } else {
            AlertGenerator.error("Введіть коректну назву складу");
        }
    }

    @FXML
    void initialize() {
        assert textFieldNameStorage != null : "fx:id=\"textFieldNameStorage\" was not injected: check your FXML file 'updateStorage.fxml'.";
        assert buttonDeleteStorage != null : "fx:id=\"buttonDeleteStorage\" was not injected: check your FXML file 'updateStorage.fxml'.";
        assert lstViewFreeCar != null : "fx:id=\"lstViewFreeCar\" was not injected: check your FXML file 'updateStorage.fxml'.";
        assert lstViewCarInTheStorage != null : "fx:id=\"lstViewCarInTheStorage\" was not injected: check your FXML file 'updateStorage.fxml'.";
        assert buttonAddToStorage != null : "fx:id=\"buttonAddToStorage\" was not injected: check your FXML file 'updateStorage.fxml'.";
        assert buttonRemoveFromStorage != null : "fx:id=\"buttonRemoveFromStorage\" was not injected: check your FXML file 'updateStorage.fxml'.";
        warehouseUpdater = new WarehouseUpdater();

        loadWagonsInfoToLstView();
        setWarehouseName();
        warehouseUpdater.updateCountWagons(warehouseName, lstViewCarInTheStorage.getItems().size());
    }

    private void loadWagonsInfoToLstView() {
        String nameWarehouse = WarehouseManager.transfer.getName();
        textFieldNameStorage.setText(nameWarehouse);

        for (Wagon wagon : warehouseUpdater.getWagonManager().getWagons()) {
            if (wagon.getNameWarehouse() == null) {
                lstViewFreeCar.getItems().addAll(ControllerTableCar.WAGON_PREFIX_NAME + wagon.getIdWagon());
            }else if ( wagon.getNameWarehouse().equals(nameWarehouse)) {
                lstViewCarInTheStorage.getItems().addAll(ControllerTableCar.WAGON_PREFIX_NAME + wagon.getIdWagon());
            }

        }
        lstViewCarInTheStorage.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lstViewFreeCar.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    private void setWarehouseName() {
        if (isCorrectWarehouseName()) {
            warehouseName = textFieldNameStorage.getText();
        } else {
            AlertGenerator.error("Введіть коректну назву складу");
        }
    }

    private List<String> getFreeWagonsFromList() {
        return lstViewFreeCar.getSelectionModel().getSelectedItems();
    }

    private List<String> getEmployedWagonsFromList() {
        return lstViewCarInTheStorage.getSelectionModel().getSelectedItems();
    }

    private boolean isCorrectWarehouseName() {
        return !Checker.checkEmptyValue(textFieldNameStorage.getText())
                && Checker.checkStringValue(textFieldNameStorage.getText());
    }

    private void updateListView() {
        clearListView();
        loadWagonsInfoToLstView();
    }

    private void clearListView() {
        lstViewCarInTheStorage.getItems().clear();
        lstViewFreeCar.getItems().clear();
    }
}
