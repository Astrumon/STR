package com.course_project.controllers;

import com.course_project.data_access.model.Ticket;
import com.course_project.data_access.model.route.Route;
import com.course_project.data_access.model.route.RouteSet;
import com.course_project.data_access.model.wagon.Place;
import com.course_project.data_access.model.wagon.TypePlace;
import com.course_project.data_access.model.wagon.Wagon;
import com.course_project.support.AlertGenerator;
import com.course_project.support.Checker;
import com.course_project.support.creator.TicketCreator;
import com.course_project.support.manager.RouteManager;
import com.course_project.support.manager.TicketManager;
import com.course_project.support.manager.WagonManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerUserBuyTicket {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> choiceBoxTypePlace;

    @FXML
    private CheckBox checkBoxBedLinen;

    @FXML
    private TextField textFieldEmailOrPhoneNumber;

    @FXML
    private Button buttonBuyTicket;

    private RouteSet routeSet;

    private int type;

    private boolean linen = false;

    private TicketCreator ticketCreator = new TicketCreator();


    private WagonManager wagonManager;
    private TicketManager ticketManager = new TicketManager();
    private RouteManager routeManager = new RouteManager();

    Ticket ticket = new Ticket();

    private String contact;
    int freeTicket = 0;
    int soldTicket = 0;

    @FXML
    void buttonBuyTicketAc(ActionEvent event) throws InterruptedException {
        if (checkBoxBedLinen.isSelected()) {
            System.out.println("idRoute: " + (routeSet.getIdRoute()-1));
            ticket.setLinen(true);
        }


        if (checkFieldEmailNumber()) {
            buyPlace();
            System.out.println("SoldTIcket: " + soldTicket);
            Route route = routeManager.getRoute(routeSet.getIdRoute()-1);
            route.setSoldTickets(soldTicket+1);
            routeManager.updateRoute(route);
        }
    }

    private boolean checkFieldEmailNumber() {
        String firstLetter = textFieldEmailOrPhoneNumber.getText().substring(0, 1);
        if (firstLetter.equals("+")) {
            if (Checker.checkValidNumber(textFieldEmailOrPhoneNumber.getText())) {
                contact = textFieldEmailOrPhoneNumber.getText();
                return true;
            } else {
                AlertGenerator.error("Некоректний номер");
            }
        } else {
            if (Checker.checkValidEmail(textFieldEmailOrPhoneNumber.getText())) {
                contact = textFieldEmailOrPhoneNumber.getText();
                return true;
            } else {
                AlertGenerator.error("Некоректна пошта");
                return false;
            }
        }
        return false;
    }

    private void buyPlace() throws InterruptedException {
        soldTicket = 0;
        List<Wagon> wagons = wagonManager.getWagonsByTrainName(routeSet.getTrainName());

        System.out.println(wagons.size()-1);
        for (Wagon wagon : wagons) {

            for (Place place : wagonManager.getPlacesByIdWagon(wagon.getIdWagon())) {
                if (place.getStatus() == Place.FREE && place.getType() == type) {
                        place.setStatus(Place.TAKEN);
                        wagonManager.setStatusPlace(place);
                        ticket.setTrainName(wagon.getTrainName());
                        ticket.setContact(contact);
                        ticket.setTypePlace(type);
                        ticket.setPlaceNumber(place.getNumber());
                        ticket.setDateSend(routeSet.getDateSend());
                        ticket.setDateArrive(routeSet.getDateArrive());
                        ticket.setTimeStart(routeSet.getSendTime());
                        ticket.setTimeEnd(routeSet.getArriveTime());
                        ticket.setFromTown(routeSet.getFromTown());
                        ticket.setToTown(routeSet.getToTown());
                        ticket.setIdWagon(wagon.getIdWagon());
                        ticket.setPrice(routeSet.getPrice());
                        ticket.setIdRoute(routeSet.getIdRoute());
                        ticketCreator.createTicket(ticket);
                        AlertGenerator.showTicket(ticket);
                    return;
                }
                if (place.getStatus() == Place.FREE) {
                    freeTicket++;
                } else {
                    soldTicket++;
                }
            }
        }
    }

    @FXML
    void initialize() {
        assert choiceBoxTypePlace != null : "fx:id=\"choiceBoxTypePlace\" was not injected: check your FXML file 'userBuyTicket.fxml'.";
        assert checkBoxBedLinen != null : "fx:id=\"checkBoxBedLinen\" was not injected: check your FXML file 'userBuyTicket.fxml'.";
        assert textFieldEmailOrPhoneNumber != null : "fx:id=\"textFieldEmailOrPhoneNumber\" was not injected: check your FXML file 'userBuyTicket.fxml'.";
        assert buttonBuyTicket != null : "fx:id=\"buttonBuyTicket\" was not injected: check your FXML file 'userBuyTicket.fxml'.";
        routeSet = RouteManager.transferRouteSet;
        wagonManager = new WagonManager();
        fillChoiceBox();
        getInfoFromBox();

    }

    private void setType(String value) {

        switch (value) {
            case "Сидяче місце":
                type = TypePlace.SEATS;
                break;
            case "Верхнє місце":
                type = TypePlace.MIDDLE;
                break;
            case "Нижнє місце":
                type = TypePlace.LOW;
                break;
            case "VIP місце":
                type = TypePlace.VIP;
                break;
        }
    }

    private void fillChoiceBox() {
        choiceBoxTypePlace.getItems().add("Сидяче місце");
        choiceBoxTypePlace.getItems().add("Верхнє місце");
        choiceBoxTypePlace.getItems().add("Нижнє місце");
        choiceBoxTypePlace.getItems().add("VIP місце");
    }

    private void getInfoFromBox() {
        choiceBoxTypePlace.valueProperty().addListener((obc, oldItem, newItem) -> {
            setType(newItem);
        });
    }
}
