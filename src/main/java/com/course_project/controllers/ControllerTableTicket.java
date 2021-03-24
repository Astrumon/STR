package com.course_project.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.course_project.FxmlLoader;
import com.course_project.data_access.model.Ticket;
import com.course_project.data_access.model.route.Route;
import com.course_project.data_access.model.wagon.TypePlace;
import com.course_project.support.NumberIDGenerator;
import com.course_project.support.manager.RouteManager;
import com.course_project.support.manager.TicketManager;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

public class ControllerTableTicket {

    @FXML
    public TableView<Ticket> tableTicket;

    @FXML
    public TableColumn tblRoute;

    @FXML
    public TableColumn tblNumber;

    @FXML
    public TableColumn tblBuyer;

    @FXML
    public TableColumn tblPrice;

    @FXML
    public TableColumn tblLinen;

    @FXML
    public TableColumn tblSendTime;

    @FXML
    public TableColumn tblArriveTime;

    @FXML
    public TableColumn tblTrainName;

    @FXML
    public TableColumn tblWagon;

    @FXML
    public TableColumn tblPlace;

    @FXML
    public TableColumn tblTypePlace;

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


        tblBuyer.setCellValueFactory(new PropertyValueFactory<Route, String>("contact"));

        tblRoute.setCellValueFactory(new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures cellDataFeatures) {
                Ticket ticket = (Ticket) cellDataFeatures.getValue();
                return new SimpleStringProperty(ticket.getFromTown() + " - " + ticket.getToTown());
            }
        });

        tblTypePlace.setCellValueFactory(new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures cellDataFeatures) {
                Ticket ticket = (Ticket) cellDataFeatures.getValue();
                return new SimpleStringProperty(TypePlace.getNameType(ticket.getTypePlace()));
            }
        });

        tblSendTime.setCellValueFactory(new PropertyValueFactory<Route, String>("timeStart"));

        tblArriveTime.setCellValueFactory(new PropertyValueFactory<Route, String>("timeEnd"));

        tblLinen.setCellValueFactory(new PropertyValueFactory<Route, String>("linen"));

        tblPrice.setCellValueFactory(new PropertyValueFactory<Route, String>("price"));

        tblTrainName.setCellValueFactory(new PropertyValueFactory<Route, String>("trainName"));

        tblWagon.setCellValueFactory(new PropertyValueFactory<Route, String>("idWagon"));

        tblPlace.setCellValueFactory(new PropertyValueFactory<Route, String>("placeNumber"));




        tblNumber.setCellValueFactory(new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures cellDataFeatures) {
                return new SimpleIntegerProperty(NumberIDGenerator.generate(tickets, cellDataFeatures));
            }
        });
    }

}
