package com.course_project.support;

import com.course_project.data_access.model.Ticket;
import com.course_project.data_access.model.route.Route;
import com.course_project.data_access.model.route.RouteSet;
import com.course_project.data_access.model.wagon.TypePlace;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class AlertGenerator {

    private static Alert alert = new Alert(Alert.AlertType.NONE);

    public AlertGenerator() {
    }

    public static void info(String text) {
        alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.setTitle("Увага!");
        alert.setContentText(text);
        alert.showAndWait();
    }

    public static void error(String text) {
        alert.setAlertType(Alert.AlertType.ERROR);
        alert.setTitle("Увага!");
        alert.setContentText(text);
        alert.showAndWait();
    }

    public static void tableRoute(List<RouteSet> routeSets) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        VBox dialogPaneContent = new VBox();
        alert.setTitle("Повний маршрут для потяга: " + routeSets.get(0).getTrainName());
        Label label = new Label("Повний маршрут");

        dialogPaneContent.getChildren().addAll(label, getTableWithRouteSet(routeSets));

        alert.getDialogPane().setContent(dialogPaneContent);
        alert.showAndWait();
    }

    public static void showTicket(Ticket ticket) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        VBox dialogPaneContent = new VBox();
        alert.setTitle("Квиток: " + ticket.getContact());
        Label label = new Label("Інформація про квиток");
        alert.setContentText("Інформація про ваш квиток була віддіслана на вашу пошту/квиток");

        dialogPaneContent.getChildren().addAll(label, getInformationToUser(ticket));

        alert.getDialogPane().setContent(dialogPaneContent);
        alert.showAndWait();
    }

    private static TableView getTableWithRouteSet(List<RouteSet> routeSets) {
        ObservableList<RouteSet> routeSets1 = FXCollections.observableArrayList(routeSets);
        TableView<RouteSet> tableView = new TableView<RouteSet>(routeSets1);
        tableView.setPrefHeight(500);
        tableView.setPrefWidth(500);

        TableColumn<RouteSet, String> fromTown = new TableColumn<RouteSet, String>("Звідки");
        fromTown.setCellValueFactory(new PropertyValueFactory<RouteSet, String>("fromTown"));
        tableView.getColumns().add(fromTown);

        TableColumn<RouteSet, String> toTown = new TableColumn<RouteSet, String>("Куди");
        toTown.setCellValueFactory(new PropertyValueFactory<RouteSet, String>("toTown"));
        tableView.getColumns().add(toTown);

        TableColumn<RouteSet, String> dateSend = new TableColumn<RouteSet, String>("Дата відправки");
        dateSend.setCellValueFactory(new PropertyValueFactory<RouteSet, String>("dateSend"));
        tableView.getColumns().add(dateSend);

        TableColumn<RouteSet, String> dateArrive = new TableColumn<RouteSet, String>("Дата прибуття");
        dateArrive.setCellValueFactory(new PropertyValueFactory<RouteSet, String>("dateArrive"));
        tableView.getColumns().add(dateArrive);

        TableColumn<RouteSet, String> sendTime = new TableColumn<RouteSet, String>("Час відправки");
        sendTime.setCellValueFactory(new PropertyValueFactory<RouteSet, String>("sendTime"));
        tableView.getColumns().add(sendTime);

        TableColumn<RouteSet, String> arriveTime = new TableColumn<RouteSet, String>("Час прибуття");
        arriveTime.setCellValueFactory(new PropertyValueFactory<RouteSet, String>("arriveTime"));
        tableView.getColumns().add(arriveTime);

        TableColumn<RouteSet, Integer> price = new TableColumn<RouteSet, Integer>("Вартість, грн");
        price.setCellValueFactory(new PropertyValueFactory<RouteSet, Integer>("price"));
        tableView.getColumns().add(price);

        return tableView;
    }

    private static ListView<String> getInformationToUser(Ticket ticket) {
        ListView<String> listView = new ListView<String>();
        listView.setPrefHeight(250);
        listView.setPrefWidth(300);

        listView.getItems().add("Назва потяга: " + ticket.getTrainName());
        listView.getItems().add("Номер вагону: " + ticket.getIdWagon());
        listView.getItems().add("Номер місця: " + ticket.getPlaceNumber());
        listView.getItems().add("Тип місця: " + TypePlace.getNameType(ticket.getTypePlace()));
        listView.getItems().add("Дата відправки потяга: " + ticket.getDateSend());
        listView.getItems().add("Дата прибуття потяга: " + ticket.getDateArrive());
        listView.getItems().add("Час відправки потяга: " + ticket.getTimeStart());
        listView.getItems().add("Час прибуття потяга: " + ticket.getTimeEnd());
        listView.getItems().add("Маршрут потяга: " + ticket.getFromTown() + " - " + ticket.getToTown());
        listView.getItems().add("Ціна квитка: " + ticket.getPrice() + " грн.");

        return listView;
    }
}


