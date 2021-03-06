package com.course_project.controllers;

import com.course_project.FxmlLoader;
import com.course_project.data_access.model.route.Route;
import com.course_project.support.NumberIDGenerator;
import com.course_project.support.manager.RouteManager;
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

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Данный класс реализует логику контроллера графического интерфейса экрана таблицы маршрутов
 * Создает таблицу в которой перечислены созданные маршруты, данные предоставляет класс RouteManager
 * Содержит обработку двойного нажатия на строку таблицы, передает нужные данные контроллеру ControllerUpdateRoute
 */
public class ControllerTablePath {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane stackPanePath;

    @FXML
    private AnchorPane anchorPaneTablePath;

    @FXML
    private TableView<Route> tablePath;

    @FXML
    private TableColumn tblNumber;

    @FXML
    private TableColumn tblFirstPoint;

    @FXML
    private TableColumn tblLastPoint;

    @FXML
    private TableColumn tblTimeSend;

    @FXML
    private TableColumn tblTimeArrive;

    @FXML
    private TableColumn tblSoldTickets;

    @FXML
    private TableColumn tblAllTickets;

    private ObservableList<Route> routes;


    @FXML
    void initialize() {
        fillTable();
        clickToEdit();
    }

    public void fillTable() {
        RouteManager routeManager = new RouteManager();

        routes = tablePath.getItems();
        routes.addAll(routeManager.getRoutes());

        tblFirstPoint.setCellValueFactory(new PropertyValueFactory<Route, String>("fromTown"));

        tblLastPoint.setCellValueFactory(new PropertyValueFactory<Route, String>("toTown"));

        tblTimeSend.setCellValueFactory(new PropertyValueFactory<Route, String>("timeStart"));

        tblTimeArrive.setCellValueFactory(new PropertyValueFactory<Route, String>("timeEnd"));

        tblSoldTickets.setCellValueFactory(new PropertyValueFactory<Route, String>("soldTickets"));

        tblAllTickets.setCellValueFactory(new PropertyValueFactory<Route, String>("allTickets"));

        tblNumber.setCellValueFactory(new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures cellDataFeatures) {
                return new SimpleIntegerProperty(NumberIDGenerator.generate(routes, cellDataFeatures));
            }
        });
    }

    public void clickToEdit() {
        tablePath.setRowFactory(tv -> {
            TableRow<Route> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    RouteManager.transfer = row.getItem();

                    FxmlLoader object = new FxmlLoader();
                    Pane view = object.getPage("updatePath");

                    stackPanePath.getChildren().remove(anchorPaneTablePath);
                    stackPanePath.getChildren().add(view);
                }
            });
            return row;
        });
    }

}
