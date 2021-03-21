package com.course_project.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.course_project.data_access.model.Operator;
import com.course_project.support.AlertGenerator;
import com.course_project.support.Checker;
import com.course_project.support.manager.OperatorManager;
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

    private OperatorManager operatorManager;

    @FXML
    void buttonSaveOperatorAc(ActionEvent event) {
        if (isEmptyField()) {
            AlertGenerator.error("Заповніть усі поля");
        } else if(!isCorrectData()) {
            AlertGenerator.error("Некорректно ведені дані");
        } else {
            Operator operator = new Operator();
            operator.setLogin(textFieldLogin.getText());
            operator.setPassword(textFieldPassword.getText());
            if (operatorManager.create(operator)) {
                AlertGenerator.info("Запис оператора створено успішно");
            } else {
                AlertGenerator.error("Виникла помилка при створені оператора");
            }

        }


    }

    private boolean isEmptyField() {
        return Checker.checkEmptyValue(textFieldLogin.getText())
                && Checker.checkEmptyValue(textFieldPassword.getText());
    }

    private boolean isCorrectData() {
        return Checker.checkStringValue(textFieldLogin.getText())
                && Checker.checkStringValue(textFieldPassword.getText());
    }
    @FXML
    void initialize() {
        assert textFieldLogin != null : "fx:id=\"textFieldLogin\" was not injected: check your FXML file 'createOperator.fxml'.";
        assert buttonSaveOperator != null : "fx:id=\"buttonSaveOperator\" was not injected: check your FXML file 'createOperator.fxml'.";
        assert textFieldPassword != null : "fx:id=\"textFieldPassword\" was not injected: check your FXML file 'createOperator.fxml'.";

        operatorManager = new OperatorManager();
    }
}
