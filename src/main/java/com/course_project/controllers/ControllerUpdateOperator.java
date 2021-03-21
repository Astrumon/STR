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

public class ControllerUpdateOperator {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField textFieldLogin;

    @FXML
    private Button buttonSaveOperator;

    @FXML
    private Button buttonDeleteOperator;

    @FXML
    private TextField textFieldPassword;

    private OperatorManager operatorManager;

    private String login;

    private Operator operator;

    @FXML
    void buttonDeleteOperatorAc(ActionEvent event) {

            Operator operator = new Operator();
            operator.setLogin(textFieldLogin.getText());
            if (operatorManager.delete(operator)) {
                AlertGenerator.info("Запис оператора видалено успішно");
            } else {
                AlertGenerator.error("Виникла помилка при видалені оператора");
            }
    }

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
            if (operatorManager.update(operator)) {
                AlertGenerator.info("Пароль оператора оновлено успішно");
            } else {
                AlertGenerator.error("Виникла помилка при оновлені пароля");
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
        assert textFieldLogin != null : "fx:id=\"textFieldLogin\" was not injected: check your FXML file 'updateOperator.fxml'.";
        assert buttonSaveOperator != null : "fx:id=\"buttonSaveOperator\" was not injected: check your FXML file 'updateOperator.fxml'.";
        assert buttonDeleteOperator != null : "fx:id=\"buttonDeleteOperator\" was not injected: check your FXML file 'updateOperator.fxml'.";
        assert textFieldPassword != null : "fx:id=\"textFieldPassword\" was not injected: check your FXML file 'updateOperator.fxml'.";
        operatorManager = new OperatorManager();
        login = OperatorManager.transfer.getLogin();
        textFieldLogin.setText(login);
    }
}
