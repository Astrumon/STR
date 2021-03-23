package com.course_project.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.course_project.FxmlLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

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
    private PasswordField passwordField;

    @FXML
    void buttonLogInAc(ActionEvent event) {
        FxmlLoader object = new FxmlLoader();
        Parent root = object.getPage("basisOperatorInterface");
        Scene scene = buttonLogIn.getScene();
        StackPane mainStackPane = (StackPane) scene.getRoot();
        mainStackPane.getChildren().remove(anchorPaneLoginAccount);
        mainStackPane.getChildren().add(root);
    }

    @FXML
    void initialize() {
        assert textFieldLogin != null : "fx:id=\"textFieldLogin\" was not injected: check your FXML file 'loginAccount.fxml'.";
        assert buttonLogIn != null : "fx:id=\"buttonLogIn\" was not injected: check your FXML file 'loginAccount.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'loginAccount.fxml'.";

    }
}
