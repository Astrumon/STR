package com.course_project.controllers;

import com.course_project.FxmlLoader;
import com.course_project.data_access.model.warehouse.Warehouse;
import com.course_project.support.manager.WagonManager;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import com.course_project.support.NumberIDGenerator;
import com.course_project.support.manager.WarehouseManager;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Данный класс реализует логику контроллера графического интерфейса экрана таблицы складов
 * Создает таблицу в которой перечислены созданные склады, данные предоставляет класс WarehouseManager
 * Содержит обработку двойного нажатия на строку таблицы, передает нужные данные контроллеру ControllerUpdateWarehouse
 */
public class ControllerTableStorage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane stackPaneStorage;

    @FXML
    private AnchorPane anchorPaneTableStorage;

    @FXML
    private TableView<Warehouse> tableWarehouse;

    @FXML
    private TableColumn tblNumber;

    @FXML
    private TableColumn tblName;

    @FXML
    private TableColumn tblCountCars;

    private ObservableList<Warehouse> warehouses;

    @FXML
    void initialize() {
        fillTable();
        clickToEdit();
    }

    public void fillTable() {
        WarehouseManager warehouseManager = new WarehouseManager();

        warehouses = tableWarehouse.getItems();
        warehouses.addAll(warehouseManager.getWarehouses());

        tblName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures cellDataFeatures) {
                String name = ((Warehouse) cellDataFeatures.getValue()).getName();

                return new SimpleStringProperty(name);
            }
        });

        tblNumber.setCellValueFactory(new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures cellDataFeatures) {
                return new SimpleIntegerProperty(NumberIDGenerator.generate(warehouses, cellDataFeatures));
            }
        });

        tblCountCars.setCellValueFactory(new PropertyValueFactory<Warehouse, String>("countWagons"));

        tableWarehouse.setItems(warehouses);
    }


    public void clickToEdit() {
        tableWarehouse.setRowFactory(tv -> {
            TableRow<Warehouse> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    WarehouseManager.transfer = row.getItem();

                    FxmlLoader object = new FxmlLoader();
                    Pane view = object.getPage("updateStorage");
                    stackPaneStorage.getChildren().remove(anchorPaneTableStorage);
                    stackPaneStorage.getChildren().add(view);
                }
            });
            return row;
        });
    }

}
