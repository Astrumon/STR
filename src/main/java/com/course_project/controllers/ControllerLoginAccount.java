package com.course_project.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.course_project.Admin;
import com.course_project.FxmlLoader;
import com.course_project.data_access.model.Operator;
import com.course_project.support.AlertGenerator;
import com.course_project.support.manager.OperatorManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * Данный класс реализует логику контроллера графического интерфейса экрана входа пользователя
 * Содержит обработку нажатия на кнопку: "Увійти", "Назад"
 * С помощью класса OperatorManager вызывается список зарегистрированных операторов
 * Позволяет в зависимости от введенных данных перейти на главную сцену программы(сцены для админа и для оператора отличаются).
 */
public class ControllerLoginAccount {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPaneLoginAccount;

    @FXML
    private TextField textFieldLogin;

    @FXML
    private Button buttonLogIn;

    @FXML
    private Button buttonBack;

    @FXML
    private PasswordField passwordField;

    private OperatorManager operatorManager;

    @FXML
    void buttonLogInAc(ActionEvent event) {
        boolean enter = false;
        for (Operator operator : operatorManager.getOperators()) {
            if (textFieldLogin.getText().equals(operator.getLogin()) && passwordField.getText().equals(operator.getPassword())) {
                OperatorManager.login = operator.getLogin();
                showBasis();
                AlertGenerator.info("Вітаємо " + operator.getLogin());
                enter = true;
                break;
            } else  if (textFieldLogin.getText().equals(Admin.login) && passwordField.getText().equals(Admin.password)) {
                showBasis();
                enter = true;
                Admin.status = true;
                AlertGenerator.info("Вітаємо " + Admin.login);
                break;
            }
        }

        if (!enter) {
            AlertGenerator.error("Невірний логін або пароль");
        }
    }

    private void showBasis() {
        FxmlLoader object = new FxmlLoader();
        Parent root = object.getPage("basisOperatorInterface");
        Scene scene = buttonLogIn.getScene();
        StackPane mainStackPane = (StackPane) scene.getRoot();
        mainStackPane.getChildren().remove(anchorPaneLoginAccount);
        mainStackPane.getChildren().add(root);
    }

    @FXML
    void buttonBackAc(ActionEvent event) {
        FxmlLoader object = new FxmlLoader();
        Parent root = object.getPage("basisUserInterface");
        Scene scene = buttonBack.getScene();
        StackPane mainStackPane = (StackPane) scene.getRoot();
        mainStackPane.getChildren().remove(anchorPaneLoginAccount);
        mainStackPane.getChildren().add(root);
    }

    @FXML
    void initialize() {
        assert textFieldLogin != null : "fx:id=\"textFieldLogin\" was not injected: check your FXML file 'loginAccount.fxml'.";
        assert buttonLogIn != null : "fx:id=\"buttonLogIn\" was not injected: check your FXML file 'loginAccount.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'loginAccount.fxml'.";
        operatorManager = new OperatorManager();

    }
}
