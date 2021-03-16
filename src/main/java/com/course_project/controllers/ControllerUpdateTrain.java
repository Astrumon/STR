package com.course_project.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ControllerUpdateTrain {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField textFieldNameTrain;

    @FXML
    private Button buttonDeleteTrain;

    @FXML
    private ListView<?> lstViewFreeCar;

    @FXML
    private ListView<?> lstViewCarConnectedToTheTrain;

    @FXML
    private Button buttonAddToTrain;

    @FXML
    private Button buttonRemoveFromTrain;

    @FXML
    void buttonAddToTrainAc(ActionEvent event) {

    }

    @FXML
    void buttonDeleteTrainAc(ActionEvent event) {

    }

    @FXML
    void buttonRemoveFromTrainAc(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert textFieldNameTrain != null : "fx:id=\"textFieldNameTrain\" was not injected: check your FXML file 'updateTrain.fxml'.";
        assert buttonDeleteTrain != null : "fx:id=\"buttonDeleteTrain\" was not injected: check your FXML file 'updateTrain.fxml'.";
        assert lstViewFreeCar != null : "fx:id=\"lstViewFreeCar\" was not injected: check your FXML file 'updateTrain.fxml'.";
        assert lstViewCarConnectedToTheTrain != null : "fx:id=\"lstViewCarConnectedToTheTrain\" was not injected: check your FXML file 'updateTrain.fxml'.";
        assert buttonAddToTrain != null : "fx:id=\"buttonAddToTrain\" was not injected: check your FXML file 'updateTrain.fxml'.";
        assert buttonRemoveFromTrain != null : "fx:id=\"buttonRemoveFromTrain\" was not injected: check your FXML file 'updateTrain.fxml'.";

    }
}
