package com.course_project.controllers;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.course_project.FxmlLoader;
import com.course_project.data_access.model.route.Route;
import com.course_project.data_access.model.route.RouteSet;
import com.course_project.data_access.model.wagon.Place;
import com.course_project.data_access.model.wagon.Wagon;
import com.course_project.support.AlertGenerator;
import com.course_project.support.NumberIDGenerator;
import com.course_project.support.creator.RouteCreator;
import com.course_project.support.manager.CargoManager;
import com.course_project.support.manager.RouteManager;
import com.course_project.support.manager.WagonManager;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

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

    private RouteCreator routeCreator = new RouteCreator();

    private WagonManager wagonManager = new WagonManager();

    private ObservableList<RouteSet> routeSets;
    private List<RouteSet> listRouteSets = new ArrayList<>();
    private List<Place> places = new ArrayList<>();



    @FXML
    void buttonFindTicketAc(ActionEvent event) {

        RouteSet routeSet = getFilledRouteSet();
        if (routeSet != null) {
            listRouteSets = routeCreator.getRouteManager().getRouteSetsByFromToDate(routeSet);
        }
        fillTable();

    }

    public void fillTable() {

        routeSets = tableUserTicket.getItems();
        routeSets.addAll(listRouteSets);

        List<Wagon> wagons = wagonManager.getWagonsByTrainName(routeSets.get(0).getTrainName());
        places = wagonManager.getPlacesByIdWagon(wagons.get(0).getIdWagon());
        System.out.println(places);
        tblFirstPoint.setCellValueFactory(new PropertyValueFactory<RouteSet, String>("fromTown"));

        tblLastPoint.setCellValueFactory(new PropertyValueFactory<RouteSet, String>("toTown"));

        tblSendTime.setCellValueFactory(new PropertyValueFactory<RouteSet, String>("sendTime"));

        tblArriveTime.setCellValueFactory(new PropertyValueFactory<RouteSet, String>("arriveTime"));

       // tblFreePlaces.setCellValueFactory(new PropertyValueFactory<Route, String>("soldTickets"));


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
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            String formattedValue = (datePicker.getValue()).format(formatter);
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

    }

    /*FxmlLoader object = new FxmlLoader();
    Pane view = object.getPage("userBuyTicket");

    stackPaneUserTableTicket.getChildren().remove(anchorPaneUserTableTicket);
    stackPaneUserTableTicket.getChildren().add(view);*/
}
