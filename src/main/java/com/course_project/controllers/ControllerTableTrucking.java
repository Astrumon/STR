package com.course_project.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.course_project.FxmlLoader;
import com.course_project.data_access.model.Cargo;
import com.course_project.data_access.model.train.Train;
import com.course_project.support.NumberIDGenerator;
import com.course_project.support.manager.CargoManager;
import com.course_project.support.manager.TrainManager;
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

public class ControllerTableTrucking {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane stackPaneTrucking;

    @FXML
    private AnchorPane anchorPaneTableTrucking;

    @FXML
    private TableView<Cargo> tableTrucking;

    @FXML
    private TableColumn tblNumber;

    @FXML
    private TableColumn tblEmail;

    @FXML
    private TableColumn tblStartAndEndPoint;

    private ObservableList cargos;

    @FXML
    void initialize() {
        assert stackPaneTrucking != null : "fx:id=\"stackPaneTrucking\" was not injected: check your FXML file 'tableTrucking.fxml'.";
        assert anchorPaneTableTrucking != null : "fx:id=\"anchorPaneTableTrucking\" was not injected: check your FXML file 'tableTrucking.fxml'.";
        assert tableTrucking != null : "fx:id=\"tableTrucking\" was not injected: check your FXML file 'tableTrucking.fxml'.";
        assert tblNumber != null : "fx:id=\"tblNumber\" was not injected: check your FXML file 'tableTrucking.fxml'.";
        assert tblEmail != null : "fx:id=\"tblEmail\" was not injected: check your FXML file 'tableTrucking.fxml'.";
        assert tblStartAndEndPoint != null : "fx:id=\"tblStartAndEndPoint\" was not injected: check your FXML file 'tableTrucking.fxml'.";
        fillTable();
        clickToEdit();
    }

    public void fillTable() {
        CargoManager cargoManager = new CargoManager();

        cargos = tableTrucking.getItems();

        cargos.addAll(cargoManager.getCargos());

        tblEmail.setCellValueFactory(new PropertyValueFactory<Cargo, String>("email"));

        tblNumber.setCellValueFactory(new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures cellDataFeatures) {
                return new SimpleIntegerProperty(NumberIDGenerator.generate(cargos, cellDataFeatures));
            }
        });

        tblStartAndEndPoint.setCellValueFactory(new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures cellDataFeatures) {
                Cargo cargo = (Cargo) cellDataFeatures.getValue();
                return new SimpleStringProperty(cargo.getFromTown() + " - " + cargo.getToTown());
            }
        });



        tableTrucking.setItems(cargos);
    }

    public void clickToEdit() {
        tableTrucking.setRowFactory(tv -> {
            TableRow<Cargo> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    CargoManager.transfer = row.getItem();

                    FxmlLoader object = new FxmlLoader();
                    Pane view = object.getPage("updateTrucking");

                    stackPaneTrucking.getChildren().remove(anchorPaneTableTrucking);
                    stackPaneTrucking.getChildren().add(view);
                }
            });
            return row;
        });
    }

}
