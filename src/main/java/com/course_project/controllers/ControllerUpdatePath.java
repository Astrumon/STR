package com.course_project.controllers;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class ControllerUpdatePath {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    /*@FXML
    private JFXTimePicker timePicker1;

    @FXML
    private JFXDatePicker datePicker1;

    @FXML
    private JFXTimePicker timePicker2;

    @FXML
    private JFXDatePicker datePicker2;
    */
    @FXML
    private TextField textFieldPoint1;

    @FXML
    private TextField textFieldPrice;

    @FXML
    private TextField textFieldPoint2;

    @FXML
    private Button buttonNextPoint;

    @FXML
    private Button buttonPreviousPoint;

    @FXML
    private Button buttonAddNewPoint;

    @FXML
    private Button buttonReviewPath;

    @FXML
    private ChoiceBox<?> choiceBoxNameTrain;

    @FXML
    private Button buttonSavePath;

    @FXML
    private Button buttonDeletePath;

    @FXML
    void buttonAddNewPointAc(ActionEvent event) {

    }

    @FXML
    void buttonReviewPathAc(ActionEvent event) {

    }

    @FXML
    void buttonDeletePathAc(ActionEvent event) {

    }

    @FXML
    void buttonNextPointAc(ActionEvent event) {

    }

    @FXML
    void buttonPreviousPointAc(ActionEvent event) {

    }

    @FXML
    void buttonSavePathAc(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert textFieldPoint1 != null : "fx:id=\"textFieldPint1\" was not injected: check your FXML file 'updatePath.fxml'.";
        assert textFieldPoint2 != null : "fx:id=\"textFieldPint2\" was not injected: check your FXML file 'updatePath.fxml'.";
        assert buttonNextPoint != null : "fx:id=\"buttonNextPoint\" was not injected: check your FXML file 'updatePath.fxml'.";
        assert buttonPreviousPoint != null : "fx:id=\"buttonPreviousPoint\" was not injected: check your FXML file 'updatePath.fxml'.";
        assert choiceBoxNameTrain != null : "fx:id=\"choiceBoxNameTrain\" was not injected: check your FXML file 'updatePath.fxml'.";
        assert buttonSavePath != null : "fx:id=\"buttonSavePath\" was not injected: check your FXML file 'updatePath.fxml'.";
        assert buttonDeletePath != null : "fx:id=\"buttonDeletePath\" was not injected: check your FXML file 'updatePath.fxml'.";
    }


}
