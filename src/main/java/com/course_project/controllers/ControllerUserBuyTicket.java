package com.course_project.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class ControllerUserBuyTicket {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<?> choiceBoxTypeCar;

    @FXML
    private ChoiceBox<?> choiceBoxTypePlace;

    @FXML
    private CheckBox checkBoxBedLinen;

    @FXML
    private TextField textFieldEmailOrPhoneNumber;

    @FXML
    private Button buttonBuyTicket;

    @FXML
    void buttonBuyTicketAc(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert choiceBoxTypeCar != null : "fx:id=\"choiceBoxTypeCar\" was not injected: check your FXML file 'userBuyTicket.fxml'.";
        assert choiceBoxTypePlace != null : "fx:id=\"choiceBoxTypePlace\" was not injected: check your FXML file 'userBuyTicket.fxml'.";
        assert checkBoxBedLinen != null : "fx:id=\"checkBoxBedLinen\" was not injected: check your FXML file 'userBuyTicket.fxml'.";
        assert textFieldEmailOrPhoneNumber != null : "fx:id=\"textFieldEmailOrPhoneNumber\" was not injected: check your FXML file 'userBuyTicket.fxml'.";
        assert buttonBuyTicket != null : "fx:id=\"buttonBuyTicket\" was not injected: check your FXML file 'userBuyTicket.fxml'.";

    }
}
