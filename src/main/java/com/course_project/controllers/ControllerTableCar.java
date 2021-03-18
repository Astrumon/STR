package com.course_project.controllers;

import com.course_project.FxmlLoader;
import com.course_project.data_access.model.wagon.Wagon;
import com.course_project.support.NumberIDGenerator;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerTableCar {

    public static final String WAGON_PREFIX_NAME = "Вагон№ ";
    @FXML
    private BorderPane mainPane;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private StackPane stackPaneCar;
    @FXML
    private AnchorPane anchorPaneTableCar;
    @FXML
    private TableView<Wagon> tableCar;
    @FXML
    private TableColumn tblName;
    @FXML
    private TableColumn tblNumber;
    @FXML
    private TableColumn tblCount;


    private ObservableList<Wagon> wagons;

    @FXML
    void initialize() {
        fillTable();
        clickToEdit();
    }

    public void fillTable() {
        WagonManager wagonManager = new WagonManager();

        wagons = tableCar.getItems();
        wagons.addAll(wagonManager.getWagons());

        tblName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures cellDataFeatures) {
                Wagon wagon = (Wagon) cellDataFeatures.getValue();
                return new SimpleStringProperty(WAGON_PREFIX_NAME + wagon.getIdWagon());
            }
        });

        tblCount.setCellValueFactory(new PropertyValueFactory<Wagon, String>("countSeats"));

        tblNumber.setCellValueFactory(new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures cellDataFeatures) {
                return new SimpleIntegerProperty(NumberIDGenerator.generate(wagons, cellDataFeatures));
            }
        });

        tableCar.setItems(wagons);
    }

    public void clickToEdit() {
        tableCar.setRowFactory(tv -> {
            TableRow<Wagon> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    WagonManager.transfer = row.getItem();

                    FxmlLoader object = new FxmlLoader();
                    Pane view = object.getPage("updateCar");
                    stackPaneCar.getChildren().remove(anchorPaneTableCar);
                    stackPaneCar.getChildren().add(view);
                }
            });
            return row;
        });
    }
}





