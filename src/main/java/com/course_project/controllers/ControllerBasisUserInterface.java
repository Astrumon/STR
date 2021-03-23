package com.course_project.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.course_project.FxmlLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class ControllerBasisUserInterface {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane mainStackPane;

    @FXML
    private AnchorPane anchorPaneBasisUserInterface;

    @FXML
    private BorderPane mainPane;

    @FXML
    private Button buttonTicket;

    @FXML
    private Button buttonCargoTransportation;

    @FXML
    private Button buttonLogIn;

    @FXML
    void buttonCargoTransportationAc(ActionEvent event) {

    }

    @FXML
    void buttonLogInAc(ActionEvent event) {
        FxmlLoader object = new FxmlLoader();
        Parent root = object.getPage("loginAccount");
        mainStackPane.getChildren().remove(anchorPaneBasisUserInterface);
        mainStackPane.getChildren().add(root);
    }

    @FXML
    void buttonTicketAc(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert mainStackPane != null : "fx:id=\"mainStackPane\" was not injected: check your FXML file 'basisUserInterface.fxml'.";
        assert anchorPaneBasisUserInterface != null : "fx:id=\"anchorPaneBasisUserIntrface\" was not injected: check your FXML file 'basisUserInterface.fxml'.";
        assert mainPane != null : "fx:id=\"mainPane\" was not injected: check your FXML file 'basisUserInterface.fxml'.";
        assert buttonTicket != null : "fx:id=\"buttonTicket\" was not injected: check your FXML file 'basisUserInterface.fxml'.";
        assert buttonCargoTransportation != null : "fx:id=\"buttonCargoTransportation\" was not injected: check your FXML file 'basisUserInterface.fxml'.";
        assert buttonLogIn != null : "fx:id=\"buttonLogIn\" was not injected: check your FXML file 'basisUserInterface.fxml'.";

    }
}
