package com.course_project.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.course_project.FxmlLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

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
    private TableView<?> tableUserTicket;

    @FXML
    private TableColumn<?, ?> tblNumber;

    @FXML
    private TableColumn<?, ?> tblFirstPoint;

    @FXML
    private TableColumn<?, ?> tblLastPoint;

    @FXML
    private TableColumn<?, ?> tblFreePlaces;

    @FXML
    private TableColumn<?, ?> tblSendTime;

    @FXML
    private TableColumn<?, ?> tblArriveTime;

    @FXML
    void buttonFindTicketAc(ActionEvent event) {

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
