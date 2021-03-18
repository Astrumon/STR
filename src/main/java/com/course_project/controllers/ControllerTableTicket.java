package com.course_project.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.course_project.data_access.model.Ticket;
import com.course_project.data_access.model.route.Route;
import com.course_project.support.NumberIDGenerator;
import com.course_project.support.manager.RouteManager;
import com.course_project.support.manager.TicketManager;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class ControllerTableTicket {

    @FXML
    public TableView<Ticket> tableTicket;

    @FXML
    public TableColumn tblFirstPoint;

    @FXML
    public TableColumn tblNumber;

    @FXML
    public TableColumn tblLastPoint;

    @FXML
    public TableColumn tblPrice;

    @FXML
    public TableColumn tblLinen;

    @FXML
    public TableColumn tblSendTime;

    @FXML
    public TableColumn tblArriveTime;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    private TicketManager ticketManager;

    private ObservableList<Ticket> tickets;

    @FXML
    void initialize() {
        fillTable();
    }

    public void fillTable() {
        ticketManager = new TicketManager();



        tickets = tableTicket.getItems();
        tickets.addAll(ticketManager.getTickets());


        tblFirstPoint.setCellValueFactory(new PropertyValueFactory<Route, String>("toTown"));

        tblLastPoint.setCellValueFactory(new PropertyValueFactory<Route, String>("fromTown"));

        tblSendTime.setCellValueFactory(new PropertyValueFactory<Route, String>("timeStart"));

        tblArriveTime.setCellValueFactory(new PropertyValueFactory<Route, String>("timeEnd"));

        tblLinen.setCellValueFactory(new PropertyValueFactory<Route, String>("linen"));

        tblPrice.setCellValueFactory(new PropertyValueFactory<Route, String>("price"));

        tblNumber.setCellValueFactory(new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures cellDataFeatures) {
                return new SimpleIntegerProperty(NumberIDGenerator.generate(tickets, cellDataFeatures));
            }
        });
    }

    public void clickToEdit() {
//        tableWarehouse.setRowFactory(tv -> {
//            TableRow<Warehouse> row = new TableRow<>();
//            row.setOnMouseClicked(event -> {
//                if (event.getClickCount() == 2 && (!row.isEmpty())) {
//                    WarehouseManager.transfer = row.getItem();
//
//                    FxmlLoader object = new FxmlLoader();
//                    Pane view = object.getPage("updateStorage");
//
//                    stackPaneStorage.getChildren().remove(anchorPaneTableStorage);
//                    stackPaneStorage.getChildren().add(view);
//                }
//            });
//            return row;
//        });
    }
}
