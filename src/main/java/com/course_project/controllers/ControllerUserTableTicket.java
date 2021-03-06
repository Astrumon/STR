package com.course_project.controllers;

import com.course_project.FxmlLoader;
import com.course_project.data_access.model.route.RouteSet;
import com.course_project.data_access.model.wagon.Place;
import com.course_project.data_access.model.wagon.Wagon;
import com.course_project.support.AlertGenerator;
import com.course_project.support.NumberIDGenerator;
import com.course_project.support.creator.RouteCreator;
import com.course_project.support.manager.RouteManager;
import com.course_project.support.manager.WagonManager;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Данный класс реализует логику контроллера графического интерфейса экрана таблицы билетов
 * Создает таблицу в которой перечислены расписания маршрутов, данные предоставляет класс RouteCreator, WagonManager
 * Содержит обработку двойного нажатия на строку таблицы, передает нужные данные контроллеру ControllerUserBuyTicket
 */
public class ControllerUserTableTicket {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane stackPaneUserTableTicket;

    @FXML
    private AnchorPane anchorPaneUserTableTicket;

    @FXML
    private TextField textFieldLastPoint;

    @FXML
    private TextField textFieldFirstPoint;

    @FXML
    private Button buttonFindTicket;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TableView<RouteSet> tableUserTicket;

    @FXML
    private TableColumn tblNumber;

    @FXML
    private TableColumn tblFirstPoint;

    @FXML
    private TableColumn tblLastPoint;

    @FXML
    private TableColumn tblFreePlaces;

    @FXML
    private TableColumn tblSendTime;

    @FXML
    private TableColumn tblArriveTime;

    private RouteCreator routeCreator;
    private WagonManager wagonManager;
    private ObservableList<RouteSet> routeSets;
    private List<RouteSet> listRouteSets = new ArrayList<>();

    @FXML
    void buttonFindTicketAc(ActionEvent event) {
        tableUserTicket.getItems().clear();
        RouteSet routeSet = getFilledRouteSet();
        if (routeSet != null) {
            //listRouteSets = routeCreator.getRouteManager().getRouteSetsByFromToDate(routeSet);
            listRouteSets = findRoutes();
        }
        fillTable();
    }

    private List<RouteSet> findRoutes() {
        List<RouteSet> routeSets = new ArrayList<>();
        List<RouteSet> routeWithFromTownAndData = new ArrayList<>();
        List<RouteSet> routeWithToTown = new ArrayList<>();
        int sum = 0;
        for (RouteSet routeSet : routeCreator.getRouteManager().getRouteSets()) {

            if (routeSet.getFromTown().equals(textFieldFirstPoint.getText())
                    && routeSet.getDateSend().equals(datePicker.getValue().toString())) {
                routeWithFromTownAndData.add(routeSet);
            }

            if (routeSet.getToTown().equals(textFieldLastPoint.getText())) {
                routeWithToTown.add(routeSet);
            }
        }

        int count = 0;
        System.out.println(routeWithToTown);
        for (RouteSet routeSet : routeWithFromTownAndData) {
            for (RouteSet routeSet1 : routeWithToTown) {
                if (routeSet.getTrainName().equals(routeSet1.getTrainName())) {
                    System.out.println(routeSet.getTrainName());
                    System.out.println(routeSet1.getTrainName());
                    for (RouteSet routeSet11 : routeCreator.getRouteManager().getRouteSets()) {
                        if (routeSet11.getTrainName().equals(routeSet1.getTrainName())) {
                            System.out.println(routeSet11);

                            if (routeSet11.getFromTown().equals(textFieldFirstPoint.getText())
                                    && routeSet11.getToTown().equals(textFieldLastPoint.getText())) {
                                sum = routeSet11.getPrice();
                                break;
                            }
                            if (!routeSet11.getToTown().equals(routeSet1.getToTown())) {
                                count++;
                                sum += routeSet11.getPrice();
                            } else {
                                if (count == 0) {
                                    sum = routeSet11.getPrice();
                                    break;
                                }
                                sum += routeSet11.getPrice();
                                break;
                            }
                        }
                    }

                    System.out.println("SUM " + sum);
                    routeSet.setArriveTime(routeSet1.getArriveTime());
                    routeSet.setDateArrive(routeSet1.getDateArrive());
                    routeSet.setToTown(routeSet1.getToTown());
                    routeSet.setPrice(sum);
                    routeSets.add(routeSet);
                    sum = 0;
                }
            }
        }


        return routeSets;
    }


    public void fillTable() {
        routeSets = tableUserTicket.getItems();
        routeSets.addAll(listRouteSets);

        tblFirstPoint.setCellValueFactory(new PropertyValueFactory<RouteSet, String>("fromTown"));

        tblLastPoint.setCellValueFactory(new PropertyValueFactory<RouteSet, String>("toTown"));

        tblSendTime.setCellValueFactory(new PropertyValueFactory<RouteSet, String>("sendTime"));

        tblArriveTime.setCellValueFactory(new PropertyValueFactory<RouteSet, String>("arriveTime"));

        tblFreePlaces.setCellValueFactory(new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures cellDataFeatures) {
                RouteSet routeSet = (RouteSet) cellDataFeatures.getValue();
                List<Wagon> wagons = wagonManager.getWagonsByTrainName(routeSet.getTrainName());
                int freePlace = 0;
                for (Wagon wagon : wagons) {
                    for (Place place : wagonManager.getPlacesByIdWagon(wagon.getIdWagon())) {
                        if (place.getStatus() == Place.FREE) {
                            freePlace++;
                        }
                    }
                }
                return new SimpleIntegerProperty(freePlace);
            }
        });

        tblNumber.setCellValueFactory(new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures cellDataFeatures) {
                return new SimpleIntegerProperty(NumberIDGenerator.generate(routeSets, cellDataFeatures));
            }

        });
    }

    private RouteSet getFilledRouteSet() {
        RouteSet routeSet = new RouteSet();
        if (routeCreator.isValidPoint(textFieldFirstPoint.getText())) {
            routeSet.setFromTown(textFieldFirstPoint.getText());
        } else {
            return null;
        }

        if (routeCreator.isValidPoint(textFieldLastPoint.getText())) {
            routeSet.setToTown(textFieldLastPoint.getText());
        } else {
            return null;
        }

        if (!datePicker.getEditor().getText().isEmpty()) {
            routeSet.setDateSend(datePicker.getValue().toString());
        } else {
            AlertGenerator.error("Невірно вказана дата");
            return null;
        }

        return routeSet;
    }

    @FXML
    void initialize() {
        assert textFieldLastPoint != null : "fx:id=\"textFieldLastPoint\" was not injected: check your FXML file 'userTableTicket.fxml'.";
        assert textFieldFirstPoint != null : "fx:id=\"textFieldFirstPoint\" was not injected: check your FXML file 'userTableTicket.fxml'.";
        assert buttonFindTicket != null : "fx:id=\"buttonFindTicket\" was not injected: check your FXML file 'userTableTicket.fxml'.";
        assert datePicker != null : "fx:id=\"datePicker\" was not injected: check your FXML file 'userTableTicket.fxml'.";
        assert tableUserTicket != null : "fx:id=\"tableUserTicket\" was not injected: check your FXML file 'userTableTicket.fxml'.";
        assert tblNumber != null : "fx:id=\"tblNumber\" was not injected: check your FXML file 'userTableTicket.fxml'.";
        assert tblFirstPoint != null : "fx:id=\"tblFirstPoint\" was not injected: check your FXML file 'userTableTicket.fxml'.";
        assert tblLastPoint != null : "fx:id=\"tblLastPoint\" was not injected: check your FXML file 'userTableTicket.fxml'.";
        assert tblFreePlaces != null : "fx:id=\"tblFreePlaces\" was not injected: check your FXML file 'userTableTicket.fxml'.";
        assert tblSendTime != null : "fx:id=\"tblSendTime\" was not injected: check your FXML file 'userTableTicket.fxml'.";
        assert tblArriveTime != null : "fx:id=\"tblArriveTime\" was not injected: check your FXML file 'userTableTicket.fxml'.";
        wagonManager = new WagonManager();
        routeCreator = new RouteCreator();
        clickToEdit();
    }

    public void clickToEdit() {
        tableUserTicket.setRowFactory(tv -> {
            TableRow<RouteSet> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    RouteManager.transferRouteSet = row.getItem();
                    FxmlLoader object = new FxmlLoader();
                    Pane view = object.getPage("userBuyTicket");

                    stackPaneUserTableTicket.getChildren().remove(anchorPaneUserTableTicket);
                    stackPaneUserTableTicket.getChildren().add(view);
                }
            });
            return row;
        });
    }

}
