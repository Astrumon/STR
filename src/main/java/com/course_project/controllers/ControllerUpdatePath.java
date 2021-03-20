package com.course_project.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControllerUpdatePath {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField textFieldPoint1;

    @FXML
    private TextField textFieldTime1;

    @FXML
    private Label lablePrice1;

    @FXML
    private DatePicker datePicker1;

    @FXML
    private TextField textFieldPrice1;

    @FXML
    private TextField textFieldPrice2;

    @FXML
    private TextField textFieldTime2;

    @FXML
    private TextField textFieldPoint2;

    @FXML
    private DatePicker datePicker2;

    @FXML
    private Button buttonNextPoint;

    @FXML
    private Button buttonPreviousPoint;

    @FXML
    private ChoiceBox<?> choiceBoxNameTrain;

    @FXML
    private Button buttonSavePath;

    @FXML
    private Button buttonDeletePath;

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
        assert textFieldTime1 != null : "fx:id=\"textFieldTime1\" was not injected: check your FXML file 'updatePath.fxml'.";
        assert lablePrice1 != null : "fx:id=\"lablePrice1\" was not injected: check your FXML file 'updatePath.fxml'.";
        assert datePicker1 != null : "fx:id=\"datePicker1\" was not injected: check your FXML file 'updatePath.fxml'.";
        assert textFieldPrice1 != null : "fx:id=\"textFieldPrice1\" was not injected: check your FXML file 'updatePath.fxml'.";
        assert textFieldPrice2 != null : "fx:id=\"textFieldPrice2\" was not injected: check your FXML file 'updatePath.fxml'.";
        assert textFieldTime2 != null : "fx:id=\"textFieldTime2\" was not injected: check your FXML file 'updatePath.fxml'.";
        assert textFieldPoint2 != null : "fx:id=\"textFieldPint2\" was not injected: check your FXML file 'updatePath.fxml'.";
        assert datePicker2 != null : "fx:id=\"datePicker2\" was not injected: check your FXML file 'updatePath.fxml'.";
        assert buttonNextPoint != null : "fx:id=\"buttonNextPoint\" was not injected: check your FXML file 'updatePath.fxml'.";
        assert buttonPreviousPoint != null : "fx:id=\"buttonPreviousPoint\" was not injected: check your FXML file 'updatePath.fxml'.";
        assert choiceBoxNameTrain != null : "fx:id=\"choiceBoxNameTrain\" was not injected: check your FXML file 'updatePath.fxml'.";
        assert buttonSavePath != null : "fx:id=\"buttonSavePath\" was not injected: check your FXML file 'updatePath.fxml'.";
        assert buttonDeletePath != null : "fx:id=\"buttonDeletePath\" was not injected: check your FXML file 'updatePath.fxml'.";
        lablePrice1.setVisible(false);
        textFieldPrice1.setVisible(false);
    }
}
