package com.course_project.controllers;

import com.course_project.FxmlLoader;
import com.course_project.data_access.model.train.Train;
import javafx.beans.property.SimpleIntegerProperty;
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
import com.course_project.support.manager.TrainManager;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Данный класс реализует логику контроллера графического интерфейса экрана таблицы поездов
 * Создает таблицу в которой перечислены созданные поезда, данные предоставляет класс TrainManager
 * Содержит обработку двойного нажатия на строку таблицы, передает нужные данные контроллеру ControllerUpdateTrain
 */
public class ControllerTableTrain {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane stackPaneTrain;

    @FXML
    private AnchorPane anchorPaneTableTrain;

    @FXML
    private TableView tableTrain;

    @FXML
    private TableColumn tblNumber;

    @FXML
    private TableColumn tblName;

    @FXML
    private TableColumn tblCountWagons;

    private ObservableList trains;

    @FXML
    void initialize() {
        fillTable();
        clickToEdit();
    }

    public void fillTable() {
        TrainManager trainManager = new TrainManager();

        trains = tableTrain.getItems();

        trains.addAll(trainManager.getTrains());

        tblName.setCellValueFactory(new PropertyValueFactory<Train, String>("name"));

        tblNumber.setCellValueFactory(new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures cellDataFeatures) {
                return new SimpleIntegerProperty(NumberIDGenerator.generate(trains, cellDataFeatures));
            }
        });

        tblCountWagons.setCellValueFactory(new PropertyValueFactory<Train, String>("countWagon"));

        tableTrain.setItems(trains);
    }

    public void clickToEdit() {
        tableTrain.setRowFactory(tv -> {
            TableRow<Train> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    TrainManager.transfer = row.getItem();

                    FxmlLoader object = new FxmlLoader();
                    Pane view = object.getPage("updateTrain");
                    stackPaneTrain.getChildren().remove(anchorPaneTableTrain);
                    stackPaneTrain.getChildren().add(view);
                }
            });
            return row;
        });
    }
}
