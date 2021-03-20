package com.course_project.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ControllerCreateOperator {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField textFieldLogin;

    @FXML
    private Button buttonSaveOperator;

    @FXML
    private TextField textFieldPassword;

    @FXML
    void buttonSaveOperatorAc(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert textFieldLogin != null : "fx:id=\"textFieldLogin\" was not injected: check your FXML file 'createOperator.fxml'.";
        assert buttonSaveOperator != null : "fx:id=\"buttonSaveOperator\" was not injected: check your FXML file 'createOperator.fxml'.";
        assert textFieldPassword != null : "fx:id=\"textFieldPassword\" was not injected: check your FXML file 'createOperator.fxml'.";

    }
}
