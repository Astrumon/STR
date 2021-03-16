package com.course_project.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.course_project.data_access.model.wagon.Wagon;
import com.course_project.data_access.model.warehouse.Warehouse;
import com.course_project.support.WagonManager;
import com.course_project.support.WarehouseManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

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






    private Warehouse warehouse;

    private WarehouseManager warehouseManager = new WarehouseManager();

    @FXML
    void buttonDeleteStorageAc(ActionEvent event) {

    }

    @FXML
    void buttonSaveStorageAc(ActionEvent event) {

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


       // System.out.println(TransferWarehouse.warehouse);
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
        textFieldNameStorage.setEditable(false);
        lstViewCarInTheStorage.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lstViewFreeCar.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}
